package cn.rpc.mongo.service;

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
import cn.rpc.mongo.dto.BusiLogDto;
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
            BusiLogDto dto = new BusiLogDto();
            dto.setBeginTime(DateUtil.stirngToDate("2015/9/25 2:13:04", "yyyy/MM/dd HH:mm:ss"));
            dto.setEndTime(DateUtil.stirngToDate("2015/9/25 2:13:08", "yyyy/MM/dd HH:mm:ss"));
            dto.setSystemName("rpc-server");
            dto.setThreadName("");
            dto.setLogName("");
            dto.setMessage("");
            page = rpcLogService.findBusiLogByPage(page, dto);
            for (BusiLog r : page.getResultList()) {
                log.warn("## {} , {} ,  {}", r.getId(), DateUtil.dateToString(r.getCreateTime(), DateUtil.fm_yyyy_MM_dd_HHmmssSSS), r.getLogName());
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
                log.warn("## {} , {} ,  {}", r.getId(), DateUtil.dateToString(r.getCreateTime(), DateUtil.fm_yyyy_MM_dd_HHmmssSSS), r.getLogName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
