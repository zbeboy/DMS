package top.zbeboy.dms.web.bean.quality;

import top.zbeboy.dms.domain.dms.tables.pojos.QualityApply;

public class QualityApplyBean extends QualityApply {
    private String applyDateStr;

    private String realName;

    private String studentNumber;

    public String getApplyDateStr() {
        return applyDateStr;
    }

    public void setApplyDateStr(String applyDateStr) {
        this.applyDateStr = applyDateStr;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}
