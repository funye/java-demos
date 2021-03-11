package com.fun.dsjob;

import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.JobAPIFactory;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.JobConfigurationAPI;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.JobOperateAPI;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

public class EsJobLiteDemo {

    public static void main(String[] args) {
        new ScheduleJobBootstrap(createRegistryCenter(), new MyJob(), createJobConfiguration()).schedule();

        JobOperateAPI jobOperateAPI = JobAPIFactory.createJobOperateAPI("localhost:2181", "my-job", "");
//        jobOperateAPI.disable("","");
        JobConfigurationAPI configAPI = JobAPIFactory.createJobConfigurationAPI("localhost:2181", "my-job", "");
//        configAPI.updateJobConfiguration(null);

    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2181", "my-job"));
        regCenter.init();

        return regCenter;
    }

    private static JobConfiguration createJobConfiguration() {
        // 创建作业配置
        // 0 0 1 * * ? 每天1点执行
        // 0 0/5 * * * ? * 没5分钟执行一次
        JobConfiguration jobConfig = JobConfiguration.newBuilder("MyJob", 3)
                .cron("0 0/3 * * * ? *")
                .overwrite(true)
                .build();
        return jobConfig;
    }


    static class MyJob implements SimpleJob {

        @Override
        public void execute(ShardingContext context) {
            System.out.println("start execute......" + Thread.currentThread().getName());
            switch (context.getShardingItem()) {
                case 0:
                    System.out.println("task shareIndex 0");
                    break;
                case 1:
                    System.out.println("task shareIndex 1");
                    break;
                case 2:
                    System.out.println("task shareIndex 2");
                    break;
                // case n: ...
            }
            System.out.println("end execute......" + Thread.currentThread().getName());
        }
    }
}
