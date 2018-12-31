package top.zbeboy.dms.web.bean.data.staff;

import top.zbeboy.dms.domain.dms.tables.pojos.Staff;

public class StaffBean extends Staff {
    private Integer schoolId;

    private String schoolName;

    private Integer collegeId;

    private String collegeName;

    private String departmentName;

    private Boolean enabled;

    private String  realName;

    private String authority;

    private String  politicalLandscapeName;

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

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getPoliticalLandscapeName() {
        return politicalLandscapeName;
    }

    public void setPoliticalLandscapeName(String politicalLandscapeName) {
        this.politicalLandscapeName = politicalLandscapeName;
    }
}
