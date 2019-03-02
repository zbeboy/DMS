/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

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
public class QualityRelease implements Serializable {

    private static final long serialVersionUID = 1676024866;

    private String    qualityReleaseId;
    private String    releaseTitle;
    private String    year;
    private Integer   term;
    private Timestamp releaseDate;
    private String    username;

    public QualityRelease() {}

    public QualityRelease(QualityRelease value) {
        this.qualityReleaseId = value.qualityReleaseId;
        this.releaseTitle = value.releaseTitle;
        this.year = value.year;
        this.term = value.term;
        this.releaseDate = value.releaseDate;
        this.username = value.username;
    }

    public QualityRelease(
        String    qualityReleaseId,
        String    releaseTitle,
        String    year,
        Integer   term,
        Timestamp releaseDate,
        String    username
    ) {
        this.qualityReleaseId = qualityReleaseId;
        this.releaseTitle = releaseTitle;
        this.year = year;
        this.term = term;
        this.releaseDate = releaseDate;
        this.username = username;
    }

    @NotNull
    @Size(max = 64)
    public String getQualityReleaseId() {
        return this.qualityReleaseId;
    }

    public void setQualityReleaseId(String qualityReleaseId) {
        this.qualityReleaseId = qualityReleaseId;
    }

    @NotNull
    @Size(max = 100)
    public String getReleaseTitle() {
        return this.releaseTitle;
    }

    public void setReleaseTitle(String releaseTitle) {
        this.releaseTitle = releaseTitle;
    }

    @NotNull
    @Size(max = 5)
    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @NotNull
    public Integer getTerm() {
        return this.term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    @NotNull
    public Timestamp getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    @NotNull
    @Size(max = 64)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("QualityRelease (");

        sb.append(qualityReleaseId);
        sb.append(", ").append(releaseTitle);
        sb.append(", ").append(year);
        sb.append(", ").append(term);
        sb.append(", ").append(releaseDate);
        sb.append(", ").append(username);

        sb.append(")");
        return sb.toString();
    }
}
