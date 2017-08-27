package com.shcem.utils.excel;

import com.shcem.utils.DateUtils;
import com.shcem.utils.ObjectUtils;
import com.shcem.utils.Reflections;
import com.shcem.utils.excel.annotation.ExcelField;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by lizhihua on 2017/4/12.
 */
public class ExcelHelper {
    private static ExcelHelper helper = new ExcelHelper();

    public static ExcelHelper Instance() {
        return helper;
    }

    /**
     * 错误信息
     */
    private StringBuilder errSB = new StringBuilder();
    /**
     * 工作薄对象
     */
    //private SXSSFWorkbook wb;
    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 将模型导出到Excel中
     *
     * @param os           输出流
     * @param models       模型List
     * @param sheetName
     * @param headRowIndex 表头行0开始
     */
    public <T> void Export2NewExcel(OutputStream os, List<T> models, Class<T> cls, String sheetName, int headRowIndex) throws Exception {
        SXSSFWorkbook wb = new SXSSFWorkbook();
        if (com.shcem.utils.StringUtils.isEmpty(sheetName)) {
            sheetName = "Sheet1";
        }
        Sheet sheet = wb.createSheet(sheetName);
        Export2Excel(sheet, models, cls, headRowIndex);
        try {
            wb.write(os);
        } catch (Exception ex) {
            throw new Exception("写入Excel失败!", ex);
        }
    }
/**将模型导出到文件
 * @param fileName 文件路径
 * @param models 数据List
 * @param sheetName SheetName
 * @param headRowIndex 表头行0开始
 * */
    public <T> void Export2NewExcel(String fileName, List<T> models, Class<T> cls, String sheetName, int headRowIndex) throws Exception {
        FileOutputStream fs = new FileOutputStream(fileName);
        Export2NewExcel(fs, models, cls, sheetName, headRowIndex);
    }
    /**
     * 将数据写入模板文件中
     * @param sheetName
     * @param templateFile 模板文件路径
     * @param os 输出流
     * @param models 数据
     * @param startRowIndex 第一行开始的index，从0开始
     * */
    public <T> void Export2TemplateExcel(String templateFile,OutputStream os,List<T> models, Class<T> cls,
                                         String sheetName, int startRowIndex)throws Exception{
        FileInputStream is=new FileInputStream(templateFile);
        if(com.shcem.utils.StringUtils.isEmpty(sheetName)){
            sheetName="Sheet1";
        }
        XSSFWorkbook wb = new XSSFWorkbook(is);
        Sheet sheet=wb.getSheet(sheetName);

        this.sheet=sheet;
        List<ExcelHeader> headers= GetHeaders(cls);
        Export2ExcelWithoutHeaders(models,cls,headers,startRowIndex);

        wb.write(os);
    }
    /**
     * 将数据写入模板文件中
     * @param sheetName
     * @param templateFile 模板文件路径
     * @param newFile 输出文件
     * @param models 数据
     * @param startRowIndex 第一行开始的index，从0开始
     * */
    public <T> void Export2TemplateExcel(String templateFile,String newFile,List<T> models, Class<T> cls,
                                         String sheetName, int startRowIndex)throws Exception{
        FileOutputStream os=new FileOutputStream(newFile);
        Export2TemplateExcel(templateFile,os,models,cls,sheetName,startRowIndex);
    }

    private <T> void Export2Excel(Sheet sheet, List<T> models, Class<T> cls, int headRowIndex) {
        this.sheet = sheet;
        List<ExcelHeader> headers = GetHeaders(cls);
        Row headRow = this.AddRow(headRowIndex);
        SetHeaders(headRow, headers);
        headRowIndex++;
        Export2ExcelWithoutHeaders(models,cls,headers,headRowIndex);
    }
    private <T> void Export2ExcelWithoutHeaders(List<T> models,Class<T> cls,List<ExcelHeader> headers,int startRowIndex){
        try {
            SetVal(models, headers, startRowIndex);
        } catch (Exception e) {
            errSB.append("\r\n导出到Excel出错!error:" + e.toString());
        }
    }

    private <T> void SetVal(List<T> models, List<ExcelHeader> headers, int rowIndex)
            throws IllegalArgumentException, IllegalAccessException {
        for (T t : models) {
            Row row = this.AddRow(rowIndex);
            int colIndex = 0;
            for (ExcelHeader header:headers){
                Object val=Reflections.InvokeGetter(t,header.getField().getName());
                this.addCell(row,header.getColumnIndex(),val);
            }

            rowIndex++;
        }
    }

    /**
     * 设置Excel 头部
     */
    private void SetHeaders(Row headRow, List<ExcelHeader> headers) {
        for(ExcelHeader header:headers){
            this.addCell(headRow,header.getColumnIndex(),header.getTitle());
        }
    }

    private List<ExcelHeader> GetHeaders(Class<?> cls) {
        Field[] fields = cls.getDeclaredFields();
        List<ExcelHeader> headers = new ArrayList<ExcelHeader>(fields.length);

        for (Field field : fields) {
            ExcelField ef = field.getAnnotation(ExcelField.class);
            if (ef == null) {
                continue;
            }

            ExcelHeader header=new ExcelHeader();
            header.setColumnIndex(ef.ColumnIndex());
            header.setField(field);
            header.setTitle(ef.title());

            headers.add(ef.ColumnIndex(),header);
        }
        return headers;
    }

    private Row AddRow(int rownum) {
        return sheet.createRow(rownum);
    }

    /**
     * 添加一个单元格
     *
     * @param row    添加的行
     * @param column 添加列号
     * @param val    添加值
     * @return 单元格对象
     */
    private Cell addCell(Row row, int column, Object val) {
        return this.addCell(row, column, val, 0, Class.class);
    }
    /**获取一个单元格数据
     * @param row
     * @param column
     * */
    private Object getCellValue(Row row,int column){
        Object val = null;
        try{
            Cell cell = row.getCell(column);
            if (cell != null){
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    if(HSSFDateUtil.isCellDateFormatted(cell)){
                        val= DateUtils.FormatDate(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                    }else{
                        val = cell.getNumericCellValue();
                    }
                }else if (cell.getCellType() == Cell.CELL_TYPE_STRING){
                    val = cell.getStringCellValue();
                }else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA){
                    val = cell.getCellFormula();
                }else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
                    val = cell.getBooleanCellValue();
                }else if (cell.getCellType() == Cell.CELL_TYPE_ERROR){
                    val = cell.getErrorCellValue();
                }
            }
        }catch (Exception e) {
            return val;
        }
        return val;
    }

    /**
     * 添加一个单元格
     *
     * @param row    添加的行
     * @param column 添加列号
     * @param val    添加值
     * @param align  对齐方式（1：靠左；2：居中；3：靠右）
     * @return 单元格对象
     */
    public Cell addCell(Row row, int column, Object val, int align, Class<?> fieldType) {
        Cell cell = row.createCell(column);
        String cellFormatString = "@";
        try {
            if (val == null) {
                cell.setCellValue("");
            } else if (fieldType != Class.class) {
                cell.setCellValue((String) fieldType.getMethod("setValue", Object.class).invoke(null, val));
            } else {
                if (val instanceof String) {
                    cell.setCellValue((String) val);
                } else if (val instanceof Integer) {
                    cell.setCellValue((Integer) val);
                    cellFormatString = "0";
                } else if (val instanceof Long) {
                    cell.setCellValue((Long) val);
                    cellFormatString = "0";
                } else if (val instanceof Double) {
                    cell.setCellValue((Double) val);
                    cellFormatString = "0.00";
                } else if (val instanceof Float) {
                    cell.setCellValue((Float) val);
                    cellFormatString = "0.00";
                } else if (val instanceof Date) {
                    cell.setCellValue((Date) val);
                    cellFormatString = "yyyy-MM-dd HH:mm";
                } else {
                    cell.setCellValue((String) Class.forName(this.getClass().getName().replaceAll(this.getClass().getSimpleName(),
                            "fieldtype." + val.getClass().getSimpleName() + "Type")).getMethod("setValue", Object.class).invoke(null, val));
                }
            }
//            if (val != null){
//                CellStyle style = styles.get("data_column_"+column);
//                if (style == null){
//                    style = wb.createCellStyle();
//                    style.cloneStyleFrom(styles.get("data"+(align>=1&&align<=3?align:"")));
//                    style.setDataFormat(wb.createDataFormat().getFormat(cellFormatString));
//                    styles.put("data_column_" + column, style);
//                }
//                cell.setCellStyle(style);
//            }
        } catch (Exception ex) {
            //log.info("Set cell value ["+row.getRowNum()+","+column+"] error: " + ex.toString());
            errSB.append("\r\nSet cell value [" + row.getRowNum() + "," + column + "] error: " + ex.toString());
            cell.setCellValue("");
        }
        return cell;
    }

    public <T> List<T> Excel2List(String filename,String sheetName, Class<T> cls,int startRowIndex) throws Exception {
        FileInputStream is=new FileInputStream(filename);
        XSSFWorkbook wb=new XSSFWorkbook(is);
        if(com.shcem.utils.StringUtils.isEmpty(sheetName)){
            sheetName="Sheet1";
        }
        List<T> list=new ArrayList<T>();
        Sheet sheet=wb.getSheet(sheetName);
        int rowIndex= sheet.getPhysicalNumberOfRows();
        for(int i=startRowIndex;i<rowIndex;i++){
            Row row=sheet.getRow(startRowIndex);
            T t=GetRowData(row,cls);
            list.add(t);
        }

        return list;
    }
    private <T> T GetRowData(Row row,Class<T> cls)throws InstantiationException, IllegalAccessException{
        List<ExcelHeader> headers=GetHeaders(cls);
        T t =cls.newInstance();
        for(ExcelHeader header:headers){
            Object val=getCellValue(row,header.getColumnIndex());
            Class<?> cl= header.getField().getType();

            if(cl.equals(int.class)){
                Reflections.InvokeSetter(t,header.getField().getName(),((Double)val).intValue());
            }else if(cl.equals(float.class)){
                Reflections.InvokeSetter(t,header.getField().getName(),((Double)val).floatValue());
            } else if(cl.equals(Date.class)){
                Date dd=DateUtils.ParseDate(val.toString());
                Reflections.InvokeSetter(t,header.getField().getName(),dd);
            } else {
                Reflections.InvokeSetter(t,header.getField().getName(),val);
            }

        }
        return t;
    }
}
