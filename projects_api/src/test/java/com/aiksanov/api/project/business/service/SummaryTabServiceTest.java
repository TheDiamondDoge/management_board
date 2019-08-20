package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.ProjectURLsRepository;
import com.aiksanov.api.project.data.repository.StatusReportRepository;
import com.aiksanov.api.project.web.DTO.SummaryDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SummaryTabServiceTest {
    @Mock
    private GeneralRepository generalRepository;

    @Mock
    private ProjectURLsRepository urlsRepository;

    @Mock
    private StatusReportRepository statusReportRepository;

    @InjectMocks
    private SummaryTabService summaryTabService;

    @Test
    public void getSummaryDTO() {
        Project dummyProject = getProject();
        ProjectURLs dummyUrls = getUrls();
        StatusReport dummyStatus = getStatus();

        when(this.generalRepository.existsById(0)).thenReturn(true);
        when(this.generalRepository.findById(0)).thenReturn(Optional.of(dummyProject));
        when(this.urlsRepository.findById(0)).thenReturn(Optional.of(dummyUrls));
        when(this.statusReportRepository.findById(0)).thenReturn(Optional.of(dummyStatus));

        SummaryDTO summaryDTO = this.summaryTabService.getSummaryDTO(0);

        assertEquals(summaryDTO.getProjectName(), dummyProject.getName());
        assertEquals(summaryDTO.getProjectDescription(), dummyProject.getAdditionalInfo().getDescription());
        assertEquals(summaryDTO.getProjectManager(), dummyProject.getManager());
        assertEquals(summaryDTO.getBusinessLineManager(), dummyProject.getAdditionalInfo().getBusinessLineManager());
        assertEquals(summaryDTO.getProductLineManager(), dummyProject.getProduct().getManager());
        assertEquals(summaryDTO.getProductLineManager(), dummyProject.getProduct().getManager());
        assertEquals(summaryDTO.getProjectState(), dummyProject.getState());
        assertEquals(summaryDTO.getProjectRigor(), dummyProject.getRigor());
        assertEquals(summaryDTO.getCharter(), dummyUrls.getCharter());
        assertEquals(summaryDTO.getOrBusinessPlan(), dummyUrls.getOrBusinessPlan());
        assertEquals(summaryDTO.getUpdatedBusinessPlan(), dummyUrls.getUpdatedBusinessPlan());
        assertEquals(summaryDTO.getDrChecklist(), dummyUrls.getTailoredChecklist());
        assertEquals(summaryDTO.getLessonsLearned(), dummyUrls.getLessonsLearned());
        assertEquals(summaryDTO.getSponsor(), dummyProject.getAdditionalInfo().getSponsor());
        assertEquals(summaryDTO.getBusinessDivision(), dummyProject.getProduct().getDivision());
        assertEquals(summaryDTO.getBusinessUnit(), dummyProject.getProduct().getBusinessUnit());
        assertEquals(summaryDTO.getProductLine(), dummyProject.getProduct().getProductLine());
        assertEquals(summaryDTO.getWorkspaceState(), dummyProject.getWorkspaceInfo().getStatus());
        assertEquals(summaryDTO.getProjectType(), dummyProject.getType());
        assertEquals(summaryDTO.getOemPartner(), dummyProject.getAdditionalInfo().getOemPartner());
        //space for epm
        assertEquals(summaryDTO.getPwsLastUpdatedDate(), dummyProject.getWorkspaceInfo().getModified());
        assertEquals(summaryDTO.getPwsLastUpdatedBy(), dummyProject.getWorkspaceInfo().getModifiedBy());
    }

    private Project getProject(){
        Product dummyProduct = new Product();
        dummyProduct.setProductLine("Product Line");
        dummyProduct.setName("Name");
        dummyProduct.setManager("Manager");
        dummyProduct.setGroup("Group");
        dummyProduct.setDivision("Division");
        dummyProduct.setBusinessUnit("BU");
        dummyProduct.setProductUnit("PU");
        dummyProduct.setTeam("Team");
        dummyProduct.setRelease("Release");
        dummyProduct.setProjectID(1);

        ProjectAdditionalInfo additionalInfo = new ProjectAdditionalInfo();
        additionalInfo.setBusinessLineManager("Awesome BLM");

        WorkspaceInfo workspaceInfo = new WorkspaceInfo();
        workspaceInfo.setId(0);
        workspaceInfo.setModified(Date.valueOf("2019-04-22"));
        workspaceInfo.setModifiedBy("User csl");
        workspaceInfo.setStatus("Enabled");

        Project project = new Project();
        project.setUid("Dummy UID");
        project.setName("Name");
        project.setType("Program");
        project.setRigor("Rigor");
        project.setState("State");
        project.setManager("Manager");
        project.setPercentOfComplition(100);
        project.setEpm(false);
        project.setTemplate("Template");
        project.setProduct(dummyProduct);
        project.setAdditionalInfo(additionalInfo);
        project.setWorkspaceInfo(workspaceInfo);

        return project;
    }

    private ProjectURLs getUrls(){
        ProjectURLs urls = new ProjectURLs();
        urls.setCharter("Charter");
        urls.setOrBusinessPlan("Business Plan");
        urls.setUpdatedBusinessPlan("Updated Business Plan");
        urls.setTailoredChecklist("Checklist");
        urls.setLessonsLearned("Lessons");
        urls.setCollabUrl("Collab site");
        urls.setPwaUrl("PWA");
        urls.setDocumentsRepoUrl("Documents Repo");
        urls.setDefectsUrl("Defects url");

        return urls;
    }

    private StatusReport getStatus(){
        StatusReport report = new StatusReport();
        report.setExecutiveSummary("Dummy Summary right here!");
        report.setActionsNeeded("Dummy Actions right here!");

        return report;
    }
}