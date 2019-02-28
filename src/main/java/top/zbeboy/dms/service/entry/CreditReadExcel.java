package top.zbeboy.dms.service.entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.zbeboy.dms.service.util.excel.Common;
import top.zbeboy.dms.service.util.excel.Util;
import top.zbeboy.dms.web.bean.analyse.CreditBean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CreditReadExcel {

    private final Logger log = LoggerFactory.getLogger(CreditReadExcel.class);

    public List<CreditBean> readExcel(String path) throws IOException {
        if (path == null || Common.EMPTY.equals(path)) {
            return null;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    return readXls(path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readXlsx(path);
                }
            } else {
                log.error(path + Common.NOT_EXCEL_FILE);
            }
        }
        return null;
    }

    public List<CreditBean> readXlsx(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        CreditBean credit = null;
        List<CreditBean> list = new ArrayList<CreditBean>();
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    credit = new CreditBean();
                    XSSFCell studentNumber = xssfRow.getCell(0);
                    XSSFCell year = xssfRow.getCell(1);
                    XSSFCell term = xssfRow.getCell(2);
                    XSSFCell sports = xssfRow.getCell(3);
                    XSSFCell skills = xssfRow.getCell(4);
                    XSSFCell voluntary = xssfRow.getCell(5);
                    XSSFCell technological = xssfRow.getCell(6);
                    XSSFCell post = xssfRow.getCell(7);
                    XSSFCell ideological = xssfRow.getCell(8);
                    XSSFCell practical = xssfRow.getCell(9);
                    XSSFCell work = xssfRow.getCell(10);
                    XSSFCell achievement = xssfRow.getCell(11);
                    XSSFCell intellectual = xssfRow.getCell(12);
                    credit.setStudentNumber(getValue(studentNumber));
                    credit.setYear(getValue(year));
                    credit.setTerm(Integer.valueOf(getValue(term)));
                    credit.setSports(Double.valueOf(getValue(sports)));
                    credit.setSkills(Double.valueOf(getValue(skills)));
                    credit.setVoluntary(Double.valueOf(getValue(voluntary)));
                    credit.setTechnological(Double.valueOf(getValue(technological)));
                    credit.setPost(Double.valueOf(getValue(post)));
                    credit.setIdeological(Double.valueOf(getValue(ideological)));
                    credit.setPractical(Double.valueOf(getValue(practical)));
                    credit.setWork(Double.valueOf(getValue(work)));
                    credit.setAchievement(Double.valueOf(getValue(achievement)));
                    credit.setIntellectual(Double.valueOf(getValue(intellectual)));
                    list.add(credit);
                }
            }
        }
        return list;
    }

    public List<CreditBean> readXls(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        CreditBean credit = null;
        List<CreditBean> list = new ArrayList<CreditBean>();
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    credit = new CreditBean();
                    HSSFCell studentNumber = hssfRow.getCell(0);
                    HSSFCell year = hssfRow.getCell(1);
                    HSSFCell term = hssfRow.getCell(2);
                    HSSFCell sports = hssfRow.getCell(3);
                    HSSFCell skills = hssfRow.getCell(4);
                    HSSFCell voluntary = hssfRow.getCell(5);
                    HSSFCell technological = hssfRow.getCell(6);
                    HSSFCell post = hssfRow.getCell(7);
                    HSSFCell ideological = hssfRow.getCell(8);
                    HSSFCell practical = hssfRow.getCell(9);
                    HSSFCell work = hssfRow.getCell(10);
                    HSSFCell achievement = hssfRow.getCell(11);
                    HSSFCell intellectual = hssfRow.getCell(12);
                    credit.setStudentNumber(getValue(studentNumber));
                    credit.setYear(getValue(year));
                    credit.setTerm(Integer.valueOf(getValue(term)));
                    credit.setSports(Double.valueOf(getValue(sports)));
                    credit.setSkills(Double.valueOf(getValue(skills)));
                    credit.setVoluntary(Double.valueOf(getValue(voluntary)));
                    credit.setTechnological(Double.valueOf(getValue(technological)));
                    credit.setPost(Double.valueOf(getValue(post)));
                    credit.setIdeological(Double.valueOf(getValue(ideological)));
                    credit.setPractical(Double.valueOf(getValue(practical)));
                    credit.setWork(Double.valueOf(getValue(work)));
                    credit.setAchievement(Double.valueOf(getValue(achievement)));
                    credit.setIntellectual(Double.valueOf(getValue(intellectual)));
                    list.add(credit);
                }
            }
        }
        return list;
    }

    private String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == CellType.NUMERIC) {
            Double d = xssfRow.getNumericCellValue();
            DecimalFormat df = new DecimalFormat("##.###");
            return df.format(d);
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}
