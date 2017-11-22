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

    public final static Map<String, Object> GAUGE = new HashMap<>();

    static {
        // 人格评估量表
        GAUGE.put("中国大五人格问卷(简式版)(CBF-PI-B)", "gauge_1");
        GAUGE.put("中文形容词大五人格量表(简式版)", "gauge_2");
        GAUGE.put("中庸实践思维方式量表", "gauge_3");
        // 一般心理健康与行为问题量表
        GAUGE.put("90项症状清单(SCL-90)", "gauge_4");
        GAUGE.put("儿少心理健康量表(MHS-CA)", "gauge_5");
        GAUGE.put("Achenbach儿童行为量表(CBCL)", "gauge_6");
        GAUGE.put("长处和困难问卷(SDQ)", "gauge_7");
        GAUGE.put("初中学生攻击行为的主要心理特征问卷", "gauge_8");
        GAUGE.put("青春期性心理健康量表", "gauge_9");
        GAUGE.put("匹兹堡睡眠质量指数(PSQI)", "gauge_10");
        GAUGE.put("布鲁奈尔心境量表(中文版)(BRUMS-C)", "gauge_11");
        // 应激及相关行为量表
        GAUGE.put("生活事件量表(LES)", "gauge_12");
        GAUGE.put("中学生生活应激评定量表", "gauge_13");
        GAUGE.put("中学生应激源量表(SSMSS)", "gauge_14");
        GAUGE.put("大学生恋爱心理压力源量表", "gauge_15");
        GAUGE.put("中小学教师工作——家庭冲突问卷(WFCS)", "gauge_16");
        GAUGE.put("简易应对方式问卷", "gauge_17");
        GAUGE.put("应付方式问卷(CSQ)", "gauge_18");
        GAUGE.put("社会支持评定量表", "gauge_19");
        GAUGE.put("青少年社会支持量表", "gauge_20");
        GAUGE.put("青少年学习倦怠量表", "gauge_21");
        GAUGE.put("中国心理咨询与治疗专业人员枯竭量表", "gauge_22");
        GAUGE.put("青少年病理性互联网使用量表(APIUS)", "gauge_23");
        GAUGE.put("青少年上网成瘾自评量表", "gauge_24");
        GAUGE.put("中学生网络成瘾诊断量表", "gauge_25");
        GAUGE.put("IT企业员工工作成瘾问卷", "gauge_26");
        GAUGE.put("自杀态度问卷(SAQ)", "gauge_27");
        GAUGE.put("创伤后应激障碍检查表(PCL)", "gauge_28");
        // 精神病学临床量表
        GAUGE.put("老年精神评定量表(PAS)", "gauge_29");
        GAUGE.put("抑郁自评量表(SDS)", "gauge_30");
        GAUGE.put("流调用抑郁自评量表(CES-D)", "gauge_31");
        GAUGE.put("贝克抑郁自评量表(BDI)", "gauge_32");
        GAUGE.put("抑郁体验问卷(DEQ)", "gauge_33");
        GAUGE.put("焦虑自评量表(SAS)", "gauge_34");
        GAUGE.put("医院焦虑抑郁量表(HAD)", "gauge_1");
        GAUGE.put("状态-特质焦虑问卷(STAI)", "gauge_1");
        GAUGE.put("大学生强迫症状问卷  ", "gauge_1");
        // 家庭与人际关系量表
        GAUGE.put("家庭亲密度和适应性量表(中文版)(FACESⅡ-CV) ", "gauge_1");
        GAUGE.put("父母教养方式评价量表(EMBU)", "gauge_1");
        GAUGE.put("父亲在位问卷(中文修订版)(FPQ-CR)", "gauge_1");
        GAUGE.put("安全感量表(SQ)", "gauge_1");
        GAUGE.put("成人依恋量表(修订版)(AAS-R)", "gauge_1");
        GAUGE.put("分离体验量表(第2版)(DES-Ⅱ)", "gauge_1");
        GAUGE.put("人际信任量表(ITS)", "gauge_1");
        GAUGE.put("社交回避及苦恼量表(SAD)", "gauge_1");
        GAUGE.put("青少年学生疏离感量表(ASAS)", "gauge_1");
        GAUGE.put("汉区少数民族学生文化疏离感量表", "gauge_1");
        GAUGE.put("UCLA孤独量表", "gauge_1");
        GAUGE.put("儿童期虐待史自评量表(PRCA)", "gauge_1");
        // 生活质量与满意度量表
        GAUGE.put("儿童少年生活质量量表(QLSCA)", "gauge_1");
        GAUGE.put("儿少主观生活质量问卷(ISLQ)", "gauge_1");
        GAUGE.put("青少年学生生活满意度量表", "gauge_1");
        GAUGE.put("纽芬兰纪念大学幸福度量表(MUNSH)", "gauge_1");
        GAUGE.put("中国人婚姻质量问卷", "gauge_1");
        GAUGE.put("舒适状况量表(GCQ)", "gauge_1");
        // 自我意识与自尊量表"
        GAUGE.put("Piers-Harris儿童自我意识量表(PHCSS)", "gauge_1");
        GAUGE.put("核心自我评价量表(CSES)", "gauge_1");
        GAUGE.put("Rosenberg自尊量表(RSES)", "gauge_1");
        GAUGE.put("中学生二维自尊量表(修订版)(SLCS-R)", "gauge_1");
        // 学习与教育量表
        GAUGE.put("大学生学习动机问卷", "gauge_1");
        GAUGE.put("中学生自我导向学习倾向性量表(SDLRS)", "gauge_1");
        GAUGE.put("大学生一般学业情绪问卷", "gauge_1");
        GAUGE.put("学习障碍儿童筛查量表(PRS)", "gauge_1");
        GAUGE.put("儿童汉语阅读障碍量表(DCCC)", "gauge_1");
        GAUGE.put("中学生考试心理和行为问题症状自评量表(EMP)", "gauge_1");
        GAUGE.put("中学生感知的学校气氛问卷(PSCI-M)", "gauge_1");
        // 行为方式量表
        GAUGE.put("思维风格量表(TSI)", "gauge_1");
        GAUGE.put("归因方式问卷(ASQ)", "gauge_1");
        GAUGE.put("Aitken拖延问卷(API)", "gauge_1");
        GAUGE.put("大学生坚韧人格评定量表", "gauge_1");
        GAUGE.put("学龄前儿童活动调查表(PSAI)", "gauge_1");
        GAUGE.put("独处行为量表", "gauge_1");
        GAUGE.put("接纳及行动问卷(中文版)(AAQ-Ⅱ)", "gauge_1");
        // 人生价值 态度量表
        GAUGE.put("中文人生意义问卷(C-MLQ)", "gauge_1");
        GAUGE.put("目标追求的入世、出世量表", "gauge_1");
        GAUGE.put("中学生与其父母价值观差异问卷", "gauge_1");
        GAUGE.put("大学生公正世界信念量表", "gauge_1");
        GAUGE.put("多维完美主义问卷(MPS)", "gauge_1");
        GAUGE.put("中文Frost多维度完美主义问卷(CFMPS)", "gauge_1");
        GAUGE.put("消极完美主义问卷(ZNPQ)", "gauge_1");
        // 职业价值 态度量表
        GAUGE.put("职业延迟满足量表(ODGS)", "gauge_1");
        GAUGE.put("变革型领导问卷(TLQ)", "gauge_1");
        GAUGE.put("心理授权问卷", "gauge_1");
        GAUGE.put("大学生文科兴趣量表", "gauge_1");
    }
}
