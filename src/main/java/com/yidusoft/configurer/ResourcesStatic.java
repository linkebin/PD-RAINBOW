package com.yidusoft.configurer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zcb on 2017/11/8.
 */
public  class  ResourcesStatic {
    public final   static Map<String,Object> OPERRESOURCES=new HashMap<>();
    static {
        //问卷的操作
        OPERRESOURCES.put("/questionnaire/addQuestionnaire","添加问卷");
        OPERRESOURCES.put("/questionnaire/updateQuestionnaire","修改问卷");
        OPERRESOURCES.put("/questionnaire/invalidQuestionnaire","问卷下架");
        OPERRESOURCES.put("/questionnaire/deleteQuestionnaire","删除问卷");
        OPERRESOURCES.put("/product/settings/add","添加套餐");
        OPERRESOURCES.put("/product/settings/update","修改套餐");
        OPERRESOURCES.put("/product/settings/deleteBacth","删除套餐");
    }
    public final   static Map<String,Object> GAUGE=new HashMap<>();
        static {
            GAUGE.put("90项症状清单(SCL90)","");
            GAUGE.put("儿少心理健康量表(MHS-CA)","");
            GAUGE.put("Achenbach儿童行为量表(CBCL)","");
            GAUGE.put("长处和困难问卷(SDQ)","");
            GAUGE.put("初中学生攻击行为的主要心理特征问卷","");
            GAUGE.put("青春期性心理健康量表","");
            GAUGE.put("匹兹堡睡眠质量指数(PSQI)","");
            GAUGE.put("生活事件量表(LES)","");
            GAUGE.put("中学生生活应激评定量表","");
            GAUGE.put("中学生应激源量表(SSMSS)","");
            GAUGE.put("大学生恋爱心理压力源量表","");
            GAUGE.put("中小学教师工作一家庭冲突问卷(WFCS)","");
            GAUGE.put("简易应对方式问卷","");
            GAUGE.put("应付方式问卷(CSQ)","");
            GAUGE.put("社会支持评定量表","");
            GAUGE.put("青少年社会支持量表","");
            GAUGE.put("青少年学习倦怠量表","");
            GAUGE.put("中国心理咨询与治疗专业人员枯竭量表","");
            GAUGE.put("青少年病理性互联网使用量表(APIUS)","");
            GAUGE.put("青少年上网成瘾自评量表","");
            GAUGE.put("中学生网络成瘾诊断量表","");
            GAUGE.put("IT企业员工工作成瘾问卷","");
            GAUGE.put("自杀态度问卷(SAQ)","");
            GAUGE.put("创伤后应激障碍检查表(PCL)","");
            GAUGE.put("老年精神评定量表(PAS)","");
            GAUGE.put("抑郁自评量表(SDS)","");
            GAUGE.put("流调用抑郁自评量表((2ES-D)","");
            GAUGE.put("贝克抑郁自评量表(BDI)","");
            GAUGE.put("抑郁体验问卷(DEQ)","");
            GAUGE.put("医院焦虑抑郁量表(HAD)","");
            GAUGE.put("焦虑自评量表(SAS)","");
            GAUGE.put("状态一特质焦虑问卷(STAI)","");
            GAUGE.put("大学生强迫症状问卷","");
            GAUGE.put("家庭亲密度和适应性量表中文版(FACESⅡ-CV)","");
            GAUGE.put("父母教养方式评价量表(EMBU)","");
            GAUGE.put("安全感量表(SQ)","");
            GAUGE.put("修订版成人依恋量表(AAS)","");
            GAUGE.put("分离体验量表第2版(DES-Ⅱ)","");
            GAUGE.put("人际信任量表(ITS)","");
            GAUGE.put("社交回避及苦恼量表(SAD)","");
            GAUGE.put("青少年学生疏离感量表(ASAS)","");
            GAUGE.put("汉区少数民族学生文化疏离感量表","");
            GAUGE.put("LICLA孤独量表","");
            GAUGE.put("儿童期虐待史自评量表(PRCA)","");
            GAUGE.put("儿童少年生活质量量表(QLSCA)","");
            GAUGE.put("儿少主观生活质量问卷(ISLQ)","");
            GAUGE.put("青少年学生生活满意度量表","");
            GAUGE.put("纽芬兰纪念大学幸福度量表(MUNSH)","");
            GAUGE.put("中国人婚姻质量问卷","");
            GAUGE.put("舒适状况量表((3CQ)","");
            GAUGE.put("Piers-fIarris儿童自我意识量表(Pf/Css)","");
            GAUGE.put("核心自我评价量表(CSES)","");
            GAUGE.put("Rosenl)erg自尊量表(RSES)","");
            GAUGE.put("大学生学习动机问卷","");
            GAUGE.put("中学生自我导向学习倾向性量表(SDLRS)","");
            GAUGE.put("大学生一般学业情绪问卷","");
            GAUGE.put("学习障碍儿童筛查量表(PRS)","");
            GAUGE.put("儿童汉语阅读障碍量表(DCCC)","");
            GAUGE.put("中学生考试心理和行为问题症状自评量表(EMP)","");
            GAUGE.put("中学生感知的学校气氛问卷(PSCI-M)","");
            GAUGE.put("思维风格量表(TSI)","");
            GAUGE.put("归因方式问卷(ASQ)","");
            GAUGE.put("Aitken拖延问卷(API)","");
            GAUGE.put("大学生坚韧人格评定量表","");
            GAUGE.put("学龄前儿童活动调查表(PSAI)","");
            GAUGE.put("中文人生意义问卷(C-MLQ)","");
            GAUGE.put("","");
            GAUGE.put("出世量表","");
            GAUGE.put("中学生与其父母价值观差异问卷","");
            GAUGE.put("大学生公正世界信念量表","");
            GAUGE.put("多维完美主义问卷(MPS)","");
            GAUGE.put("中文Frost多维度完美主义问卷((2FMPS)","");
            GAUGE.put("消极完美主义问卷(ZNPQ)","");
            GAUGE.put("职业延迟满足量表(()DGS)","");
            GAUGE.put("变革型领导问卷(TLQ)","");
            GAUGE.put("心理授权问卷","");
            GAUGE.put("大学生文科兴趣量表","");
        }
}
