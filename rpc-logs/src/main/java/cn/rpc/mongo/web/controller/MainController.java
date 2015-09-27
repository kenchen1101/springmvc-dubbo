package cn.rpc.mongo.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.rpc.mongo.common.utils.Page;
import cn.rpc.mongo.dto.BusiLogDto;
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

    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    @RequestMapping("/index")
    public String index(Model model) {
        log.warn("## rpc-logs index page.");
        Page<BusiLog> page = new Page<BusiLog>();
        page = rpcLogService.findBusiLogByPage(page, null);
        model.addAttribute("page", page);
        return "busi_log";
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String page(@ModelAttribute("busiLogDto") BusiLogDto busiLogDto, Model model) {
        log.warn("## rpc-logs index page.");
        Page<BusiLog> page = new Page<BusiLog>(busiLogDto.getCurrentPage());
        page = rpcLogService.findBusiLogByPage(page, busiLogDto);
        model.addAttribute("page", page);
        model.addAttribute("busiLog", busiLogDto);
        return "busi_log_page";
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
