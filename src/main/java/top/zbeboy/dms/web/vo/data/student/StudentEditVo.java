package top.zbeboy.dms.web.vo.data.student;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudentEditVo {
    @NotNull(message = "学生id不能为空")
    private int studentId;
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
    @NotNull(message = "专业不能为空")
    @Min(value = 1, message = "专业不正确")
    private int scienceId;
    @NotNull(message = "年级不能为空")
    @Min(value = 1, message = "年级不正确")
    private int gradeId;
    @NotNull(message = "班级不能为空")
    @Min(value = 1, message = "班级不正确")
    private int organizeId;
    @NotBlank(message = "姓名不能为空")
    @Size(max = 30, message = "姓名30位字符")
    private String realName;
    @NotBlank(message = "学号不能为空")
    @Size(max = 20, message = "学号20位字符")
    private String studentNumber;
    private String sex;
    private int politicalLandscapeId;
    private String placeOrigin;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public int getScienceId() {
        return scienceId;
    }

    public void setScienceId(int scienceId) {
        this.scienceId = scienceId;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(int organizeId) {
        this.organizeId = organizeId;
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

    public String getPlaceOrigin() {
        return placeOrigin;
    }

    public void setPlaceOrigin(String placeOrigin) {
        this.placeOrigin = placeOrigin;
    }
}
