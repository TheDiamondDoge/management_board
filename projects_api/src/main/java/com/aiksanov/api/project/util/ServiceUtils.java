package com.aiksanov.api.project.util;

import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.MilestoneRepository;
import com.aiksanov.api.project.exceptions.ProjectDoesNotExist;
import com.aiksanov.api.project.util.enums.MilestoneLabels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class ServiceUtils {
    private MilestoneRepository milestoneRepository;
    private GeneralRepository generalRepository;

    @Autowired
    public ServiceUtils(MilestoneRepository milestoneRepository, GeneralRepository generalRepository) {
        this.milestoneRepository = milestoneRepository;
        this.generalRepository = generalRepository;
    }

    public boolean isDr1Exists(int projectID) {
        return milestoneRepository.existsById(new MilestonePK(projectID, MilestoneLabels.DR1.getLabel()));
    }

    public boolean isProjectExist(int projectID) {
        return this.generalRepository.existsById(projectID);
    }

    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public Date getCurrentDate() {
        return new Date();
    }

    public List<Integer> generateRandomDataset() {
        List<Integer> numbers = new ArrayList<>();
        for (int j = 0; j < 15; j++) {
            numbers.add(getRandomInt() / 10000000);
        }

        return numbers;
    }

    private int getRandomInt() {
        Random rand = new Random();
        return Math.abs(rand.nextInt());
    }

    public HttpHeaders getFileDownloadHeaders(String filename) {
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return header;
    }

    public String getProjectName(int projectId) {
        Project project = this.generalRepository.findById(projectId).orElseThrow(ProjectDoesNotExist::new);
        return project.getName();
    }
}
