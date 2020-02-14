package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.web.DTO.RequirementsDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RequirementsService {
    public List<RequirementsDTO> getJiraRequirements() {
        return getDataFromJira();
    }

    private List<RequirementsDTO> getDataFromJira() {
        //Some processing here
        //Mock for time being:
        List<RequirementsDTO> rqs = new ArrayList<>();
        String[] fixVersions = {"1.1", "1.2", "1.3"};
        rqs.add(new RequirementsDTO("TESTDEV-231", "Should fix this thing1", "Minor", "To Do", new ArrayList<>(Arrays.asList(fixVersions)), "Super component1"));
        rqs.add(new RequirementsDTO("TESTDEV-232", "Should fix this thing2", "High", "To Do", new ArrayList<>(Arrays.asList(fixVersions)), "Super component2"));
        rqs.add(new RequirementsDTO("TESTDEV-233", "Should fix this thing3", "Minor", "To Do", new ArrayList<>(Arrays.asList(fixVersions)), "Super component3"));
        rqs.add(new RequirementsDTO("TESTDEV-234", "Should fix this thing4", "Major", "To Do", new ArrayList<>(Arrays.asList(fixVersions)), "Super component4"));
        rqs.add(new RequirementsDTO("TESTDEV-235", "Should fix this thing4", "Major", "To Do", new ArrayList<>(Arrays.asList(fixVersions)), "Super component5"));
        rqs.add(new RequirementsDTO("TESTDEV-236", "Should fix this thing4", "Major", "To Do", new ArrayList<>(Arrays.asList(fixVersions)), "Super component6"));
        rqs.add(new RequirementsDTO("TESTDEV-237", "Should fix this thing4", "Major", "To Do", new ArrayList<>(Arrays.asList(fixVersions)), "Super component7"));
        rqs.add(new RequirementsDTO("TESTDEV-238", "Should fix this thing4", "Major", "To Do", new ArrayList<>(Arrays.asList(fixVersions)), "Super component8"));
        return rqs;
    }
}
