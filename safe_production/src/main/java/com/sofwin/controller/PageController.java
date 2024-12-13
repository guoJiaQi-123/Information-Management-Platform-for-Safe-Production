package com.sofwin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    //后台页面访问
    @RequestMapping("/backstage/{page}")
    public String showBackStagePage(@PathVariable String page) {
        return "/backstage/" + page;
    }

    // 忽略访问项目logo
    @RequestMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }

}
