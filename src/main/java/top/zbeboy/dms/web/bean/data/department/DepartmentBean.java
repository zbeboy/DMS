package top.zbeboy.dms.web.bean.data.department;

import top.zbeboy.dms.domain.dms.tables.pojos.Department;

public class DepartmentBean extends Department {

    private Integer schoolId;

    private String  schoolName;

    private String  collegeName;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
