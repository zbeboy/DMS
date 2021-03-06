package top.zbeboy.dms.service.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportUtils<T> {

    private List<T> data;

    public ExportUtils(List<T> data) {
        this.data = data;
    }

    public Boolean exportExcel(String outputPath, String fileName, String ext) throws IOException {
        boolean isCreate = false;
        Workbook wb = null;
        if (ext.equalsIgnoreCase(top.zbeboy.dms.config.Workbook.XLS_FILE)) {
            wb = new HSSFWorkbook();
        } else if (ext.equalsIgnoreCase(top.zbeboy.dms.config.Workbook.XLSX_FILE)) {
            wb = new XSSFWorkbook();
        }

        if (!ObjectUtils.isEmpty(wb)) {
            Sheet sheet = wb.createSheet("new sheet");
            Row row = sheet.createRow(0);
            createHeader(row);
            for (int i = 0; i < data.size(); i++) {
                row = sheet.createRow(i + 1);
                createCell(row, data.get(i));
            }
            File saveFile = new File(outputPath, fileName + "." + ext);
            if (!saveFile.getParentFile().exists()) {//create file
                saveFile.getParentFile().mkdirs();
            }
            // Write the output to a file
            FileOutputStream fileOut = new FileOutputStream(outputPath + fileName + "." + ext);
            wb.write(fileOut);
            fileOut.close();
            isCreate = true;
        }
        return isCreate;
    }

    /**
     * excel 标题
     *
     * @param row 列
     */
    public void createHeader(Row row) {
        // 实现
    }

    /**
     * 每行内容
     *
     * @param row 列
     * @param t   对象
     */
    public void createCell(Row row, T t) {
        // 实现
    }

    /**
     * 每行内容
     *
     * @param row   列
     * @param t     对象
     * @param style 列格式
     */
    public void createCell(Row row, T t, CellStyle style) {
        // 实现
    }
}
