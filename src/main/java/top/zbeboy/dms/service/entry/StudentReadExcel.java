package top.zbeboy.dms.service.entry;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.zbeboy.dms.service.util.excel.Common;
import top.zbeboy.dms.service.util.excel.Util;
import top.zbeboy.dms.web.bean.data.student.StudentBean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentReadExcel {

    private final Logger log = LoggerFactory.getLogger(StudentReadExcel.class);

    public List<StudentBean> readExcel(String path) throws IOException {
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

    public List<StudentBean> readXlsx(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        StudentBean student = null;
        List<StudentBean> list = new ArrayList<StudentBean>();
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    student = new StudentBean();
                    XSSFCell organizeId = xssfRow.getCell(0);
                    XSSFCell realName = xssfRow.getCell(1);
                    XSSFCell studentNumber = xssfRow.getCell(2);
                    XSSFCell sex = xssfRow.getCell(3);
                    XSSFCell politicalLandscapeId = xssfRow.getCell(4);
                    XSSFCell placeOrigin = xssfRow.getCell(5);
                    student.setOrganizeId(Integer.valueOf(getValue(organizeId)));
                    student.setRealName(getValue(realName));
                    student.setStudentNumber(getValue(studentNumber));
                    student.setSex(getValue(sex));
                    student.setPoliticalLandscapeId(Integer.valueOf(getValue(politicalLandscapeId)));
                    student.setPlaceOrigin(getValue(placeOrigin));
                    list.add(student);
                }
            }
        }
        return list;
    }

    public List<StudentBean> readXls(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        StudentBean student = null;
        List<StudentBean> list = new ArrayList<StudentBean>();
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    student = new StudentBean();
                    HSSFCell organizeId = hssfRow.getCell(0);
                    HSSFCell realName = hssfRow.getCell(1);
                    HSSFCell studentNumber = hssfRow.getCell(2);
                    HSSFCell sex = hssfRow.getCell(3);
                    HSSFCell politicalLandscapeId = hssfRow.getCell(4);
                    HSSFCell placeOrigin = hssfRow.getCell(5);
                    student.setOrganizeId(Integer.valueOf(getValue(organizeId)));
                    student.setRealName(getValue(realName));
                    student.setStudentNumber(getValue(studentNumber));
                    student.setSex(getValue(sex));
                    student.setPoliticalLandscapeId(Integer.valueOf(getValue(politicalLandscapeId)));
                    student.setPlaceOrigin(getValue(placeOrigin));
                    list.add(student);
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
            DecimalFormat df = new DecimalFormat("#");
            return df.format(d);
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    private String getValue(HSSFCell hssfCell) {
        if(hssfCell.getCellType() == CellType.BOOLEAN){
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == CellType.NUMERIC){
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}
