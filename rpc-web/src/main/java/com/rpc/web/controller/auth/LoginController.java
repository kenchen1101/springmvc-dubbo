package com.rpc.web.controller.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.rpc.auth.dto.UserDto;
import com.rpc.auth.model.User;
import com.rpc.common.WebUtil;
import com.rpc.web.validator.UserValidator;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(@ModelAttribute("userCommand") UserDto userCommand, BindingResult result) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            log.error("# 非法登录系统，请先登录。");
            return "login";
        }

        new UserValidator(UserValidator.LOGIN).validate(userCommand, result);
        User user = WebUtil.getUser();
        if (user.getIsDel() != 0) {
            result.reject("user.lock.error");
            log.error("## v  , {}", JSON.toJSONString(result.getAllErrors()));
            subject.logout();
            return "login";
        }

        return "redirect:/index";
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@ModelAttribute("userCommand") UserDto userCommand, BindingResult result) {
        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            result.reject("user.login.error");
            log.error("## 登录失败  , {}", JSON.toJSONString(result.getAllErrors()));
            return "login";
        }

        new UserValidator(UserValidator.LOGIN).validate(userCommand, result);
        User user = WebUtil.getUser();
        if (user.getIsDel() != 0) {
            result.reject("user.lock.error");
            log.error("## 用户已经失效，无法登录  , {}", JSON.toJSONString(result.getAllErrors()));
            subject.logout();
            return "login";
        }

        return "redirect:/index";
    }

}
