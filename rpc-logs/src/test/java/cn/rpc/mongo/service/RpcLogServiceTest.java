package cn.rpc.mongo.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.rpc.mongo.entity.RpcLogEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class RpcLogServiceTest {

    private static final Logger log = LoggerFactory.getLogger(RpcLogServiceTest.class);

    @Autowired
    private RpcLogService rpcLogService;

    @Test
    public void findRpcLogEntityAll() {
        try {
            List<RpcLogEntity> rpcLogEntitys = rpcLogService.findRpcLogEntityAll();
            for (RpcLogEntity r : rpcLogEntitys) {
                log.warn("## {}", JSON.toJSON(r));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
