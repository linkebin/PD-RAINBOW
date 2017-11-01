package com.yidusoft.project.business.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "visitor_register")
public class VisitorRegister {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 来访者姓名
     */
    @Column(name = "visitor_name")
    private String visitorName;

    /**
     * 来访者编号
     */
    @Column(name = "visitor_code")
    private String visitorCode;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 职业
     */
    private String occupation;

    /**
     * 身份证
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 血型
     */
    @Column(name = "blood_type")
    private String bloodType;

    /**
     * 民族
     */
    private String nation;

    /**
     * 地址
     */
    private String address;

    /**
     * 紧急联系人的关系
     */
    @Column(name = "exigence_contacts")
    private String exigenceContacts;

    /**
     * 紧急联系人的电话
     */
    @Column(name = "exigence_phone")
    private String exigencePhone;

    /**
     * 婚姻状况(1未婚 2已婚 3分居 4再婚 5离婚独居 6丧偶独居)
     */
    @Column(name = "marital_status")
    private Integer maritalStatus;

    /**
     * 结婚年数
     */
    @Column(name = "marry_total")
    private Integer marryTotal;

    /**
     * 孩子性别
     */
    @Column(name = "child_sex")
    private Integer childSex;

    /**
     * 孩子年龄
     */
    @Column(name = "child_age")
    private Integer childAge;

    /**
     * 教育程度（1小学 2初中 3高中 4专科 5本科 6研究生 7博士）
     */
    @Column(name = "education_level")
    private Integer educationLevel;

    /**
     * 宗教信仰
     */
    @Column(name = "Religious_belief")
    private Integer religiousBelief;

    /**
     * 父亲的年龄
     */
    @Column(name = "father_age")
    private Integer fatherAge;

    /**
     * 父亲去世时你的年龄
     */
    @Column(name = "father_death_your_age")
    private Integer fatherDeathYourAge;

    /**
     * 父亲的学历
     */
    @Column(name = "father_education")
    private String fatherEducation;

    /**
     * 父亲的职业
     */
    @Column(name = "father_occupation")
    private String fatherOccupation;

    /**
     * 母亲的年龄
     */
    @Column(name = "mother_age")
    private Integer motherAge;

    /**
     * 母亲去世时你的年龄
     */
    @Column(name = "mother_death_your_age")
    private Integer motherDeathYourAge;

    /**
     * 母亲的学历
     */
    @Column(name = "mother_education")
    private String motherEducation;

    /**
     * 母亲的职业
     */
    @Column(name = "mother_occupation")
    private String motherOccupation;

    /**
     * 父母分居那时你的年龄
     */
    @Column(name = "parental_separation_your_age")
    private Integer parentalSeparationYourAge;

    /**
     * 父母离婚那时你的年龄
     */
    @Column(name = "parental_divorce_your_age")
    private Integer parentalDivorceYourAge;

    /**
     * 兄弟姐妹人数
     */
    @Column(name = "brothers_sisters_total")
    private Integer brothersSistersTotal;

    /**
     * 排行第几
     */
    @Column(name = "brothers_sisters_ranking")
    private Integer brothersSistersRanking;

    /**
     * 以前进行过心理咨询吗
     */
    @Column(name = "psychological_counseling")
    private Integer psychologicalCounseling;

    /**
     * 做过咨询是多少次
     */
    @Column(name = "counseling_total")
    private Integer counselingTotal;

    /**
     * 做过咨询的地址
     */
    @Column(name = "counseling_address")
    private String counselingAddress;

    /**
     * 距现在多少天
     */
    @Column(name = "away_now_total")
    private Integer awayNowTotal;

    /**
     * 是否因心理问题就医(0 没有 1有)
     */
    @Column(name = "medical_treatment")
    private Integer medicalTreatment;

    /**
     * 诊断结果
     */
    @Column(name = "diagnosis_result")
    private String diagnosisResult;

    /**
     * 是否慢性疾病,家族病史和过敏史(0没有 1有)
     */
    @Column(name = "medical_history")
    private Integer medicalHistory;

    /**
     * 具体情况
     */
    @Column(name = "specific_circumstances")
    private String specificCircumstances;

    /**
     * 曾长期服用过何种药物
     */
    @Column(name = "Long_medication")
    private Integer longMedication;

    /**
     * 药物不良症状
     */
    @Column(name = "adverse_drug_symptoms")
    private String adverseDrugSymptoms;

    /**
     * 填写日期
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    /**
     * 咨询师id
     */
    private String creator;


    /**
     * 是否删除
     */
    private Integer deleted;

    @Column(name = "income")
    private String income;
    @Column(name = "email")
    private String email;

    @Column(name = "native_place")
    private String nativePlace;

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    /**
     * 目前与健康有关的疾病及所接收的治疗
     */
    @Column(name = "treatment_condition")
    private String treatmentCondition;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return ID_
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取来访者姓名
     *
     * @return visitor_name - 来访者姓名
     */
    public String getVisitorName() {
        return visitorName;
    }

    /**
     * 设置来访者姓名
     *
     * @param visitorName 来访者姓名
     */
    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    /**
     * 获取来访者编号
     *
     * @return visitor_code - 来访者编号
     */
    public String getVisitorCode() {
        return visitorCode;
    }

    /**
     * 设置来访者编号
     *
     * @param visitorCode 来访者编号
     */
    public void setVisitorCode(String visitorCode) {
        this.visitorCode = visitorCode;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取出生日期
     *
     * @return birthday - 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取职业
     *
     * @return occupation - 职业
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * 设置职业
     *
     * @param occupation 职业
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * 获取身份证
     *
     * @return id_card - 身份证
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置身份证
     *
     * @param idCard 身份证
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取血型
     *
     * @return blood_type - 血型
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * 设置血型
     *
     * @param bloodType 血型
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * 获取民族
     *
     * @return nation - 民族
     */
    public String getNation() {
        return nation;
    }

    /**
     * 设置民族
     *
     * @param nation 民族
     */
    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取紧急联系人的关系
     *
     * @return exigence_contacts - 紧急联系人的关系
     */
    public String getExigenceContacts() {
        return exigenceContacts;
    }

    /**
     * 设置紧急联系人的关系
     *
     * @param exigenceContacts 紧急联系人的关系
     */
    public void setExigenceContacts(String exigenceContacts) {
        this.exigenceContacts = exigenceContacts;
    }

    /**
     * 获取紧急联系人的电话
     *
     * @return exigence_phone - 紧急联系人的电话
     */
    public String getExigencePhone() {
        return exigencePhone;
    }

    /**
     * 设置紧急联系人的电话
     *
     * @param exigencePhone 紧急联系人的电话
     */
    public void setExigencePhone(String exigencePhone) {
        this.exigencePhone = exigencePhone;
    }

    /**
     * 获取婚姻状况(1未婚 2已婚 3分居 4再婚 5离婚独居 6丧偶独居)
     *
     * @return marital_status - 婚姻状况(1未婚 2已婚 3分居 4再婚 5离婚独居 6丧偶独居)
     */
    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * 设置婚姻状况(1未婚 2已婚 3分居 4再婚 5离婚独居 6丧偶独居)
     *
     * @param maritalStatus 婚姻状况(1未婚 2已婚 3分居 4再婚 5离婚独居 6丧偶独居)
     */
    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * 获取结婚年数
     *
     * @return marry_total - 结婚年数
     */
    public Integer getMarryTotal() {
        return marryTotal;
    }

    /**
     * 设置结婚年数
     *
     * @param marryTotal 结婚年数
     */
    public void setMarryTotal(Integer marryTotal) {
        this.marryTotal = marryTotal;
    }

    /**
     * 获取孩子性别
     *
     * @return child_sex - 孩子性别
     */
    public Integer getChildSex() {
        return childSex;
    }

    /**
     * 设置孩子性别
     *
     * @param childSex 孩子性别
     */
    public void setChildSex(Integer childSex) {
        this.childSex = childSex;
    }

    /**
     * 获取孩子年龄
     *
     * @return child_age - 孩子年龄
     */
    public Integer getChildAge() {
        return childAge;
    }

    /**
     * 设置孩子年龄
     *
     * @param childAge 孩子年龄
     */
    public void setChildAge(Integer childAge) {
        this.childAge = childAge;
    }

    /**
     * 获取教育程度（1小学 2初中 3高中 4专科 5本科 6研究生 7博士）
     *
     * @return education_level - 教育程度（1小学 2初中 3高中 4专科 5本科 6研究生 7博士）
     */
    public Integer getEducationLevel() {
        return educationLevel;
    }

    /**
     * 设置教育程度（1小学 2初中 3高中 4专科 5本科 6研究生 7博士）
     *
     * @param educationLevel 教育程度（1小学 2初中 3高中 4专科 5本科 6研究生 7博士）
     */
    public void setEducationLevel(Integer educationLevel) {
        this.educationLevel = educationLevel;
    }

    /**
     * 获取宗教信仰
     *
     * @return Religious_belief - 宗教信仰
     */
    public Integer getReligiousBelief() {
        return religiousBelief;
    }

    /**
     * 设置宗教信仰
     *
     * @param religiousBelief 宗教信仰
     */
    public void setReligiousBelief(Integer religiousBelief) {
        this.religiousBelief = religiousBelief;
    }

    /**
     * 获取父亲的年龄
     *
     * @return father_age - 父亲的年龄
     */
    public Integer getFatherAge() {
        return fatherAge;
    }

    /**
     * 设置父亲的年龄
     *
     * @param fatherAge 父亲的年龄
     */
    public void setFatherAge(Integer fatherAge) {
        this.fatherAge = fatherAge;
    }

    /**
     * 获取父亲去世时你的年龄
     *
     * @return father_death_your_age - 父亲去世时你的年龄
     */
    public Integer getFatherDeathYourAge() {
        return fatherDeathYourAge;
    }

    /**
     * 设置父亲去世时你的年龄
     *
     * @param fatherDeathYourAge 父亲去世时你的年龄
     */
    public void setFatherDeathYourAge(Integer fatherDeathYourAge) {
        this.fatherDeathYourAge = fatherDeathYourAge;
    }

    /**
     * 获取父亲的学历
     *
     * @return father_education - 父亲的学历
     */
    public String getFatherEducation() {
        return fatherEducation;
    }

    /**
     * 设置父亲的学历
     *
     * @param fatherEducation 父亲的学历
     */
    public void setFatherEducation(String fatherEducation) {
        this.fatherEducation = fatherEducation;
    }

    /**
     * 获取父亲的职业
     *
     * @return father_occupation - 父亲的职业
     */
    public String getFatherOccupation() {
        return fatherOccupation;
    }

    /**
     * 设置父亲的职业
     *
     * @param fatherOccupation 父亲的职业
     */
    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    /**
     * 获取母亲的年龄
     *
     * @return mother_age - 母亲的年龄
     */
    public Integer getMotherAge() {
        return motherAge;
    }

    /**
     * 设置母亲的年龄
     *
     * @param motherAge 母亲的年龄
     */
    public void setMotherAge(Integer motherAge) {
        this.motherAge = motherAge;
    }

    /**
     * 获取母亲去世时你的年龄
     *
     * @return mother_death_your_age - 母亲去世时你的年龄
     */
    public Integer getMotherDeathYourAge() {
        return motherDeathYourAge;
    }

    /**
     * 设置母亲去世时你的年龄
     *
     * @param motherDeathYourAge 母亲去世时你的年龄
     */
    public void setMotherDeathYourAge(Integer motherDeathYourAge) {
        this.motherDeathYourAge = motherDeathYourAge;
    }

    /**
     * 获取母亲的学历
     *
     * @return mother_education - 母亲的学历
     */
    public String getMotherEducation() {
        return motherEducation;
    }

    /**
     * 设置母亲的学历
     *
     * @param motherEducation 母亲的学历
     */
    public void setMotherEducation(String motherEducation) {
        this.motherEducation = motherEducation;
    }

    /**
     * 获取母亲的职业
     *
     * @return mother_occupation - 母亲的职业
     */
    public String getMotherOccupation() {
        return motherOccupation;
    }

    /**
     * 设置母亲的职业
     *
     * @param motherOccupation 母亲的职业
     */
    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    /**
     * 获取父母分居那时你的年龄
     *
     * @return parental_separation_your_age - 父母分居那时你的年龄
     */
    public Integer getParentalSeparationYourAge() {
        return parentalSeparationYourAge;
    }

    /**
     * 设置父母分居那时你的年龄
     *
     * @param parentalSeparationYourAge 父母分居那时你的年龄
     */
    public void setParentalSeparationYourAge(Integer parentalSeparationYourAge) {
        this.parentalSeparationYourAge = parentalSeparationYourAge;
    }

    /**
     * 获取父母离婚那时你的年龄
     *
     * @return parental_divorce_your_age - 父母离婚那时你的年龄
     */
    public Integer getParentalDivorceYourAge() {
        return parentalDivorceYourAge;
    }

    /**
     * 设置父母离婚那时你的年龄
     *
     * @param parentalDivorceYourAge 父母离婚那时你的年龄
     */
    public void setParentalDivorceYourAge(Integer parentalDivorceYourAge) {
        this.parentalDivorceYourAge = parentalDivorceYourAge;
    }

    /**
     * 获取兄弟姐妹人数
     *
     * @return brothers_sisters_total - 兄弟姐妹人数
     */
    public Integer getBrothersSistersTotal() {
        return brothersSistersTotal;
    }

    /**
     * 设置兄弟姐妹人数
     *
     * @param brothersSistersTotal 兄弟姐妹人数
     */
    public void setBrothersSistersTotal(Integer brothersSistersTotal) {
        this.brothersSistersTotal = brothersSistersTotal;
    }

    /**
     * 获取排行第几
     *
     * @return brothers_sisters_ranking - 排行第几
     */
    public Integer getBrothersSistersRanking() {
        return brothersSistersRanking;
    }

    /**
     * 设置排行第几
     *
     * @param brothersSistersRanking 排行第几
     */
    public void setBrothersSistersRanking(Integer brothersSistersRanking) {
        this.brothersSistersRanking = brothersSistersRanking;
    }

    /**
     * 获取以前进行过心理咨询吗
     *
     * @return psychological_counseling - 以前进行过心理咨询吗
     */
    public Integer getPsychologicalCounseling() {
        return psychologicalCounseling;
    }

    /**
     * 设置以前进行过心理咨询吗
     *
     * @param psychologicalCounseling 以前进行过心理咨询吗
     */
    public void setPsychologicalCounseling(Integer psychologicalCounseling) {
        this.psychologicalCounseling = psychologicalCounseling;
    }

    /**
     * 获取做过咨询是多少次
     *
     * @return counseling_total - 做过咨询是多少次
     */
    public Integer getCounselingTotal() {
        return counselingTotal;
    }

    /**
     * 设置做过咨询是多少次
     *
     * @param counselingTotal 做过咨询是多少次
     */
    public void setCounselingTotal(Integer counselingTotal) {
        this.counselingTotal = counselingTotal;
    }

    /**
     * 获取做过咨询的地址
     *
     * @return counseling_address - 做过咨询的地址
     */
    public String getCounselingAddress() {
        return counselingAddress;
    }

    /**
     * 设置做过咨询的地址
     *
     * @param counselingAddress 做过咨询的地址
     */
    public void setCounselingAddress(String counselingAddress) {
        this.counselingAddress = counselingAddress;
    }

    /**
     * 获取距现在多少天
     *
     * @return away_now_total - 距现在多少天
     */
    public Integer getAwayNowTotal() {
        return awayNowTotal;
    }

    /**
     * 设置距现在多少天
     *
     * @param awayNowTotal 距现在多少天
     */
    public void setAwayNowTotal(Integer awayNowTotal) {
        this.awayNowTotal = awayNowTotal;
    }

    /**
     * 获取是否因心理问题就医(0 没有 1有)
     *
     * @return medical_treatment - 是否因心理问题就医(0 没有 1有)
     */
    public Integer getMedicalTreatment() {
        return medicalTreatment;
    }

    /**
     * 设置是否因心理问题就医(0 没有 1有)
     *
     * @param medicalTreatment 是否因心理问题就医(0 没有 1有)
     */
    public void setMedicalTreatment(Integer medicalTreatment) {
        this.medicalTreatment = medicalTreatment;
    }

    /**
     * 获取诊断结果
     *
     * @return diagnosis_result - 诊断结果
     */
    public String getDiagnosisResult() {
        return diagnosisResult;
    }

    /**
     * 设置诊断结果
     *
     * @param diagnosisResult 诊断结果
     */
    public void setDiagnosisResult(String diagnosisResult) {
        this.diagnosisResult = diagnosisResult;
    }

    /**
     * 获取是否慢性疾病,家族病史和过敏史(0没有 1有)
     *
     * @return medical_history - 是否慢性疾病,家族病史和过敏史(0没有 1有)
     */
    public Integer getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * 设置是否慢性疾病,家族病史和过敏史(0没有 1有)
     *
     * @param medicalHistory 是否慢性疾病,家族病史和过敏史(0没有 1有)
     */
    public void setMedicalHistory(Integer medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    /**
     * 获取具体情况
     *
     * @return specific_circumstances - 具体情况
     */
    public String getSpecificCircumstances() {
        return specificCircumstances;
    }

    /**
     * 设置具体情况
     *
     * @param specificCircumstances 具体情况
     */
    public void setSpecificCircumstances(String specificCircumstances) {
        this.specificCircumstances = specificCircumstances;
    }

    /**
     * 获取曾长期服用过何种药物
     *
     * @return Long_medication - 曾长期服用过何种药物
     */
    public Integer getLongMedication() {
        return longMedication;
    }

    /**
     * 设置曾长期服用过何种药物
     *
     * @param longMedication 曾长期服用过何种药物
     */
    public void setLongMedication(Integer longMedication) {
        this.longMedication = longMedication;
    }

    /**
     * 获取药物不良症状
     *
     * @return adverse_drug_symptoms - 药物不良症状
     */
    public String getAdverseDrugSymptoms() {
        return adverseDrugSymptoms;
    }

    /**
     * 设置药物不良症状
     *
     * @param adverseDrugSymptoms 药物不良症状
     */
    public void setAdverseDrugSymptoms(String adverseDrugSymptoms) {
        this.adverseDrugSymptoms = adverseDrugSymptoms;
    }

    /**
     * 获取填写日期
     *
     * @return create_time - 填写日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置填写日期
     *
     * @param createTime 填写日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取咨询师id
     *
     * @return creator - 咨询师id
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置咨询师id
     *
     * @param creator 咨询师id
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取是否删除
     *
     * @return deleted - 是否删除
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除
     *
     * @param deleted 是否删除
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取目前与健康有关的疾病及所接收的治疗
     *
     * @return treatment_condition - 目前与健康有关的疾病及所接收的治疗
     */
    public String getTreatmentCondition() {
        return treatmentCondition;
    }

    /**
     * 设置目前与健康有关的疾病及所接收的治疗
     *
     * @param treatmentCondition 目前与健康有关的疾病及所接收的治疗
     */
    public void setTreatmentCondition(String treatmentCondition) {
        this.treatmentCondition = treatmentCondition;
    }
}