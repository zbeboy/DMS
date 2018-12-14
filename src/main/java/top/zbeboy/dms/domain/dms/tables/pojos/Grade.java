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
public class Grade implements Serializable {

    private static final long serialVersionUID = -206537524;

    private Integer gradeId;
    private String  grade;
    private Integer scienceId;

    public Grade() {}

    public Grade(Grade value) {
        this.gradeId = value.gradeId;
        this.grade = value.grade;
        this.scienceId = value.scienceId;
    }

    public Grade(
        Integer gradeId,
        String  grade,
        Integer scienceId
    ) {
        this.gradeId = gradeId;
        this.grade = grade;
        this.scienceId = scienceId;
    }

    public Integer getGradeId() {
        return this.gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    @Size(max = 5)
    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @NotNull
    public Integer getScienceId() {
        return this.scienceId;
    }

    public void setScienceId(Integer scienceId) {
        this.scienceId = scienceId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Grade (");

        sb.append(gradeId);
        sb.append(", ").append(grade);
        sb.append(", ").append(scienceId);

        sb.append(")");
        return sb.toString();
    }
}
