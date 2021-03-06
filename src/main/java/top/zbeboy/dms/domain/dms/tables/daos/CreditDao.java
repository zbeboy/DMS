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

import top.zbeboy.dms.domain.dms.tables.Credit;
import top.zbeboy.dms.domain.dms.tables.records.CreditRecord;


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
public class CreditDao extends DAOImpl<CreditRecord, top.zbeboy.dms.domain.dms.tables.pojos.Credit, Integer> {

    /**
     * Create a new CreditDao without any configuration
     */
    public CreditDao() {
        super(Credit.CREDIT, top.zbeboy.dms.domain.dms.tables.pojos.Credit.class);
    }

    /**
     * Create a new CreditDao with an attached configuration
     */
    @Autowired
    public CreditDao(Configuration configuration) {
        super(Credit.CREDIT, top.zbeboy.dms.domain.dms.tables.pojos.Credit.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(top.zbeboy.dms.domain.dms.tables.pojos.Credit object) {
        return object.getCreditId();
    }

    /**
     * Fetch records that have <code>CREDIT_ID IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Credit> fetchByCreditId(Integer... values) {
        return fetch(Credit.CREDIT.CREDIT_ID, values);
    }

    /**
     * Fetch a unique record that has <code>CREDIT_ID = value</code>
     */
    public top.zbeboy.dms.domain.dms.tables.pojos.Credit fetchOneByCreditId(Integer value) {
        return fetchOne(Credit.CREDIT.CREDIT_ID, value);
    }

    /**
     * Fetch records that have <code>STUDENT_NUMBER IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Credit> fetchByStudentNumber(String... values) {
        return fetch(Credit.CREDIT.STUDENT_NUMBER, values);
    }

    /**
     * Fetch records that have <code>YEAR IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Credit> fetchByYear(String... values) {
        return fetch(Credit.CREDIT.YEAR, values);
    }

    /**
     * Fetch records that have <code>TERM IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Credit> fetchByTerm(Integer... values) {
        return fetch(Credit.CREDIT.TERM, values);
    }

    /**
     * Fetch records that have <code>SPORTS IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Credit> fetchBySports(Double... values) {
        return fetch(Credit.CREDIT.SPORTS, values);
    }

    /**
     * Fetch records that have <code>SKILLS IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Credit> fetchBySkills(Double... values) {
        return fetch(Credit.CREDIT.SKILLS, values);
    }

    /**
     * Fetch records that have <code>VOLUNTARY IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Credit> fetchByVoluntary(Double... values) {
        return fetch(Credit.CREDIT.VOLUNTARY, values);
    }

    /**
     * Fetch records that have <code>TECHNOLOGICAL IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Credit> fetchByTechnological(Double... values) {
        return fetch(Credit.CREDIT.TECHNOLOGICAL, values);
    }

    /**
     * Fetch records that have <code>POST IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Credit> fetchByPost(Double... values) {
        return fetch(Credit.CREDIT.POST, values);
    }

    /**
     * Fetch records that have <code>IDEOLOGICAL IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Credit> fetchByIdeological(Double... values) {
        return fetch(Credit.CREDIT.IDEOLOGICAL, values);
    }

    /**
     * Fetch records that have <code>PRACTICAL IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Credit> fetchByPractical(Double... values) {
        return fetch(Credit.CREDIT.PRACTICAL, values);
    }

    /**
     * Fetch records that have <code>WORK IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Credit> fetchByWork(Double... values) {
        return fetch(Credit.CREDIT.WORK, values);
    }

    /**
     * Fetch records that have <code>ACHIEVEMENT IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Credit> fetchByAchievement(Double... values) {
        return fetch(Credit.CREDIT.ACHIEVEMENT, values);
    }

    /**
     * Fetch records that have <code>INTELLECTUAL IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Credit> fetchByIntellectual(Double... values) {
        return fetch(Credit.CREDIT.INTELLECTUAL, values);
    }
}
