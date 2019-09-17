package com.aiksanov.api.project.web.DTO;

public class BlcIndicators {
    private int or;
    private int charter;
    private int prjPlan;
    private int tailoring;
    private int accPrgMgr;
    private int accCoreTeam;
    private int bpPlan;
    private int bpSales;
    private int launchPlan;
    private int launchSales;
    private int lessons;
    private int risks;

    public BlcIndicators() {
    }

    public BlcIndicators(int or, int charter, int prjPlan, int tailoring, int accPrgMgr, int accCoreTeam, int bpPlan,
                         int bpSales, int launchPlan, int launchSales, int lessons, int risks) {
        this.or = or;
        this.charter = charter;
        this.prjPlan = prjPlan;
        this.tailoring = tailoring;
        this.accPrgMgr = accPrgMgr;
        this.accCoreTeam = accCoreTeam;
        this.bpPlan = bpPlan;
        this.bpSales = bpSales;
        this.launchPlan = launchPlan;
        this.launchSales = launchSales;
        this.lessons = lessons;
        this.risks = risks;
    }

    public int getOr() {
        return or;
    }

    public int getCharter() {
        return charter;
    }

    public int getPrjPlan() {
        return prjPlan;
    }

    public int getTailoring() {
        return tailoring;
    }

    public int getAccPrgMgr() {
        return accPrgMgr;
    }

    public int getAccCoreTeam() {
        return accCoreTeam;
    }

    public int getBpPlan() {
        return bpPlan;
    }

    public int getBpSales() {
        return bpSales;
    }

    public int getLaunchPlan() {
        return launchPlan;
    }

    public int getLaunchSales() {
        return launchSales;
    }

    public int getLessons() {
        return lessons;
    }

    public int getRisks() {
        return risks;
    }
}
