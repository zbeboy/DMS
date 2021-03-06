/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms.tables.daos;


import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.zbeboy.dms.domain.dms.tables.College;
import top.zbeboy.dms.domain.dms.tables.records.CollegeRecord;


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
public class CollegeDao extends DAOImpl<CollegeRecord, top.zbeboy.dms.domain.dms.tables.pojos.College, Integer> {

    /**
     * Create a new CollegeDao without any configuration
     */
    public CollegeDao() {
        super(College.COLLEGE, top.zbeboy.dms.domain.dms.tables.pojos.College.class);
    }

    /**
     * Create a new CollegeDao with an attached configuration
     */
    @Autowired
    public CollegeDao(Configuration configuration) {
        super(College.COLLEGE, top.zbeboy.dms.domain.dms.tables.pojos.College.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(top.zbeboy.dms.domain.dms.tables.pojos.College object) {
        return object.getCollegeId();
    }

    /**
     * Fetch records that have <code>COLLEGE_ID IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.College> fetchByCollegeId(Integer... values) {
        return fetch(College.COLLEGE.COLLEGE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>COLLEGE_ID = value</code>
     */
    public top.zbeboy.dms.domain.dms.tables.pojos.College fetchOneByCollegeId(Integer value) {
        return fetchOne(College.COLLEGE.COLLEGE_ID, value);
    }

    /**
     * Fetch records that have <code>COLLEGE_NAME IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.College> fetchByCollegeName(String... values) {
        return fetch(College.COLLEGE.COLLEGE_NAME, values);
    }

    /**
     * Fetch records that have <code>COLLEGE_ADDRESS IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.College> fetchByCollegeAddress(String... values) {
        return fetch(College.COLLEGE.COLLEGE_ADDRESS, values);
    }

    /**
     * Fetch records that have <code>SCHOOL_ID IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.College> fetchBySchoolId(Integer... values) {
        return fetch(College.COLLEGE.SCHOOL_ID, values);
    }

    /**
     * Fetch records that have <code>COLLEGE_IS_DEL IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.College> fetchByCollegeIsDel(Boolean... values) {
        return fetch(College.COLLEGE.COLLEGE_IS_DEL, values);
    }
}
