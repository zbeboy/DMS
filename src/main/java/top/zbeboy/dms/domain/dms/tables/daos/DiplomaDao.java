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

import top.zbeboy.dms.domain.dms.tables.Diploma;
import top.zbeboy.dms.domain.dms.tables.records.DiplomaRecord;


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
public class DiplomaDao extends DAOImpl<DiplomaRecord, top.zbeboy.dms.domain.dms.tables.pojos.Diploma, Integer> {

    /**
     * Create a new DiplomaDao without any configuration
     */
    public DiplomaDao() {
        super(Diploma.DIPLOMA, top.zbeboy.dms.domain.dms.tables.pojos.Diploma.class);
    }

    /**
     * Create a new DiplomaDao with an attached configuration
     */
    @Autowired
    public DiplomaDao(Configuration configuration) {
        super(Diploma.DIPLOMA, top.zbeboy.dms.domain.dms.tables.pojos.Diploma.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(top.zbeboy.dms.domain.dms.tables.pojos.Diploma object) {
        return object.getDiplomaId();
    }

    /**
     * Fetch records that have <code>DIPLOMA_ID IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Diploma> fetchByDiplomaId(Integer... values) {
        return fetch(Diploma.DIPLOMA.DIPLOMA_ID, values);
    }

    /**
     * Fetch a unique record that has <code>DIPLOMA_ID = value</code>
     */
    public top.zbeboy.dms.domain.dms.tables.pojos.Diploma fetchOneByDiplomaId(Integer value) {
        return fetchOne(Diploma.DIPLOMA.DIPLOMA_ID, value);
    }

    /**
     * Fetch records that have <code>CREDIT_ID IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Diploma> fetchByCreditId(Integer... values) {
        return fetch(Diploma.DIPLOMA.CREDIT_ID, values);
    }

    /**
     * Fetch records that have <code>DIPLOMA_NAME IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Diploma> fetchByDiplomaName(String... values) {
        return fetch(Diploma.DIPLOMA.DIPLOMA_NAME, values);
    }
}