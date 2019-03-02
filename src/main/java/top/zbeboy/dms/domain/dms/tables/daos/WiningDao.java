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

import top.zbeboy.dms.domain.dms.tables.Wining;
import top.zbeboy.dms.domain.dms.tables.records.WiningRecord;


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
public class WiningDao extends DAOImpl<WiningRecord, top.zbeboy.dms.domain.dms.tables.pojos.Wining, Integer> {

    /**
     * Create a new WiningDao without any configuration
     */
    public WiningDao() {
        super(Wining.WINING, top.zbeboy.dms.domain.dms.tables.pojos.Wining.class);
    }

    /**
     * Create a new WiningDao with an attached configuration
     */
    @Autowired
    public WiningDao(Configuration configuration) {
        super(Wining.WINING, top.zbeboy.dms.domain.dms.tables.pojos.Wining.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(top.zbeboy.dms.domain.dms.tables.pojos.Wining object) {
        return object.getWiningId();
    }

    /**
     * Fetch records that have <code>WINING_ID IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Wining> fetchByWiningId(Integer... values) {
        return fetch(Wining.WINING.WINING_ID, values);
    }

    /**
     * Fetch a unique record that has <code>WINING_ID = value</code>
     */
    public top.zbeboy.dms.domain.dms.tables.pojos.Wining fetchOneByWiningId(Integer value) {
        return fetchOne(Wining.WINING.WINING_ID, value);
    }

    /**
     * Fetch records that have <code>CREDIT_ID IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Wining> fetchByCreditId(Integer... values) {
        return fetch(Wining.WINING.CREDIT_ID, values);
    }

    /**
     * Fetch records that have <code>WINING_CONTENT IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Wining> fetchByWiningContent(String... values) {
        return fetch(Wining.WINING.WINING_CONTENT, values);
    }

    /**
     * Fetch records that have <code>WINING_SCORE IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.Wining> fetchByWiningScore(Double... values) {
        return fetch(Wining.WINING.WINING_SCORE, values);
    }
}
