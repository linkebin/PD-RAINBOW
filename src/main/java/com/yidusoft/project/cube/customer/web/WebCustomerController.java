package com.yidusoft.project.cube.customer.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yidusoft.project.business.domain.VisitorRegister;
import com.yidusoft.project.business.service.VisitorRegisterService;
import com.yidusoft.project.system.domain.SelectOption;
import com.yidusoft.project.system.service.SelectOptionService;
import org.json.JSONObject;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by smy on 2017/10/19.
 */
@Controller
@RequestMapping("/web/customer")
public class WebCustomerController {
    @Autowired
    private SelectOptionService selectOptionService;

    @Autowired
    private VisitorRegisterService visitorRegisterService;

    @RequestMapping("/customerinfodetail")
    public String customerinfodetail(String id,Model model){
        VisitorRegister visitorRegister = visitorRegisterService.findById(id);
        visitorRegister.setAddress(visitorRegister.getProvince()+visitorRegister.getCity()+visitorRegister.getDistrict()+visitorRegister.getAddress());

        String json = JSON.toJSONString(visitorRegister, SerializerFeature.WriteMapNullValue);

        Map<String,Object> map = JSON.parseObject(json,Map.class);

        religiousBelief(visitorRegister,map);
        educationLevel(visitorRegister,map);
        maritalStatus(visitorRegister,map);

        if (visitorRegister.getMotherDeathYourAge()==0){
            map.put("motherDeathYourAge","");
        }
        if (visitorRegister.getFatherDeathYourAge()==0){
            map.put("fatherDeathYourAge","");
        }

        if (visitorRegister.getParentalDivorceYourAge()==0){
            map.put("parentalDivorceYourAge","");
        }
        if (visitorRegister.getParentalSeparationYourAge()==0){
            map.put("parentalSeparationYourAge","");
        }
        model.addAttribute("vr",map);

        return "project/cube/customer/customer-info-detail";
    }


    public void maritalStatus(VisitorRegister visitorRegister,Map<String,Object> map){
        String str ="未婚";
        if (visitorRegister.getMaritalStatus()==2){
            str="已婚";
        }
        if (visitorRegister.getMaritalStatus()==3){
            str="已婚分居";
        }
        if (visitorRegister.getMaritalStatus()==4){
            str="再婚";
        }
        if (visitorRegister.getMaritalStatus()==5){
            str="离婚独居";
        }
        if (visitorRegister.getMaritalStatus()==6){
            str="丧偶独居";
        }
        map.put("maritalStatus",str);
    }
    public void educationLevel(VisitorRegister visitorRegister,Map<String,Object> map){
        String str ="小学";
        if (visitorRegister.getEducationLevel()==2){
            str="初中";
        }
        if (visitorRegister.getEducationLevel()==3){
            str="高中";
        }
        if (visitorRegister.getEducationLevel()==4){
            str="专科";
        }
        if (visitorRegister.getEducationLevel()==5){
            str="本科";
        }
        if (visitorRegister.getEducationLevel()==6){
            str="研究生";
        }
        if (visitorRegister.getEducationLevel()==7){
            str="博士";
        }

        map.put("educationLevel",str);
    }
    public void religiousBelief(VisitorRegister visitorRegister,Map<String,Object> map){
        String str ="没有";
        if (visitorRegister.getReligiousBelief()==1){
            str="基督教";
        }
        if (visitorRegister.getReligiousBelief()==2){
            str="佛教";
        }
        if (visitorRegister.getReligiousBelief()==3){
            str="道教";
        }
        if (visitorRegister.getReligiousBelief()==4){
            str="天主教";
        }
        if (visitorRegister.getReligiousBelief()==5){
            str="伊斯兰教";
        }
        if (visitorRegister.getReligiousBelief()==6){
            str="其他";
        }
        map.put("religiousBelief",str);
    }

    @RequestMapping("/customerList")
    public String customerList(){

        return "project/cube/customer/customerList";
    }

    @RequestMapping("/visitorRegister")
    public String visitorRegister(Model model,String creator){

        model.addAttribute("creator",creator);
        List<SelectOption> selectOptionList = selectOptionService.findSelectOptionByType("profession");
        model.addAttribute("selectOptionList",selectOptionList);

        return "project/cube/customer/visitor-register";
    }

    @RequestMapping("/checkIn")
    public String checkIn(Model model,String creator){

        model.addAttribute("creator",creator);
        List<SelectOption> selectOptionList = selectOptionService.findSelectOptionByType("profession");
        model.addAttribute("selectOptionList",selectOptionList);

        return "project/cube/customer/visitor-check-in";
    }
    @RequestMapping("/checkUpdate")
    public String checkUpdate(Model model,String id){
        model.addAttribute("id",id);
        List<SelectOption> selectOptionList = selectOptionService.findSelectOptionByType("profession");
        model.addAttribute("selectOptionList",selectOptionList);
        return "project/cube/customer/visitor-check-update";
    }

    @RequestMapping("/visitorUpdate")
    public String visitorUpdate(Model model,String id){

        model.addAttribute("id",id);
        List<SelectOption> selectOptionList = selectOptionService.findSelectOptionByType("profession");
        model.addAttribute("selectOptionList",selectOptionList);

        return "project/cube/customer/visitor-update";
    }

    @RequestMapping("/visitorInfo")
    public String visitorInfo(Model model,String id){

        model.addAttribute("id",id);
        VisitorRegister visitorRegister = visitorRegisterService.findById(id);
        model.addAttribute("visitorRegister",visitorRegister);
        List<SelectOption> selectOptionList =selectOptionService.findSelectOptionByType("goal");
        model.addAttribute("selectOptionList",selectOptionList);

        return "project/cube/customer/consultant";
    }

    //跳转选择问卷页面
    @RequestMapping("/getCheckQuestionnaire")
    public String getCheckQuestionnaire(){
        return "project/cube/customer/check-questionnaire";
    }

    //添加活动 跳转选择问卷页面
    @RequestMapping("/getCheckQuestionnaireForAct")
    public String getCheckQuestionnaireForAct(){
        return "project/cube/customer/check-questionnaire-activity";
    }

    @RequestMapping("/dna")
    public String dna(){
        return "project/cube/customer/costomer-dna";
    }

    /**
     * 跳转到客户统计页面
     * @return
     */
    @RequestMapping("/count")
    public String customerCount(){
        return "project/cube/customer/customer-count";
    }
    /**
     * 跳转到客户问卷分析页面
     * @return
     */
    @RequestMapping("/getCustomerAnalysis")
    public String getCustomerAnalysis(String userId,Model model){
        model.addAttribute("userId",userId);
        return "project/cube/customer/customer-analysis";
    }

    /**
     * 跳转到客户统计页面
     * @return
     */
    @RequestMapping("/visitorCount")
    public String visitorCount(Model model){
        List<SelectOption> selectOptionList =selectOptionService.findSelectOptionByType("goal");
        model.addAttribute("selectOptionList",selectOptionList);
        return "project/cube/customer/visitor-count";
    }

    @RequestMapping("/visitorCountBack")
    public String visitorCountBack(Model model){
        List<SelectOption> selectOptionList =selectOptionService.findSelectOptionByType("goal");
        model.addAttribute("selectOptionList",selectOptionList);
        return "project/cube/customer/visitor-count-back";
    }

    @RequestMapping("/testCount")
    public String testCount(){
        return "project/cube/customer/count-test";
    }

    @RequestMapping("/certificationData")
    public String certificationData(){
        return "project/cube/customer/costomer-certification-dna";
    }
}
