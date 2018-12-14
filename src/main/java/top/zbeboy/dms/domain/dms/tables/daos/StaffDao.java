/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms.tables.daos;


import java.sql.Date;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.zbeboy.dms.domain.dms.tables.Staff;
import top.zbeboy.dms.domain.dms.tables.records.StaffRecord;


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
@Repository
public class StaffDao extends DAOImpl<StaffRecord, top.zbeboy.dms.domain.dms.tables.pojos.Staff, Integer> {

    /**
     * Create a new StaffDao without any configuration
     */
    public StaffDao() {
        super(Staff.STAFF, top.zbeboy.dms.domain.dms.tables.pojos.Staff.class);
    }

    /**
     * Create a new StaffDao with an attached configuration
     */
    @Autowired
    public StaffDao(Configuration configuration) {
        super(Staff.STAFF, top.zbeboy.dms.domain.dms.tables.pojos.Staff.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(top.zbeboy.dms.domain.dms.tables.pojos.Staff object) {
        return object.getStaffId();
    }

    /**
     * Fetch records that have <code>STAFF_ID IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Staff> fetchByStaffId(Integer... values) {
        return fetch(Staff.STAFF.STAFF_ID, values);
    }

    /**
     * Fetch a unique record that has <code>STAFF_ID = value</code>
     */
    public top.zbeboy.dms.domain.dms.tables.pojos.Staff fetchOneByStaffId(Integer value) {
        return fetchOne(Staff.STAFF.STAFF_ID, value);
    }

    /**
     * Fetch records that have <code>STAFF_NUMBER IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Staff> fetchByStaffNumber(String... values) {
        return fetch(Staff.STAFF.STAFF_NUMBER, values);
    }

    /**
     * Fetch a unique record that has <code>STAFF_NUMBER = value</code>
     */
    public top.zbeboy.dms.domain.dms.tables.pojos.Staff fetchOneByStaffNumber(String value) {
        return fetchOne(Staff.STAFF.STAFF_NUMBER, value);
    }

    /**
     * Fetch records that have <code>BIRTHDAY IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Staff> fetchByBirthday(Date... values) {
        return fetch(Staff.STAFF.BIRTHDAY, values);
    }

    /**
     * Fetch records that have <code>SEX IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Staff> fetchBySex(String... values) {
        return fetch(Staff.STAFF.SEX, values);
    }

    /**
     * Fetch records that have <code>FAMILY_RESIDENCE IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Staff> fetchByFamilyResidence(String... values) {
        return fetch(Staff.STAFF.FAMILY_RESIDENCE, values);
    }

    /**
     * Fetch records that have <code>POLITICAL_LANDSCAPE_ID IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Staff> fetchByPoliticalLandscapeId(Integer... values) {
        return fetch(Staff.STAFF.POLITICAL_LANDSCAPE_ID, values);
    }

    /**
     * Fetch records that have <code>NATION_ID IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Staff> fetchByNationId(Integer... values) {
        return fetch(Staff.STAFF.NATION_ID, values);
    }

    /**
     * Fetch records that have <code>POST IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Staff> fetchByPost(String... values) {
        return fetch(Staff.STAFF.POST, values);
    }

    /**
     * Fetch records that have <code>ACADEMIC_TITLE_ID IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Staff> fetchByAcademicTitleId(Integer... values) {
        return fetch(Staff.STAFF.ACADEMIC_TITLE_ID, values);
    }

    /**
     * Fetch records that have <code>DEPARTMENT_ID IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Staff> fetchByDepartmentId(Integer... values) {
        return fetch(Staff.STAFF.DEPARTMENT_ID, values);
    }

    /**
     * Fetch records that have <code>USERNAME IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Staff> fetchByUsername(String... values) {
        return fetch(Staff.STAFF.USERNAME, values);
    }
}
