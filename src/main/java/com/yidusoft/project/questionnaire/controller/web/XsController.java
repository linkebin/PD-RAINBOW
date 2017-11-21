package com.yidusoft.project.questionnaire.controller.web;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.service.GaugeService;
import com.yidusoft.utils.ExcelRead;
import com.yidusoft.utils.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by smy on 2017/11/21.
 */
@RestController
public class XsController {

    @Autowired
    private GaugeService gaugeService;

    @PostMapping(value="/readExcel")
    public Result readExcel(@RequestParam(value="excelFile") MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException {
        if(file == null){
            return ResultGenerator.genFailResult("文件为空");
        }
        String name = file.getOriginalFilename();
        long size = file.getSize();
        if(name == null || ExcelUtil.EMPTY.equals(name) && size==0){
            return ResultGenerator.genFailResult("文件为空");
        }
        //读取量表
        List<ArrayList<String>> lb = new ExcelRead().readExcel(file,3,3);

        //读取量表问题
        List<ArrayList<String>> wt = new ExcelRead().readExcel(file,6,ExcelUtil.getExcelTotalRows(file));

        return gaugeService.excelImportAdd(lb,wt);
    }
}
