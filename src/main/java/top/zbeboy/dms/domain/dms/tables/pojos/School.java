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
public class School implements Serializable {

    private static final long serialVersionUID = 1499313142;

    private Integer schoolId;
    private String  schoolName;
    private Boolean schoolIsDel;

    public School() {}

    public School(School value) {
        this.schoolId = value.schoolId;
        this.schoolName = value.schoolName;
        this.schoolIsDel = value.schoolIsDel;
    }

    public School(
        Integer schoolId,
        String  schoolName,
        Boolean schoolIsDel
    ) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.schoolIsDel = schoolIsDel;
    }

    public Integer getSchoolId() {
        return this.schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    @NotNull
    @Size(max = 200)
    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Boolean getSchoolIsDel() {
        return this.schoolIsDel;
    }

    public void setSchoolIsDel(Boolean schoolIsDel) {
        this.schoolIsDel = schoolIsDel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("School (");

        sb.append(schoolId);
        sb.append(", ").append(schoolName);
        sb.append(", ").append(schoolIsDel);

        sb.append(")");
        return sb.toString();
    }
}
