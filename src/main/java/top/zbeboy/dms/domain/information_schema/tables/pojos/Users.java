/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.information_schema.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;
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
public class Users implements Serializable {

    private static final long serialVersionUID = 2103122754;

    private String  name;
    private String  admin;
    private String  remarks;
    private Integer id;

    public Users() {}

    public Users(Users value) {
        this.name = value.name;
        this.admin = value.admin;
        this.remarks = value.remarks;
        this.id = value.id;
    }

    public Users(
        String  name,
        String  admin,
        String  remarks,
        Integer id
    ) {
        this.name = name;
        this.admin = admin;
        this.remarks = remarks;
        this.id = id;
    }

    @Size(max = 2147483647)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(max = 2147483647)
    public String getAdmin() {
        return this.admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Size(max = 2147483647)
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Users (");

        sb.append(name);
        sb.append(", ").append(admin);
        sb.append(", ").append(remarks);
        sb.append(", ").append(id);

        sb.append(")");
        return sb.toString();
    }
}
