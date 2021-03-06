/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Credit implements Serializable {

    private static final long serialVersionUID = -1841921806;

    private Integer creditId;
    private String  studentNumber;
    private String  year;
    private Integer term;
    private Double  sports;
    private Double  skills;
    private Double  voluntary;
    private Double  technological;
    private Double  post;
    private Double  ideological;
    private Double  practical;
    private Double  work;
    private Double  achievement;
    private Double  intellectual;

    public Credit() {}

    public Credit(Credit value) {
        this.creditId = value.creditId;
        this.studentNumber = value.studentNumber;
        this.year = value.year;
        this.term = value.term;
        this.sports = value.sports;
        this.skills = value.skills;
        this.voluntary = value.voluntary;
        this.technological = value.technological;
        this.post = value.post;
        this.ideological = value.ideological;
        this.practical = value.practical;
        this.work = value.work;
        this.achievement = value.achievement;
        this.intellectual = value.intellectual;
    }

    public Credit(
        Integer creditId,
        String  studentNumber,
        String  year,
        Integer term,
        Double  sports,
        Double  skills,
        Double  voluntary,
        Double  technological,
        Double  post,
        Double  ideological,
        Double  practical,
        Double  work,
        Double  achievement,
        Double  intellectual
    ) {
        this.creditId = creditId;
        this.studentNumber = studentNumber;
        this.year = year;
        this.term = term;
        this.sports = sports;
        this.skills = skills;
        this.voluntary = voluntary;
        this.technological = technological;
        this.post = post;
        this.ideological = ideological;
        this.practical = practical;
        this.work = work;
        this.achievement = achievement;
        this.intellectual = intellectual;
    }

    public Integer getCreditId() {
        return this.creditId;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }

    @NotNull
    @Size(max = 20)
    public String getStudentNumber() {
        return this.studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    @NotNull
    @Size(max = 5)
    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @NotNull
    public Integer getTerm() {
        return this.term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Double getSports() {
        return this.sports;
    }

    public void setSports(Double sports) {
        this.sports = sports;
    }

    public Double getSkills() {
        return this.skills;
    }

    public void setSkills(Double skills) {
        this.skills = skills;
    }

    public Double getVoluntary() {
        return this.voluntary;
    }

    public void setVoluntary(Double voluntary) {
        this.voluntary = voluntary;
    }

    public Double getTechnological() {
        return this.technological;
    }

    public void setTechnological(Double technological) {
        this.technological = technological;
    }

    public Double getPost() {
        return this.post;
    }

    public void setPost(Double post) {
        this.post = post;
    }

    public Double getIdeological() {
        return this.ideological;
    }

    public void setIdeological(Double ideological) {
        this.ideological = ideological;
    }

    public Double getPractical() {
        return this.practical;
    }

    public void setPractical(Double practical) {
        this.practical = practical;
    }

    public Double getWork() {
        return this.work;
    }

    public void setWork(Double work) {
        this.work = work;
    }

    public Double getAchievement() {
        return this.achievement;
    }

    public void setAchievement(Double achievement) {
        this.achievement = achievement;
    }

    public Double getIntellectual() {
        return this.intellectual;
    }

    public void setIntellectual(Double intellectual) {
        this.intellectual = intellectual;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Credit (");

        sb.append(creditId);
        sb.append(", ").append(studentNumber);
        sb.append(", ").append(year);
        sb.append(", ").append(term);
        sb.append(", ").append(sports);
        sb.append(", ").append(skills);
        sb.append(", ").append(voluntary);
        sb.append(", ").append(technological);
        sb.append(", ").append(post);
        sb.append(", ").append(ideological);
        sb.append(", ").append(practical);
        sb.append(", ").append(work);
        sb.append(", ").append(achievement);
        sb.append(", ").append(intellectual);

        sb.append(")");
        return sb.toString();
    }
}
