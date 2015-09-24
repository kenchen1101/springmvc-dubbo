package cn.rpc.mongo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.rpc.mongo.entity.BusiLog;
import cn.rpc.mongo.service.BusiLogService;

/**
 * @author Vincent.wang
 *
 */
@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private BusiLogService rpcLogService;

    @RequestMapping("/index")
    public String index(Model model) {
        log.warn("## springmvc-mongodb index page.");
        List<BusiLog> rpcLogs = rpcLogService.findBusiLogAll();
        model.addAttribute("rpcLogs", rpcLogs);
        return "index";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String error403() {
        return "/common/403";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String error404() {
        return "/common/404";
    }

}
