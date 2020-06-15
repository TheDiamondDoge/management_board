package com.aiksanov.api.project.util.enums;

public enum CommentsFieldNames {
    UPDATED_BP ("updatedBusinessPlan"),
    DR_CHECKLIST ("drChecklist"),
    LESSONS_LEARNED ("lessonsLearned"),
    PROJECT_PLAN ("projectPlan"),
    LAUNCHING_PLAN ("launchingPlan");
    private final String title;

    CommentsFieldNames(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
