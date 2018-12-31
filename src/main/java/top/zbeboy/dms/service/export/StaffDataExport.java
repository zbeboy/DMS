package top.zbeboy.dms.service.export;

import org.apache.poi.ss.usermodel.Row;
import top.zbeboy.dms.service.util.ExportUtils;
import top.zbeboy.dms.web.bean.data.staff.StaffBean;

import java.util.List;

public class StaffDataExport extends ExportUtils<StaffBean> {

    // 序号
    private int sequence = 0;

    public StaffDataExport(List<StaffBean> data) {
        super(data);
    }

    @Override
    public void createHeader(Row row) {
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("工号");
        row.createCell(3).setCellValue("系");
        row.createCell(4).setCellValue("性别");
        row.createCell(5).setCellValue("政治面貌");
    }

    @Override
    public void createCell(Row row, StaffBean t) {
        sequence++;
        row.createCell(0).setCellValue(sequence);
        row.createCell(1).setCellValue(t.getRealName());
        row.createCell(2).setCellValue(t.getStaffNumber());
        row.createCell(3).setCellValue(t.getDepartmentName());
        row.createCell(4).setCellValue(t.getSex());
        row.createCell(5).setCellValue(t.getPoliticalLandscapeName());
    }
}
