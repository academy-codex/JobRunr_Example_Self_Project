public class ReqPojo {
    private String scheduleId;
    private String cronExpr;
    private MetaData metaData;

    static class MetaData {
        private String configId;
        private String dataSetId;
        private String runMode;
        private String flowName;
        private String tenant;
        private String entityName;
        private String dataSetName;
        private String jobType;
        private boolean executeWithAlreadyRunning;

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

        public String getRunMode() {
            return runMode;
        }

        public void setRunMode(String runMode) {
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

        public String getJobType() {
            return jobType;
        }

        public void setJobType(String jobType) {
            this.jobType = jobType;
        }

        public boolean isExecuteWithAlreadyRunning() {
            return executeWithAlreadyRunning;
        }

        public void setExecuteWithAlreadyRunning(boolean executeWithAlreadyRunning) {
            this.executeWithAlreadyRunning = executeWithAlreadyRunning;
        }
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

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}