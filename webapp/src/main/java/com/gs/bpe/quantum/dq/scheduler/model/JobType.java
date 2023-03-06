package com.gs.bpe.quantum.dq.scheduler.model;

public enum JobType {
    CUSTOM_RULE("customRule"), ANOMALY_DETECTION("anomalyDetection"), DAILY_PROFILE("dailyProfile");
    private final String alias;
    JobType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }
}
