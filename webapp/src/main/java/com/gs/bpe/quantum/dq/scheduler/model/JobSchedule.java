package com.gs.bpe.quantum.dq.scheduler.model;

public class JobSchedule {
    private String scheduleId;
    private String cronExpr;
    private ScheduleMetaData metaData;

    public JobSchedule(String scheduleId, String cronExpr, ScheduleMetaData metaData) {
        this.scheduleId = scheduleId;
        this.cronExpr = cronExpr;
        this.metaData = metaData;
    }
    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getCronExpr() {
        return cronExpr;
    }

    public void setCronExpr(String cronExpr) {
        this.cronExpr = cronExpr;
    }

    public ScheduleMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(ScheduleMetaData metaData) {
        this.metaData = metaData;
    }
}
