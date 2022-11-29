package com.orange.graduation.config.kfk;


import lombok.Data;

/**
 * @author orange.zhang
 * @date 2022/11/20 20:04
 */
@Data
public class KafkaProps {
    public final static String BOOTSTRAP_SERVERS_CONFIG = "43.138.217.93:9092";
    public final static String TEST_TOPIC = "orange";
    public final static String TEST_GROUP_ID = "orange";
    public final static int[]  TEST_TOPIC_PARTITIONS = {0};
    public static int maxPollRecords = 500;
    public static int fetchMaxWaitMs = 10;
    public static int fetchMinBytes = 182040000;
    public static int requestTimeoutMs = 30000;
    public static int sessionTimeoutMs = 10000;
    public static int connectionsMaxIdleMs = 600000;
}
