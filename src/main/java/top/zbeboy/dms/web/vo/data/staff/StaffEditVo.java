package top.zbeboy.dms.web.vo.data.staff;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StaffEditVo {
    @NotNull(message = "教师id不能为空")
    private int staffId;
    @NotBlank(message = "账号不能为空")
    private String username;
    @NotNull(message = "学校不能为空")
    @Min(value = 1, message = "学校不正确")
    private int schoolId;
    @NotNull(message = "院不能为空")
    @Min(value = 1, message = "院不正确")
    private int collegeId;
    @NotNull(message = "系不能为空")
    @Min(value = 1, message = "系不正确")
    private int departmentId;
    @NotBlank(message = "姓名不能为空")
    @Size(max = 30, message = "姓名30位字符")
    private String realName;
    @NotBlank(message = "工号不能为空")
    @Size(max = 20, message = "工号20位字符")
    private String staffNumber;
    private String sex;
    private int politicalLandscapeId;

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getPoliticalLandscapeId() {
        return politicalLandscapeId;
    }

    public void setPoliticalLandscapeId(int politicalLandscapeId) {
        this.politicalLandscapeId = politicalLandscapeId;
    }
}
