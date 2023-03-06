import kong.unirest.Unirest;

import java.util.Random;

public class ScheduleConcurrencyTest {
    public static void main(String[] args) {
        createSchedules();
    }

    private static void createSchedules() {
        String[] hostNames = {"http://localhost:8085", "http://localhost:8083"};

        for (int i=100;i<100000;i++) {
            int hostIndex = i%2;
            ReqPojo request = new ReqPojo();
            request.setScheduleId("test_schedule_"+i);
            request.setCronExpr("*/1" + i%10 + " * * * * *");

            ReqPojo.MetaData metaData = new ReqPojo.MetaData();
            metaData.setConfigId("test_cfg_"+i);
            metaData.setDataSetId("test_ds_"+i);
            metaData.setEntityName("test_entity");
            metaData.setFlowName("test_flow_"+i);
            metaData.setTenant("test_tenant_"+i);
            metaData.setDataSetName("test_ds_name_"+i);
            metaData.setJobType("CUSTOM_RULE");
            metaData.setRunMode("ASYNC");
            metaData.setExecuteWithAlreadyRunning(false);

            request.setMetaData(metaData);

            String response = Unirest.post(hostNames[hostIndex]+"/scheduler/schedule/createOrUpdate")
                    .contentType("application/json")
                    .body(request)
                    .asString()
                    .getBody();

            System.out.println("Response for create request: " + response);
        }
    }
}

