package com.rpc.auth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rpc.auth.model.User;
import com.rpc.auth.service.AuthService;
import com.rpc.framework.base.Page;
import com.rpc.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/framework/applicationContext.xml")
public class AuthServiceTest {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceTest.class);

    @Autowired
    private AuthService userService;

    @Test
    public void paginateUser() {
        try {
            Page<User> page = new Page<User>();
            Map<String, Object> paramMap = new HashMap<String, Object>();

            Date endTime = DateUtil.getSystemDateTime();
            Date startTime = DateUtil.addDate(endTime, -100);

            paramMap.put("startTime", startTime);
            paramMap.put("endTime", endTime);
            page.setParamMap(paramMap);
            page = userService.paginateUser(page);
            if (page != null) {
                log.warn("#  taotalPage = {}", page.getTotalPage());
                log.warn("#  taotalCount = {}", page.getTotalCount());
                List<User> users = page.getResultList();
                for (User u : users) {
                    log.warn("# id={} , userName={}", u.getId(), u.getUserName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
