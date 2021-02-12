package com.example.a_middlewares.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;;

import org.apache.poi.ss.usermodel.*;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

/**
 * parse excel with merged cells
 */
public class app {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\workstation\\demo\\javaTest\\src\\main\\resources\\DemoImport.xls");
        InputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);// just get the first sheet

        // parse title
        int titleRowIndex = 1; // start from 0
        Row titleRow = sheet.getRow(titleRowIndex);
        // List<String> colTitle = new ArrayList<>();
        Map<String, Integer> colTitle = new LinkedHashMap<>();// map with order item
        for (int i = 0; i < titleRow.getLastCellNum(); i++) {
            int colIndex = i + 1;
            Cell cell = titleRow.getCell(i);
            if (StringUtils.isEmpty(cell) || StringUtils.isEmpty(cell.toString())) {
                cell = sheet.getRow(titleRowIndex - 1).getCell(i);// get title from previous row
            }
            if (StringUtils.isEmpty(cell) || StringUtils.isEmpty(cell.toString())) {
                throw new Exception("Title is empty at col: " + colIndex);
            }
            // colTitle.add(cell.getStringCellValue());
            String title = cell.getStringCellValue();
            if (colTitle.keySet().contains(title)) {
                Integer count = colTitle.get(title);
                colTitle.put(title, ++count);
                colTitle.put(title + ("" + count), 0);
            } else {
                colTitle.put(title, 0);
            }
        }

        // parse row data
        List<ArrayList<Object>> list = new ArrayList<>();
        int colTotalNumber = colTitle.size();
        for (int i = titleRowIndex + 1; i < sheet.getPhysicalNumberOfRows(); i++) { // getLastRowNum
            Row row = sheet.getRow(i);
            ArrayList<Object> curRow = new ArrayList<>();
            for (int j = 0; j < colTotalNumber; j++) {// row.getLastCellNum() getPhysicalNumberOfCells()
                curRow.add(getCellValue(row, j));
            }
            list.add(curRow);
        }

        // Convert data from Excel to Entity list
        List<UserEntity> dataList = parseData(colTitle.keySet(), list, UserEntity.class);
        System.out.println(dataList.size());
    }
    private static int getColIndex(Set<String> colTitle, String val) {
        int i = 0;
        for (String str : colTitle) {
            if (str.equals(val)) {
                return i;
            }
            i++;
        }
        return -1;
    }
    private static Map<Field, ExcelCol> getFields(Class cls) {
        // List<Field> fieldList = new ArrayList<>();
        Map<Field, ExcelCol> fieldList = new LinkedHashMap<>();

        // Get anno from Class
        // Field[] fields = cls.getDeclaredFields();
        // for (Field f : fields) {
        //     if (f.isAnnotationPresent(ExcelCol.class)) {
        //         // fieldList.add(f);
        //         fieldList.put(f, f.getAnnotation(ExcelCol.class));
        //     }
        // }
        while(cls.getSuperclass() != null && !cls.getSimpleName().equals("Object") ) {
            for (Field f : cls.getDeclaredFields()) {
                if (f.isAnnotationPresent(ExcelCol.class)) {
                    fieldList.put(f, f.getAnnotation(ExcelCol.class));
                }
            }
            cls = cls.getSuperclass();
        }

        return fieldList;
    }
    private static <T> List<T> parseData(Set<String> colTitle, List<ArrayList<Object>> rowData, Class<T> cls) throws Exception {
        Map<Field, ExcelCol> fieldList = getFields(cls);

        List<T> dataList = new ArrayList<>();
        // for each all data rows
        int rowIndex = 0;
        for (ArrayList<Object> curRow : rowData) {
            rowIndex++;
            T t = cls.newInstance();
            for (Map.Entry<Field, ExcelCol> f : fieldList.entrySet()) {
                // parse data base on field
                Field field = f.getKey();
                ExcelCol col = f.getValue();
                String titleStr = col.title();
                if (col.index() > 0) {
                    titleStr = titleStr + col.index();
                }
                int colIndex = getColIndex(colTitle, titleStr);
                if (colIndex < 0) {// ignore the field if col is not exist
                    continue;
                }

                Object colVal = null;
                if (col.type() == Date.class) {
                    if (curRow.get(colIndex) == null || curRow.get(colIndex).getClass() != Date.class) {
                        throw new Exception("Date type cell error at [row: " + rowIndex + ", col: " + colIndex + "]");
                    }
                    colVal = cn.hutool.core.date.DateUtil.format((Date) curRow.get(colIndex), col.format());
                } else {
                    colVal = curRow.get(colIndex);
                }
                // set val for this field
                // ReflectUtils.invokeSetter(t, field.getName(), colVal);
                field.set(t, colVal);
            }
            dataList.add(t);
        }

        return dataList;
    }

    public static Object getCellValue(Row row, int column) {
        if (row == null) {
            return row;
        }
        Object val = "";
        try {
            Cell cell = row.getCell(column);
            if (!StringUtils.isEmpty(cell)) {
                CellType cellType = cell.getCellType();
                if (cellType == CellType.NUMERIC || cellType == CellType.FORMULA) {
                    val = cell.getNumericCellValue();
                    if (DateUtil.isCellDateFormatted(cell)) {
                        val = DateUtil.getJavaDate((Double) val); // POI Excel 日期格式转换
                    } else {
                        if ((Double) val % 1 > 0) {
                            val = new BigDecimal(val.toString());
                        } else {
                            // val = new DecimalFormat("0").format(val);
                            val = ((Double) val).intValue();
                        }
                    }
                } else if (cellType == CellType.STRING) {
                    val = cell.getStringCellValue().trim();
                } else if (cellType == CellType.BOOLEAN) {
                    val = cell.getBooleanCellValue();
                } else if (cellType == CellType.ERROR) {
                    val = cell.getErrorCellValue();
                }
            }
        } catch (Exception e) {
            return val;
        }
        return val;
    }
}
