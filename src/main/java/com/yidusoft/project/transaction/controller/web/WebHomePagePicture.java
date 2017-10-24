package com.yidusoft.project.transaction.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by L on 2017/10/24.
 */
@Controller
@RequestMapping("/web/homePage")
public class WebHomePagePicture {
    @RequestMapping("/linkList")
    public String linkList(){
        return "project/transaction/homePagePicture/homePagePictureList";
    }
}
