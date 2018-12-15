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
public class College implements Serializable {

    private static final long serialVersionUID = -870842973;

    private Integer collegeId;
    private String  collegeName;
    private String  collegeAddress;
    private Integer schoolId;
    private Boolean collegeIsDel;

    public College() {}

    public College(College value) {
        this.collegeId = value.collegeId;
        this.collegeName = value.collegeName;
        this.collegeAddress = value.collegeAddress;
        this.schoolId = value.schoolId;
        this.collegeIsDel = value.collegeIsDel;
    }

    public College(
        Integer collegeId,
        String  collegeName,
        String  collegeAddress,
        Integer schoolId,
        Boolean collegeIsDel
    ) {
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.collegeAddress = collegeAddress;
        this.schoolId = schoolId;
        this.collegeIsDel = collegeIsDel;
    }

    public Integer getCollegeId() {
        return this.collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    @NotNull
    @Size(max = 200)
    public String getCollegeName() {
        return this.collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @NotNull
    @Size(max = 500)
    public String getCollegeAddress() {
        return this.collegeAddress;
    }

    public void setCollegeAddress(String collegeAddress) {
        this.collegeAddress = collegeAddress;
    }

    @NotNull
    public Integer getSchoolId() {
        return this.schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Boolean getCollegeIsDel() {
        return this.collegeIsDel;
    }

    public void setCollegeIsDel(Boolean collegeIsDel) {
        this.collegeIsDel = collegeIsDel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("College (");

        sb.append(collegeId);
        sb.append(", ").append(collegeName);
        sb.append(", ").append(collegeAddress);
        sb.append(", ").append(schoolId);
        sb.append(", ").append(collegeIsDel);

        sb.append(")");
        return sb.toString();
    }
}
