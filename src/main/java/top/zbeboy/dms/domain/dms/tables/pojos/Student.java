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
public class Student implements Serializable {

    private static final long serialVersionUID = -1241768178;

    private Integer studentId;
    private String  studentNumber;
    private String  sex;
    private Integer politicalLandscapeId;
    private String  placeOrigin;
    private Integer organizeId;
    private String  username;

    public Student() {}

    public Student(Student value) {
        this.studentId = value.studentId;
        this.studentNumber = value.studentNumber;
        this.sex = value.sex;
        this.politicalLandscapeId = value.politicalLandscapeId;
        this.placeOrigin = value.placeOrigin;
        this.organizeId = value.organizeId;
        this.username = value.username;
    }

    public Student(
        Integer studentId,
        String  studentNumber,
        String  sex,
        Integer politicalLandscapeId,
        String  placeOrigin,
        Integer organizeId,
        String  username
    ) {
        this.studentId = studentId;
        this.studentNumber = studentNumber;
        this.sex = sex;
        this.politicalLandscapeId = politicalLandscapeId;
        this.placeOrigin = placeOrigin;
        this.organizeId = organizeId;
        this.username = username;
    }

    public Integer getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @NotNull
    @Size(max = 20)
    public String getStudentNumber() {
        return this.studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Size(max = 2)
    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getPoliticalLandscapeId() {
        return this.politicalLandscapeId;
    }

    public void setPoliticalLandscapeId(Integer politicalLandscapeId) {
        this.politicalLandscapeId = politicalLandscapeId;
    }

    @Size(max = 200)
    public String getPlaceOrigin() {
        return this.placeOrigin;
    }

    public void setPlaceOrigin(String placeOrigin) {
        this.placeOrigin = placeOrigin;
    }

    @NotNull
    public Integer getOrganizeId() {
        return this.organizeId;
    }

    public void setOrganizeId(Integer organizeId) {
        this.organizeId = organizeId;
    }

    @NotNull
    @Size(max = 64)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Student (");

        sb.append(studentId);
        sb.append(", ").append(studentNumber);
        sb.append(", ").append(sex);
        sb.append(", ").append(politicalLandscapeId);
        sb.append(", ").append(placeOrigin);
        sb.append(", ").append(organizeId);
        sb.append(", ").append(username);

        sb.append(")");
        return sb.toString();
    }
}
