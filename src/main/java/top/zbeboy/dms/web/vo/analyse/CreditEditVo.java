package top.zbeboy.dms.web.vo.analyse;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreditEditVo {
    @NotNull(message = "学分ID不能为空")
    @Min(value = 1, message = "学分ID不正确")
    private int creditId;
    @NotBlank(message = "学号不能为空")
    private String studentNumber;
    @NotBlank(message = "年份不能为空")
    private String year;
    @NotNull(message = "学期不能为空")
    private int term;
    private Double sports;
    private Double skills;
    private Double voluntary;
    private Double technological;
    private Double post;
    private Double ideological;
    private Double practical;
    private Double work;
    private Double achievement;

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Double getSports() {
        return sports;
    }

    public void setSports(Double sports) {
        this.sports = sports;
    }

    public Double getSkills() {
        return skills;
    }

    public void setSkills(Double skills) {
        this.skills = skills;
    }

    public Double getVoluntary() {
        return voluntary;
    }

    public void setVoluntary(Double voluntary) {
        this.voluntary = voluntary;
    }

    public Double getTechnological() {
        return technological;
    }

    public void setTechnological(Double technological) {
        this.technological = technological;
    }

    public Double getPost() {
        return post;
    }

    public void setPost(Double post) {
        this.post = post;
    }

    public Double getIdeological() {
        return ideological;
    }

    public void setIdeological(Double ideological) {
        this.ideological = ideological;
    }

    public Double getPractical() {
        return practical;
    }

    public void setPractical(Double practical) {
        this.practical = practical;
    }

    public Double getWork() {
        return work;
    }

    public void setWork(Double work) {
        this.work = work;
    }

    public Double getAchievement() {
        return achievement;
    }

    public void setAchievement(Double achievement) {
        this.achievement = achievement;
    }
}
