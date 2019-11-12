package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.web.DTO.PWSTableViewDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProjectTableViewServiceTest {

    @Mock
    private GeneralRepository generalRepository;

    @InjectMocks
    private ProjectTableViewService projectTableViewService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getListView() {
        Project dummyProject = getDummyNotEPMProject();
        List<Project> it = new ArrayList<>();
        it.add(dummyProject);

        Mockito.when(this.generalRepository.findAllByEpm(false)).thenReturn(it);

        List<PWSTableViewDTO> listViewProject = this.projectTableViewService.getProjectsListView(false, null);
        PWSTableViewDTO tableView = listViewProject.get(0);

        assertEquals(tableView.getProjectName(), dummyProject.getName());
        assertEquals(tableView.getProjectManager(), dummyProject.getManager());
        assertEquals(tableView.getProjectRelease(), dummyProject.getProduct().getRelease());
        assertEquals(tableView.getProjectManager(), dummyProject.getManager());
        assertEquals(tableView.getBusinessLineManager(), dummyProject.getAdditionalInfo().getBusinessLineManager());
        assertEquals(tableView.getProductLineManager(), dummyProject.getProduct().getManager());
        assertEquals(tableView.getBusinessDivision(), dummyProject.getProduct().getDivision());
        assertEquals(tableView.getBusinessUnit(), dummyProject.getProduct().getBusinessUnit());
        assertEquals(tableView.getProductLine(), dummyProject.getProduct().getProductLine());
        assertEquals(tableView.getProjectState(), dummyProject.getState());
        assertEquals(tableView.getProjectRigor(), dummyProject.getRigor());
        assertEquals(tableView.getProjectType(), dummyProject.getType());
//        assertEquals(tableView.getOrDate(), dummyProject.getMilestones().get(0).getBaselineDate());
//        assertEquals(tableView.getScheduleStatus(),)*/;
    }

    private Project getDummyNotEPMProject(){
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

        Milestone dummyMilestoneOR = new Milestone();
        MilestonePK dummyMilestonePK = new MilestonePK(1, "OR");
        dummyMilestoneOR.setMilestonePK(dummyMilestonePK);
        dummyMilestoneOR.setBaselineDate(Date.valueOf("2018-11-23"));
        dummyMilestoneOR.setActualDate(null);
        dummyMilestoneOR.setCompletion(75);
        dummyMilestoneOR.setMeetingMinutes("dummyUrl.com");
        dummyMilestoneOR.setShown(true);

        List<Milestone> dummyMilestoneList = new ArrayList<>();
        dummyMilestoneList.add(dummyMilestoneOR);

        ProjectAdditionalInfo additionalInfo = new ProjectAdditionalInfo();
        additionalInfo.setBusinessLineManager("Awesome BLM");

        Project project = new Project();
        project.setUid("Dummy UID");
        project.setName("Name");
        project.setType("Program");
        project.setRigor("Rigor");
        project.setState("State");
        project.setManager("Manager");
        project.setPercentOfCompletion(100);
        project.setEpm(false);
        project.setTemplate("Template");
        project.setProduct(dummyProduct);
//        project.setMilestones(dummyMilestoneList);
        project.setAdditionalInfo(additionalInfo);

        return project;

    }
}