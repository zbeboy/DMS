/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms.tables.daos;


import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.zbeboy.dms.domain.dms.tables.QualityRelease;
import top.zbeboy.dms.domain.dms.tables.records.QualityReleaseRecord;


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
public class QualityReleaseDao extends DAOImpl<QualityReleaseRecord, top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease, String> {

    /**
     * Create a new QualityReleaseDao without any configuration
     */
    public QualityReleaseDao() {
        super(QualityRelease.QUALITY_RELEASE, top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease.class);
    }

    /**
     * Create a new QualityReleaseDao with an attached configuration
     */
    @Autowired
    public QualityReleaseDao(Configuration configuration) {
        super(QualityRelease.QUALITY_RELEASE, top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease object) {
        return object.getQualityReleaseId();
    }

    /**
     * Fetch records that have <code>QUALITY_RELEASE_ID IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease> fetchByQualityReleaseId(String... values) {
        return fetch(QualityRelease.QUALITY_RELEASE.QUALITY_RELEASE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>QUALITY_RELEASE_ID = value</code>
     */
    public top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease fetchOneByQualityReleaseId(String value) {
        return fetchOne(QualityRelease.QUALITY_RELEASE.QUALITY_RELEASE_ID, value);
    }

    /**
     * Fetch records that have <code>RELEASE_TITLE IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease> fetchByReleaseTitle(String... values) {
        return fetch(QualityRelease.QUALITY_RELEASE.RELEASE_TITLE, values);
    }

    /**
     * Fetch records that have <code>YEAR IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease> fetchByYear(String... values) {
        return fetch(QualityRelease.QUALITY_RELEASE.YEAR, values);
    }

    /**
     * Fetch records that have <code>TERM IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease> fetchByTerm(Integer... values) {
        return fetch(QualityRelease.QUALITY_RELEASE.TERM, values);
    }

    /**
     * Fetch records that have <code>RELEASE_DATE IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease> fetchByReleaseDate(Timestamp... values) {
        return fetch(QualityRelease.QUALITY_RELEASE.RELEASE_DATE, values);
    }

    /**
     * Fetch records that have <code>USERNAME IN (values)</code>
     */
    public List<top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease> fetchByUsername(String... values) {
        return fetch(QualityRelease.QUALITY_RELEASE.USERNAME, values);
    }
}
