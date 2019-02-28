/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms.tables.records;


import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record14;
import org.jooq.Row14;
import org.jooq.impl.UpdatableRecordImpl;

import top.zbeboy.dms.domain.dms.tables.Credit;


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
public class CreditRecord extends UpdatableRecordImpl<CreditRecord> implements Record14<Integer, String, String, Integer, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double> {

    private static final long serialVersionUID = 793998458;

    /**
     * Setter for <code>DMS.CREDIT.CREDIT_ID</code>.
     */
    public void setCreditId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>DMS.CREDIT.CREDIT_ID</code>.
     */
    public Integer getCreditId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>DMS.CREDIT.STUDENT_NUMBER</code>.
     */
    public void setStudentNumber(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>DMS.CREDIT.STUDENT_NUMBER</code>.
     */
    @NotNull
    @Size(max = 20)
    public String getStudentNumber() {
        return (String) get(1);
    }

    /**
     * Setter for <code>DMS.CREDIT.YEAR</code>.
     */
    public void setYear(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>DMS.CREDIT.YEAR</code>.
     */
    @NotNull
    @Size(max = 5)
    public String getYear() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DMS.CREDIT.TERM</code>.
     */
    public void setTerm(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>DMS.CREDIT.TERM</code>.
     */
    @NotNull
    public Integer getTerm() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>DMS.CREDIT.SPORTS</code>.
     */
    public void setSports(Double value) {
        set(4, value);
    }

    /**
     * Getter for <code>DMS.CREDIT.SPORTS</code>.
     */
    public Double getSports() {
        return (Double) get(4);
    }

    /**
     * Setter for <code>DMS.CREDIT.SKILLS</code>.
     */
    public void setSkills(Double value) {
        set(5, value);
    }

    /**
     * Getter for <code>DMS.CREDIT.SKILLS</code>.
     */
    public Double getSkills() {
        return (Double) get(5);
    }

    /**
     * Setter for <code>DMS.CREDIT.VOLUNTARY</code>.
     */
    public void setVoluntary(Double value) {
        set(6, value);
    }

    /**
     * Getter for <code>DMS.CREDIT.VOLUNTARY</code>.
     */
    public Double getVoluntary() {
        return (Double) get(6);
    }

    /**
     * Setter for <code>DMS.CREDIT.TECHNOLOGICAL</code>.
     */
    public void setTechnological(Double value) {
        set(7, value);
    }

    /**
     * Getter for <code>DMS.CREDIT.TECHNOLOGICAL</code>.
     */
    public Double getTechnological() {
        return (Double) get(7);
    }

    /**
     * Setter for <code>DMS.CREDIT.POST</code>.
     */
    public void setPost(Double value) {
        set(8, value);
    }

    /**
     * Getter for <code>DMS.CREDIT.POST</code>.
     */
    public Double getPost() {
        return (Double) get(8);
    }

    /**
     * Setter for <code>DMS.CREDIT.IDEOLOGICAL</code>.
     */
    public void setIdeological(Double value) {
        set(9, value);
    }

    /**
     * Getter for <code>DMS.CREDIT.IDEOLOGICAL</code>.
     */
    public Double getIdeological() {
        return (Double) get(9);
    }

    /**
     * Setter for <code>DMS.CREDIT.PRACTICAL</code>.
     */
    public void setPractical(Double value) {
        set(10, value);
    }

    /**
     * Getter for <code>DMS.CREDIT.PRACTICAL</code>.
     */
    public Double getPractical() {
        return (Double) get(10);
    }

    /**
     * Setter for <code>DMS.CREDIT.WORK</code>.
     */
    public void setWork(Double value) {
        set(11, value);
    }

    /**
     * Getter for <code>DMS.CREDIT.WORK</code>.
     */
    public Double getWork() {
        return (Double) get(11);
    }

    /**
     * Setter for <code>DMS.CREDIT.ACHIEVEMENT</code>.
     */
    public void setAchievement(Double value) {
        set(12, value);
    }

    /**
     * Getter for <code>DMS.CREDIT.ACHIEVEMENT</code>.
     */
    public Double getAchievement() {
        return (Double) get(12);
    }

    /**
     * Setter for <code>DMS.CREDIT.INTELLECTUAL</code>.
     */
    public void setIntellectual(Double value) {
        set(13, value);
    }

    /**
     * Getter for <code>DMS.CREDIT.INTELLECTUAL</code>.
     */
    public Double getIntellectual() {
        return (Double) get(13);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record14 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row14<Integer, String, String, Integer, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double> fieldsRow() {
        return (Row14) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row14<Integer, String, String, Integer, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double> valuesRow() {
        return (Row14) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Credit.CREDIT.CREDIT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Credit.CREDIT.STUDENT_NUMBER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Credit.CREDIT.YEAR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return Credit.CREDIT.TERM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field5() {
        return Credit.CREDIT.SPORTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field6() {
        return Credit.CREDIT.SKILLS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field7() {
        return Credit.CREDIT.VOLUNTARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field8() {
        return Credit.CREDIT.TECHNOLOGICAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field9() {
        return Credit.CREDIT.POST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field10() {
        return Credit.CREDIT.IDEOLOGICAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field11() {
        return Credit.CREDIT.PRACTICAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field12() {
        return Credit.CREDIT.WORK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field13() {
        return Credit.CREDIT.ACHIEVEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field14() {
        return Credit.CREDIT.INTELLECTUAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getCreditId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getStudentNumber();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getYear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getTerm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component5() {
        return getSports();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component6() {
        return getSkills();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component7() {
        return getVoluntary();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component8() {
        return getTechnological();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component9() {
        return getPost();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component10() {
        return getIdeological();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component11() {
        return getPractical();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component12() {
        return getWork();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component13() {
        return getAchievement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component14() {
        return getIntellectual();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getCreditId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getStudentNumber();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getYear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getTerm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value5() {
        return getSports();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value6() {
        return getSkills();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value7() {
        return getVoluntary();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value8() {
        return getTechnological();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value9() {
        return getPost();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value10() {
        return getIdeological();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value11() {
        return getPractical();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value12() {
        return getWork();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value13() {
        return getAchievement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value14() {
        return getIntellectual();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord value1(Integer value) {
        setCreditId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord value2(String value) {
        setStudentNumber(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord value3(String value) {
        setYear(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord value4(Integer value) {
        setTerm(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord value5(Double value) {
        setSports(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord value6(Double value) {
        setSkills(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord value7(Double value) {
        setVoluntary(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord value8(Double value) {
        setTechnological(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord value9(Double value) {
        setPost(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord value10(Double value) {
        setIdeological(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord value11(Double value) {
        setPractical(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord value12(Double value) {
        setWork(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord value13(Double value) {
        setAchievement(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord value14(Double value) {
        setIntellectual(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditRecord values(Integer value1, String value2, String value3, Integer value4, Double value5, Double value6, Double value7, Double value8, Double value9, Double value10, Double value11, Double value12, Double value13, Double value14) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CreditRecord
     */
    public CreditRecord() {
        super(Credit.CREDIT);
    }

    /**
     * Create a detached, initialised CreditRecord
     */
    public CreditRecord(Integer creditId, String studentNumber, String year, Integer term, Double sports, Double skills, Double voluntary, Double technological, Double post, Double ideological, Double practical, Double work, Double achievement, Double intellectual) {
        super(Credit.CREDIT);

        set(0, creditId);
        set(1, studentNumber);
        set(2, year);
        set(3, term);
        set(4, sports);
        set(5, skills);
        set(6, voluntary);
        set(7, technological);
        set(8, post);
        set(9, ideological);
        set(10, practical);
        set(11, work);
        set(12, achievement);
        set(13, intellectual);
    }
}
