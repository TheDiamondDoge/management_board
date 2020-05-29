package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.ReportSnapshotInfoRepository;
import com.aiksanov.api.project.data.repository.ReportSnapshotRepository;
import com.aiksanov.api.project.data.repository.StatusReportRepository;
import com.aiksanov.api.project.exceptions.ProjectDoesNotExistException;
import com.aiksanov.api.project.exceptions.TooMuchFilesException;
import com.aiksanov.api.project.web.DTO.Base64ImageDTO;
import com.aiksanov.api.project.web.DTO.reports.ReportTabDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsSaveDTO;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReportService {
    @Value("${reportImages.file.storage}")
    private String IMG_SAVE_PATH;

    @Value("${reportImages.max.amount}")
    private int maxAmount;
    private PptGenerationService pptGenerationService;
    private StatusReportRepository reportRepository;
    private ReportSnapshotInfoRepository reportSnapshotInfoRepository;
    private GeneralRepository generalRepository;
    private ReportSnapshotRepository snapshotRepository;

    @Autowired
    public ReportService(PptGenerationService pptGenerationService, StatusReportRepository reportRepository,
                         ReportSnapshotRepository snapshotRepository, GeneralRepository generalRepository,
                         ReportSnapshotInfoRepository reportSnapshotInfoRepository) {
        this.reportSnapshotInfoRepository = reportSnapshotInfoRepository;
        this.pptGenerationService = pptGenerationService;
        this.reportRepository = reportRepository;
        this.snapshotRepository = snapshotRepository;
        this.generalRepository = generalRepository;
    }

    public ReportTabDTO getReportTab(int projectId) {
        Project project = this.generalRepository.findById(projectId).orElseThrow(ProjectDoesNotExistException::new);
        ReportTabDTO dto = new ReportTabDTO();
        if (Objects.nonNull(project)) {
            dto.setProjectName(project.getName());
            dto.setProjectManager(project.getManager());
            WorkspaceInfo info = project.getWorkspaceInfo();
            if (Objects.nonNull(info)) {
                dto.setUpdatedOn(info.getModified());
            }
        }

        return dto;
    }

    public UserReportsDTO getUserReports(int projectId) {
        StatusReport report = this.reportRepository.findById(projectId).orElseGet(StatusReport::new);
        return new UserReportsDTO(report);
    }

    @Transactional
    public void saveReport(int projectId, UserReportsSaveDTO dto) throws Exception {
        ByteArrayResource pptBinary = pptGenerationService.createPptReportSnapshot(projectId);
        StatusReportSnapshot snapshot = new StatusReportSnapshot(0, pptBinary.getByteArray());
        StatusReportSnapshot savedSnap = this.snapshotRepository.save(snapshot);
        int snapId = savedSnap.getId();

        StatusReportSnapshotInfo snapshotInfo = new StatusReportSnapshotInfo(snapId, projectId, new Date(), "Test User");
        this.reportSnapshotInfoRepository.save(snapshotInfo);

        StatusReport report = getStatusReportObj(projectId, dto);
        this.reportRepository.save(report);
//        this.generalService.modifyWorkspaceUpdatedBy(projectId, "TestRepSaver");
    }

    private StatusReport getStatusReportObj(int projectId, UserReportsSaveDTO dto) {
        StatusReport report = new StatusReport();
        report.setProjectId(projectId);
        report.setExecutiveSummary(dto.getSummary());
        report.setRedFlag(dto.getRed());
        report.setOrangeFlag(dto.getOrange());
        report.setGreenFlag(dto.getGreen());
        report.setDetails(dto.getDetails());
        return report;
    }

    public List<Base64ImageDTO> getReportImages(int projectId) {
        String folderPath = IMG_SAVE_PATH + "/" + projectId;
        List<Base64ImageDTO> images = new ArrayList<>();
        final File folder = new File(folderPath);
        if (folder.exists()) {
            try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
                images = paths
                        .filter(Files::isRegularFile)
                        .map(file ->
                                {
                                    try {
                                        String filename = file.getName(file.getNameCount() - 1).toString();
                                        String base64ImgString = fileToHtmlBase64Img(file);
                                        return new Base64ImageDTO(filename, base64ImgString);
                                    } catch (IOException e) {
                                        return null;
                                    }
                                }
                        ).filter(Objects::nonNull)
                        .sorted()
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return images;
    }

    public void saveImages(int projectId, MultipartFile[] files) throws IOException, TooMuchFilesException {
        String folderPath = IMG_SAVE_PATH + "/" + projectId;
        File dir = new File(folderPath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        int filesAmount = Objects.nonNull(dir.listFiles()) ? dir.listFiles().length : 0;

        if (filesAmount + files.length > maxAmount) throw new TooMuchFilesException();

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (Objects.isNull(file)) continue;

            file.getContentType();
            byte[] fileBytes = file.getBytes();
            String filePath = folderPath + "/" + new Date().getTime() + i + "." + getFileFormat(file.getOriginalFilename());

            File localFile = new File(filePath);
            Files.write(Paths.get(filePath), fileBytes);
        }
    }

    public void deleteImage(int projectId, String filename) {
        String folderPath = IMG_SAVE_PATH + "/" + projectId;
        String filePath = folderPath + "/" + filename;
        File file = new File(filePath);
        file.delete();
    }

    private String getFileFormat(String filename) {
        String[] split = filename.split("\\.");
        return split[split.length - 1];
    }

    private String fileToHtmlBase64Img(Path file) throws IOException {
        String filename = file.getName(file.getNameCount() - 1).toString();
        byte[] encodedBytes = FileUtils.readFileToByteArray(new File(String.valueOf(file)));
        String encodedString = Base64.getEncoder().encodeToString(encodedBytes);
        return "data:image/" + getFileFormat(filename) + ";base64, " + encodedString;
    }
}
