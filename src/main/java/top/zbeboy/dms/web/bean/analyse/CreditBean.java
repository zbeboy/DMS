package top.zbeboy.dms.web.bean.analyse;

import top.zbeboy.dms.domain.dms.tables.pojos.Credit;

public class CreditBean extends Credit {

    private String  realName;

    private String  studentNumber;

    private String  organizeName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String getStudentNumber() {
        return studentNumber;
    }

    @Override
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }
}
