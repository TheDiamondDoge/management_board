package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.ProjectGeneralService;
import com.aiksanov.api.project.business.service.ProjectTableViewService;
import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.util.enums.WorkspaceStatus;
import com.aiksanov.api.project.web.DTO.PWSTableViewDTO;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
@RunWith(SpringRunner.class)
@WebMvcTest(ProjectsController.class)
public class ProjectsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectTableViewService projectTableViewService;

    @MockBean
    private ProjectGeneralService generalService;

    @Test
    public void getProjectInfoById() throws Exception {
        Project dummyProject = new Project();
        dummyProject.setName("Dummy");

        when(this.generalService.getProjectGeneralInfo(0)).thenReturn(dummyProject);
        this.mockMvc.perform(get("/api/projects/0")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Dummy\"")))
                .andExpect(content().string(containsString("\"product\":null")))
                .andExpect(content().string(containsString("\"milestones\":null")));
//                .andExpect(content().string(containsString("\"healthIndicators\":null")));
    }

    @Test
    public void getAllProjectsInfo() throws Exception {
        List<Project> dummyProjectsWithoutEPM = generateProjectsWithEpm(false);

        when(this.projectTableViewService.getProjectsData(false, WorkspaceStatus.ENABLED)).thenReturn(dummyProjectsWithoutEPM);
        this.mockMvc.perform(get("/api/projects")
                .param("isEPM", "0")
                .param("status", "enabled")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"First Dummy\"")))
                .andExpect(content().string(containsString("\"name\":\"Second Dummy\"")))
                .andExpect(content().string(containsString("\"epm\":false")));

        List<Project> dummyProjectsWithEPM = generateProjectsWithEpm(true);
        when(this.projectTableViewService.getProjectsData(true, WorkspaceStatus.ENABLED)).thenReturn(dummyProjectsWithEPM);

        this.mockMvc.perform(get("/api/projects")
                .param("isEPM", "1")
                .param("status", "enabled")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"First Dummy\"")))
                .andExpect(content().string(containsString("\"name\":\"Second Dummy\"")))
                .andExpect(content().string(containsString("\"epm\":true")));
    }

    private List<Project> generateProjectsWithEpm(Boolean isEpm){
        Project dummyProjectOne = new Project();
        dummyProjectOne.setName("First Dummy");
        dummyProjectOne.setEpm(isEpm);

        Project dummyProjectTwo = new Project();
        dummyProjectTwo.setName("Second Dummy");
        dummyProjectTwo.setEpm(isEpm);

        List<Project> dummyProjects = new ArrayList<>();
        dummyProjects.add(dummyProjectOne);
        dummyProjects.add(dummyProjectTwo);

        return dummyProjects;
    }

    @Test
    public void getProjectsTableView() throws Exception {
        Project project = new Project();
        project.setName("Dummy project");
        PWSTableViewDTO dummyDto = new PWSTableViewDTO(project, null, null);

        List<PWSTableViewDTO> dtoList = new ArrayList<>();
        dtoList.add(dummyDto);

        when(this.projectTableViewService.getProjectsListView(true, WorkspaceStatus.ENABLED)).thenReturn(dtoList);

        this.mockMvc.perform(get("/api/projects/tableview")
                .param("isEPM", "1")
                .param("status", "enabled")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"projectName\":\"Dummy project\"")))
                .andExpect(content().string(containsString("\"businessLineManager\":null")))
                .andExpect(content().string(containsString("\"orDate\":null")))
                .andExpect(content().string(containsString("\"scheduleStatus\":0")));
    }
}