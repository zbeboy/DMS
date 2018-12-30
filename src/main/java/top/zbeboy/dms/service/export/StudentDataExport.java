package top.zbeboy.dms.service.export;

import org.apache.poi.ss.usermodel.Row;
import top.zbeboy.dms.service.util.ExportUtils;
import top.zbeboy.dms.web.bean.data.student.StudentBean;

import java.util.List;

public class StudentDataExport extends ExportUtils<StudentBean> {

    // 序号
    private int sequence = 0;

    public StudentDataExport(List<StudentBean> data) {
        super(data);
    }

    @Override
    public void createHeader(Row row) {
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("学号");
        row.createCell(3).setCellValue("班级");
        row.createCell(4).setCellValue("性别");
        row.createCell(5).setCellValue("政治面貌");
        row.createCell(6).setCellValue("生源地");
    }

    @Override
    public void createCell(Row row, StudentBean t) {
        sequence++;
        row.createCell(0).setCellValue(sequence);
        row.createCell(1).setCellValue(t.getRealName());
        row.createCell(2).setCellValue(t.getStudentNumber());
        row.createCell(3).setCellValue(t.getOrganizeName());
        row.createCell(4).setCellValue(t.getSex());
        row.createCell(5).setCellValue(t.getPoliticalLandscapeName());
        row.createCell(6).setCellValue(t.getPlaceOrigin());
    }
}
