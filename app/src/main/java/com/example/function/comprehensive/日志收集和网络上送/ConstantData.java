package com.example.function.comprehensive.日志收集和网络上送;

public interface ConstantData {
   String SRNO_SERVER = "http://121.199.47.32:8080/SRnOWeb";
    String SRNO_LOG_STRUTS2 = "/logger_dealFromPos.action";
    int SUCC = 0;
    int PACK_ERROR = 1;
    int WORKFLOW_ERROR = 2;
     int NET_ERROR = 3;
     int FINISH=4;
     int OBTAIN_LOG=5;
     int COMMUNICATION=6;
}
