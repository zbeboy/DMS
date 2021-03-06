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
public class Organize implements Serializable {

    private static final long serialVersionUID = 769626832;

    private Integer organizeId;
    private String  organizeName;
    private Integer gradeId;
    private Boolean organizeIsDel;
    private Integer staffId;

    public Organize() {}

    public Organize(Organize value) {
        this.organizeId = value.organizeId;
        this.organizeName = value.organizeName;
        this.gradeId = value.gradeId;
        this.organizeIsDel = value.organizeIsDel;
        this.staffId = value.staffId;
    }

    public Organize(
        Integer organizeId,
        String  organizeName,
        Integer gradeId,
        Boolean organizeIsDel,
        Integer staffId
    ) {
        this.organizeId = organizeId;
        this.organizeName = organizeName;
        this.gradeId = gradeId;
        this.organizeIsDel = organizeIsDel;
        this.staffId = staffId;
    }

    public Integer getOrganizeId() {
        return this.organizeId;
    }

    public void setOrganizeId(Integer organizeId) {
        this.organizeId = organizeId;
    }

    @NotNull
    @Size(max = 200)
    public String getOrganizeName() {
        return this.organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    @NotNull
    public Integer getGradeId() {
        return this.gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Boolean getOrganizeIsDel() {
        return this.organizeIsDel;
    }

    public void setOrganizeIsDel(Boolean organizeIsDel) {
        this.organizeIsDel = organizeIsDel;
    }

    public Integer getStaffId() {
        return this.staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Organize (");

        sb.append(organizeId);
        sb.append(", ").append(organizeName);
        sb.append(", ").append(gradeId);
        sb.append(", ").append(organizeIsDel);
        sb.append(", ").append(staffId);

        sb.append(")");
        return sb.toString();
    }
}
