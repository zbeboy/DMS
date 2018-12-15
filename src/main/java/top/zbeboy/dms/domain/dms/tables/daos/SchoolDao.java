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

import top.zbeboy.dms.domain.dms.tables.School;
import top.zbeboy.dms.domain.dms.tables.records.SchoolRecord;


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
public class SchoolDao extends DAOImpl<SchoolRecord, top.zbeboy.dms.domain.dms.tables.pojos.School, Integer> {

    /**
     * Create a new SchoolDao without any configuration
     */
    public SchoolDao() {
        super(School.SCHOOL, top.zbeboy.dms.domain.dms.tables.pojos.School.class);
    }

    /**
     * Create a new SchoolDao with an attached configuration
     */
    @Autowired
    public SchoolDao(Configuration configuration) {
        super(School.SCHOOL, top.zbeboy.dms.domain.dms.tables.pojos.School.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(top.zbeboy.dms.domain.dms.tables.pojos.School object) {
        return object.getSchoolId();
    }

    /**
     * Fetch records that have <code>SCHOOL_ID IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.School> fetchBySchoolId(Integer... values) {
        return fetch(School.SCHOOL.SCHOOL_ID, values);
    }

    /**
     * Fetch a unique record that has <code>SCHOOL_ID = value</code>
     */
    public top.zbeboy.dms.domain.dms.tables.pojos.School fetchOneBySchoolId(Integer value) {
        return fetchOne(School.SCHOOL.SCHOOL_ID, value);
    }

    /**
     * Fetch records that have <code>SCHOOL_NAME IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.School> fetchBySchoolName(String... values) {
        return fetch(School.SCHOOL.SCHOOL_NAME, values);
    }

    /**
     * Fetch records that have <code>SCHOOL_IS_DEL IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.School> fetchBySchoolIsDel(Boolean... values) {
        return fetch(School.SCHOOL.SCHOOL_IS_DEL, values);
    }
}
