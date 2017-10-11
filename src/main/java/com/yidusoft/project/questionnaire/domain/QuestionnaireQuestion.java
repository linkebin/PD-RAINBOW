package com.yidusoft.project.questionnaire.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "questionnaire_question")
public class QuestionnaireQuestion {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否删除  0 否 1 是
     */
    private Integer deleted;

    /**
     * 问题编号
     */
    @Column(name = "question_code")
    private String questionCode;

    /**
     * 答案
     */
    private String answer;

    /**
     * 选项
     */
    @Column(name = "option_answer")
    private String optionAnswer;

    /**
     * 选项对应分数
     */
    @Column(name = "option_score")
    private String optionScore;

    /**
     * 类型
     */
    @Column(name = "question_type")
    private Integer questionType;

    /**
     * 问题内容
     */
    @Column(name = "question_content")
    private String questionContent;

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
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否删除  0 否 1 是
     *
     * @return deleted - 是否删除  0 否 1 是
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除  0 否 1 是
     *
     * @param deleted 是否删除  0 否 1 是
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取问题编号
     *
     * @return question_code - 问题编号
     */
    public String getQuestionCode() {
        return questionCode;
    }

    /**
     * 设置问题编号
     *
     * @param questionCode 问题编号
     */
    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    /**
     * 获取答案
     *
     * @return answer - 答案
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 设置答案
     *
     * @param answer 答案
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 获取选项
     *
     * @return option_answer - 选项
     */
    public String getOptionAnswer() {
        return optionAnswer;
    }

    /**
     * 设置选项
     *
     * @param optionAnswer 选项
     */
    public void setOptionAnswer(String optionAnswer) {
        this.optionAnswer = optionAnswer;
    }

    /**
     * 获取选项对应分数
     *
     * @return option_score - 选项对应分数
     */
    public String getOptionScore() {
        return optionScore;
    }

    /**
     * 设置选项对应分数
     *
     * @param optionScore 选项对应分数
     */
    public void setOptionScore(String optionScore) {
        this.optionScore = optionScore;
    }

    /**
     * 获取类型
     *
     * @return question_type - 类型
     */
    public Integer getQuestionType() {
        return questionType;
    }

    /**
     * 设置类型
     *
     * @param questionType 类型
     */
    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    /**
     * 获取问题内容
     *
     * @return question_content - 问题内容
     */
    public String getQuestionContent() {
        return questionContent;
    }

    /**
     * 设置问题内容
     *
     * @param questionContent 问题内容
     */
    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}