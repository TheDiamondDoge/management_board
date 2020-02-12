package com.aiksanov.api.project.util;

import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.MilestoneRepository;
import com.aiksanov.api.project.util.enums.MilestoneLabels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
