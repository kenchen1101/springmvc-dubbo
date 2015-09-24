package cn.rpc.mongo.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.rpc.mongo.common.utils.Page;
import cn.rpc.mongo.common.utils.date.DateUtil;
import cn.rpc.mongo.entity.BusiLog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class BusiLogServiceTest {

    private static final Logger log = LoggerFactory.getLogger(BusiLogServiceTest.class);

    @Autowired
    private BusiLogService rpcLogService;

    @Test
    public void findBusiLogByPage() {
        try {
            Page<BusiLog> page = new Page<BusiLog>();
            Date begin = DateUtil.stirngToDate("2015-09-24 14:33:49.590", DateUtil.fm_yyyy_MM_dd_HHmmssSSS);
            Date end = DateUtil.stirngToDate("2015-09-24 14:33:49.610", DateUtil.fm_yyyy_MM_dd_HHmmssSSS);
            String system = "rpc-server";
            String level = "";
            String threadName = "";
            String logName = "";
            String messgae = "";
            page = rpcLogService.findBusiLogByPage(page, begin, end, system, level, threadName, logName, messgae);
            for (BusiLog r : page.getResultList()) {
                // log.warn("## {} , {} , {}", r.getId(), DateUtil.transferLongToString(r.getTimeStamp()), r.getMessage());
                log.warn("## {} , {} ,  {}", r.getId(), DateUtil.dateToString(r.getTimeStamp(), DateUtil.fm_yyyy_MM_dd_HHmmssSSS), r.getLogName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findBusiLogAll() {
        try {
            List<BusiLog> rpcLogEntitys = rpcLogService.findBusiLogAll();
            for (BusiLog r : rpcLogEntitys) {
                // log.warn("## {} , {} , {} ", r.getId(), DateUtil.transferLongToString(r.getTimeStamp()), r.getLogName());
                log.warn("## {} , {} ,  {}", r.getId(), DateUtil.dateToString(r.getTimeStamp(), DateUtil.fm_yyyy_MM_dd_HHmmssSSS), r.getLogName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
