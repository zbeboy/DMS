package top.zbeboy.dms.web.bean.data.college;

import top.zbeboy.dms.domain.dms.tables.pojos.College;

public class CollegeBean extends College {
    private String  schoolName;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
