package com.gs.bpe.quantum.dq.scheduler.model;

public class ScheduleMetaData {
    private String configId;
    private String dataSetId;
    private RunMode runMode = RunMode.ASYNC;

    private String flowName;
    private String tenant;
    private String entityName;
    private String dataSetName;

    private JobType jobType;

    private boolean executeWithAlreadyRunning = false;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getDataSetId() {
        return dataSetId;
    }

    public void setDataSetId(String dataSetId) {
        this.dataSetId = dataSetId;
    }

    public RunMode getRunMode() {
        return runMode;
    }

    public void setRunMode(RunMode runMode) {
        this.runMode = runMode;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getDataSetName() {
        return dataSetName;
    }

    public void setDataSetName(String dataSetName) {
        this.dataSetName = dataSetName;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public boolean isExecuteWithAlreadyRunning() {
        return executeWithAlreadyRunning;
    }

    public void setExecuteWithAlreadyRunning(boolean executeWithAlreadyRunning) {
        this.executeWithAlreadyRunning = executeWithAlreadyRunning;
    }

    @Override
    public String toString() {
        return "ScheduleMetaData{" +
                "configId='" + configId + '\'' +
                ", dataSetId='" + dataSetId + '\'' +
                ", runMode=" + runMode +
                ", flowName='" + flowName + '\'' +
                ", tenant='" + tenant + '\'' +
                ", entityName='" + entityName + '\'' +
                ", dataSetName='" + dataSetName + '\'' +
                ", jobType=" + jobType +
                '}';
    }
}
