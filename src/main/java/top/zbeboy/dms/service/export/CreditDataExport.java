package top.zbeboy.dms.service.export;

import org.apache.poi.ss.usermodel.Row;
import top.zbeboy.dms.service.util.ExportUtils;
import top.zbeboy.dms.web.bean.analyse.CreditBean;

import java.util.List;

public class CreditDataExport extends ExportUtils<CreditBean> {

    // 序号
    private int sequence = 0;

    public CreditDataExport(List<CreditBean> data) {
        super(data);
    }

    @Override
    public void createHeader(Row row) {
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("学号");
        row.createCell(3).setCellValue("班级");
        row.createCell(4).setCellValue("年份");
        row.createCell(5).setCellValue("学期");
        row.createCell(6).setCellValue("文体活动");
        row.createCell(7).setCellValue("技能特长");
        row.createCell(8).setCellValue("志愿公益");
        row.createCell(9).setCellValue("科技创新");
        row.createCell(10).setCellValue("任职经历");
        row.createCell(11).setCellValue("思想成长");
        row.createCell(12).setCellValue("实践实习");
        row.createCell(13).setCellValue("工作履历");
        row.createCell(14).setCellValue("学习成绩");
    }

    @Override
    public void createCell(Row row, CreditBean t) {
        sequence++;
        row.createCell(0).setCellValue(sequence);
        row.createCell(1).setCellValue(t.getRealName());
        row.createCell(2).setCellValue(t.getStudentNumber());
        row.createCell(3).setCellValue(t.getOrganizeName());
        row.createCell(4).setCellValue(t.getYear());
        row.createCell(5).setCellValue(t.getTerm());
        row.createCell(6).setCellValue(t.getSports());
        row.createCell(7).setCellValue(t.getSkills());
        row.createCell(8).setCellValue(t.getVoluntary());
        row.createCell(9).setCellValue(t.getTechnological());
        row.createCell(10).setCellValue(t.getPost());
        row.createCell(11).setCellValue(t.getIdeological());
        row.createCell(12).setCellValue(t.getPractical());
        row.createCell(13).setCellValue(t.getWork());
        row.createCell(14).setCellValue(t.getAchievement());
    }
}
