package com.fun.simple;

import com.google.gson.Gson;
import lombok.Data;
import org.junit.Test;

public class BeanTest {

    @Data
    public class OutMsg {

        private String msgId;
        private String topic;
        private Msg msg;
    }

    @Data
    class Msg {
        private String biz;
        private Object throughData;
    }

    @Data
    class RenameData {
        private String deviceId;
        private String newName;
    }

    @Test
    public void test() {

        String str = "{\n" +
                "  \"msgId\": \"\",\n" +
                "  \"topic\": \"lc_through_msg\",\n" +
                "  \"key\": \"\",\n" +
                "  \"ts\": \"\",\n" +
                "  \"msg\": {\n" +
                "    \"biz\": \"device-rename\",\n" +
                "    \"throughData\": {\n" +
                "      \"deviceId\": \"dehxy2sd\",\n" +
                "      \"newName\": \"新名称\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        Gson gson = new Gson();
        OutMsg outMsg = gson.fromJson(str, OutMsg.class);

        Object tdata = outMsg.getMsg().getThroughData();

        String throughData = gson.toJson(tdata);

        RenameData renameData = gson.fromJson(throughData, RenameData.class);
        System.out.println(renameData.getDeviceId());
    }
}


