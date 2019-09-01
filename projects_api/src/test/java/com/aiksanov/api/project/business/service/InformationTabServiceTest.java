package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.ProjectURLsRepository;
import com.aiksanov.api.project.web.DTO.InformationDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class InformationTabServiceTest {

    @Mock
    private GeneralRepository generalRepository;

    @Mock
    private ProjectURLsRepository urlsRepository;

    @InjectMocks
    private InformationTabService informationTabService;

    @Test
    public void getInfoTabData() {
        Project dummyProject = getProject();
        ProjectURLs dummyUrls = getUrls();

        when(this.generalRepository.findById(0)).thenReturn(Optional.of(dummyProject));
        when(this.urlsRepository.findById(0)).thenReturn(Optional.of(dummyUrls));

        InformationDTO informationDTO = this.informationTabService.getInfoTabData(0);

        assertEquals(informationDTO.getProjectDescription(), dummyProject.getAdditionalInfo().getDescription());
        assertEquals(informationDTO.getOemPartner(), dummyProject.getAdditionalInfo().getOemPartner());
        assertEquals(informationDTO.getProductRelease(), dummyProject.getProduct().getRelease());
        assertEquals(informationDTO.getProjectType(), dummyProject.getType());
        assertEquals(informationDTO.getProjectRigor(), dummyProject.getRigor());
        assertEquals(informationDTO.getProjectState(), dummyProject.getState());
        assertEquals(informationDTO.getBusinessDivision(), dummyProject.getProduct().getDivision());
        assertEquals(informationDTO.getBusinessUnit(), dummyProject.getProduct().getBusinessUnit());
        assertEquals(informationDTO.getProductLine(), dummyProject.getProduct().getProductLine());
        assertEquals(informationDTO.getProductName(), dummyProject.getProduct().getName());
        assertEquals(informationDTO.getSponsor(), dummyProject.getAdditionalInfo().getSponsor());
        assertEquals(informationDTO.getBusinessLineManager(), dummyProject.getAdditionalInfo().getBusinessLineManager());
        assertEquals(informationDTO.getProductLineManager(), dummyProject.getProduct().getManager());
        assertEquals(informationDTO.getProjectManager(), dummyProject.getManager());
        assertEquals(informationDTO.getCharter(), dummyUrls.getCharter());
        assertEquals(informationDTO.getOrBusinessPlan(), dummyUrls.getOrBusinessPlan());
        assertEquals(informationDTO.getUpdatedBusinessPlan(), dummyUrls.getUpdatedBusinessPlan());
        assertEquals(informationDTO.getDrChecklist(), dummyUrls.getTailoredChecklist());
        assertEquals(informationDTO.getLessonsLearned(), dummyUrls.getLessonsLearned());
//        assertEquals(informationDTO.getMetricsScope());
//        assertEquals(informationDTO.getRqRelease());
//        assertEquals(informationDTO.getEcmaBacklogTarget());
//        assertEquals(informationDTO.isComposite());
        assertEquals(informationDTO.getProjectCollabUrl(), dummyUrls.getCollabUrl());
        assertEquals(informationDTO.getProjectPWASiteUrl(), dummyUrls.getPwaUrl());
        assertEquals(informationDTO.getDocRepositoryUrl(), dummyUrls.getDocumentsRepoUrl());
        assertEquals(informationDTO.getDefectsUrl(), dummyUrls.getDefectsUrl());
        assertEquals(informationDTO.getRequirementsUrl(), dummyUrls.getRequirementsUrl());
        assertEquals(informationDTO.getCisUrl(), dummyUrls.getCisUrl());

    }


    private Project getProject(){
        Product dummyProduct = new Product();
        dummyProduct.setName("Name");
        dummyProduct.setProductLine("Product Line");
        dummyProduct.setManager("Manager");
        dummyProduct.setDivision("Division");
        dummyProduct.setBusinessUnit("BU");
        dummyProduct.setRelease("Release");

        ProjectAdditionalInfo additionalInfo = new ProjectAdditionalInfo();
        additionalInfo.setBusinessLineManager("Awesome BLM");
        additionalInfo.setSponsor("Awesome sponsor");
        additionalInfo.setDescription("Awesome project");
        additionalInfo.setOemPartner("Awesome partner");

        Project project = new Project();
        project.setName("Name");
        project.setType("Program");
        project.setRigor("Rigor");
        project.setState("State");
        project.setManager("Manager");
        project.setProduct(dummyProduct);
        project.setAdditionalInfo(additionalInfo);

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
        urls.setRequirementsUrl("Req");
        urls.setCisUrl("CIS url");

        return urls;
    }


}