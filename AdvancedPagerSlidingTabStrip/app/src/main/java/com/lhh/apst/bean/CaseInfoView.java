package com.lhh.apst.bean;

/**
 * Created by John on 2016-05-16.
 */
public class CaseInfoView {
    /**
     * 接警单编号
     */
    private String jjdbh;
    /**
     * 案件状态
     */
    private int ajzt;
    /**
     * 报警电话
     */
    private String bjdh;
    /**
     * 事发地址
     */
    private String sfdz;
    /**
     * 报警电话用户姓名
     */
    private String bjdhyhxm;
    /**
     * 报警方式编号
     */
    private String bjfsbh;
    /**
     * 警情级别
     */
    private int caseLevel;
    /**
     * 一级报警类型
     */
    private String bjlbmc;
    /**
     * 二级报警类型
     */
    private String bjlxmc;
    /**
     * 三级报警类型
     */
    private String bjxlmc;
    /**
     * 报警电话地址
     */
    private String bjdhyhdz;
    /**
     * 报警时间
     */
    private String bjsj;
    /**
     * 报警内容
     */
    private String bjnr;
    /**
     * 标注状态
     */
    private Boolean mark;
    /**
     * 管辖单位编号
     */
    private String gxdwbh;
    /**
     * 警员姓名
     */
    private String policeName;

    public String getJjdbh() {
        return jjdbh;
    }

    public void setJjdbh(String jjdbh) {
        this.jjdbh = jjdbh;
    }

    public int getAjzt() {
        return ajzt;
    }

    public void setAjzt(int ajzt) {
        this.ajzt = ajzt;
    }

    public String getBjdh() {
        return bjdh;
    }

    public void setBjdh(String bjdh) {
        this.bjdh = bjdh;
    }

    public String getSfdz() {
        return sfdz;
    }

    public void setSfdz(String sfdz) {
        this.sfdz = sfdz;
    }

    public String getBjdhyhxm() {
        return bjdhyhxm;
    }

    public void setBjdhyhxm(String bjdhyhxm) {
        this.bjdhyhxm = bjdhyhxm;
    }

    public String getBjfsbh() {
        return bjfsbh;
    }

    public void setBjfsbh(String bjfsbh) {
        this.bjfsbh = bjfsbh;
    }

    public int getCaseLevel() {
        return caseLevel;
    }

    public void setCaseLevel(int caseLevel) {
        this.caseLevel = caseLevel;
    }

    public String getBjlbmc() {
        return bjlbmc;
    }

    public void setBjlbmc(String bjlbmc) {
        this.bjlbmc = bjlbmc;
    }

    public String getBjlxmc() {
        return bjlxmc;
    }

    public void setBjlxmc(String bjlxmc) {
        this.bjlxmc = bjlxmc;
    }

    public String getBjxlmc() {
        return bjxlmc;
    }

    public void setBjxlmc(String bjxlmc) {
        this.bjxlmc = bjxlmc;
    }

    public String getBjdhyhdz() {
        return bjdhyhdz;
    }

    public void setBjdhyhdz(String bjdhyhdz) {
        this.bjdhyhdz = bjdhyhdz;
    }

    public String getBjsj() {
        return bjsj;
    }

    public void setBjsj(String bjsj) {
        this.bjsj = bjsj;
    }

    public String getBjnr() {
        return bjnr;
    }

    public void setBjnr(String bjnr) {
        this.bjnr = bjnr;
    }

    public Boolean getMark() {
        return mark;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
    }

    public String getGxdwbh() {
        return gxdwbh;
    }

    public void setGxdwbh(String gxdwbh) {
        this.gxdwbh = gxdwbh;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }
}
