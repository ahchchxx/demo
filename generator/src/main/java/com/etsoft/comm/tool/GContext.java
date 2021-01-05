package com.etsoft.comm.tool;

import com.etsoft.comm.db.DBHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GContext {
    //	public static String sampleFilePath = System.getProperty("user.dir") + "/sample/SampleDao.java";
    public static String sampleFilePath = System.getProperty("java.class.path").split(";")[0];

    private final String entityFoldSuffixStr = "entity";
    private final String entityFileSuffixStr = "Info";

    private final String daoFoldSuffixStr = "mapper";//"dao";
    private final String daoFileSuffixStr = "Dao";
    private final String xmlFoldSuffixStr = "mapperXml";//"mapper xml";
    private final String xmlFileSuffixStr = "Mapper";

    private final String serviceFoldSuffixStr = "service";
    private final String serviceFileSuffixStr = "Service";

    private final String serviceImplFoldSuffixStr = "service\\impl";
    private final String serviceImplFileSuffixStr = "ServiceImpl";

    private final String controllerFoldSuffixStr = "controller";
    private final String controllerFileSuffixStr = "Controller";

    private final String tablePrefixName = "t_";

    public static List<String> defaultList = new ArrayList<>();
    static {
        defaultList.add("id");
        defaultList.add("create_by");
        defaultList.add("create_time");
        defaultList.add("update_by");
        defaultList.add("update_time");
        defaultList.add("del_flag");
    }

    Connection l_conn = DBHelper.getConnection();


    private String table_name;
    private String filePath;
    private ResultSet l_rset;

    public GContext(String table_name, String filePath) throws SQLException {
        this.table_name = table_name;
        this.filePath = filePath;
        this.l_rset = l_conn.prepareStatement("select * from " + table_name + " limit 1").executeQuery();
    }

    public boolean is_date_exsit() throws SQLException {
        boolean l_date = false;// 有无时间类型
        for (int i = 1; i <= l_rset.getMetaData().getColumnCount(); i++) {
            String col_name = l_rset.getMetaData().getColumnName(i).toLowerCase();
            if (defaultList.indexOf(col_name) >= 0) {
                continue; // 这些封装在父类里了
            }
            if (TypeHelper.isDateType(l_rset.getMetaData(), i)) {
                l_date = true; break;
            }
        }
        return l_date;
    }
    public ResultSet getL_rset() {
        return l_rset;
    }

    public String getTable_name() {
        return table_name;
    }
    /*
    * get name for entity
     */
    public String getClassName(boolean isFirstUpperCase) {
        return StringUnit.getCamelName(table_name, tablePrefixName, isFirstUpperCase);
    }

    // 表名
    // String table_name = ctx.getTable_name();// "hr_org";
    // 类名，驼峰命名法，首字母大写
    // String ClassName = ctx.getClassName();
    // String ClassNameSuffix = ClassName + suffixStr;
    public String getEntityClassNameSuffix() {
        return getClassName(true) + entityFileSuffixStr;
    }
    public String getDaoClassNameSuffix() {
        return getClassName(true) + daoFileSuffixStr;
    }
    public String getDaoXmlClassNameSuffix() {
        return getClassName(true) + xmlFileSuffixStr;
    }
    public String getServiceClassNameSuffix() {
        return getClassName(true) + serviceFileSuffixStr;
    }
    public String getServiceImplClassNameSuffix() {
        return getClassName(true) + serviceImplFileSuffixStr;
    }
    public String getControllerClassNameSuffix() {
        return getClassName(true) + controllerFileSuffixStr;
    }

    // String fileName = filePath + ClassNameSuffix + ".java";
    public String getEntityFileFullPath(){
        String fullPath = filePath + entityFoldSuffixStr + "\\";
        FileHelper.checkDirectoryExsit(fullPath);
        return fullPath + getEntityClassNameSuffix() + ".java";
    }
    public String getDaoFileFullPath(){
        String fullPath = filePath + daoFoldSuffixStr + "\\";
        FileHelper.checkDirectoryExsit(fullPath);
        return fullPath + getDaoClassNameSuffix() + ".java";
    }
    public String getDaoXmlFileFullPath(){
        String fullPath = filePath + xmlFoldSuffixStr + "\\";
        FileHelper.checkDirectoryExsit(fullPath);
        return fullPath + getDaoXmlClassNameSuffix() + ".xml";
    }
    public String getServiceFileFullPath(){
        String fullPath = filePath + serviceFoldSuffixStr + "\\";
        FileHelper.checkDirectoryExsit(fullPath);
        return fullPath + getServiceClassNameSuffix() + ".java";
    }
    public String getServiceImplFileFullPath(){
        String fullPath = filePath + serviceImplFoldSuffixStr + "\\";
        FileHelper.checkDirectoryExsit(fullPath);
        return fullPath + getServiceImplClassNameSuffix() + ".java";
    }
    public String getControllerFileFullPath(){
        String fullPath = filePath + controllerFoldSuffixStr + "\\";
        FileHelper.checkDirectoryExsit(fullPath);
        return fullPath + getControllerClassNameSuffix() + ".java";
    }

    public String getEntitySampleFileStr(){
        return FileHelper.readToString(sampleFilePath + "/sample/SampleEntity.java");
    }
    public String getDaoSampleFileStr(){
        return FileHelper.readToString(sampleFilePath + "/sample/SampleDao.java");
    }
    public String getDaoXmlSampleFileStr(){
        return FileHelper.readToString(sampleFilePath + "/sample/SampleDaoXml.xml");
    }
    public String getServiceSampleFileStr(){
        return FileHelper.readToString(sampleFilePath + "/sample/SampleService.java");
    }
    public String getServiceImplSampleFileStr(){
        return FileHelper.readToString(sampleFilePath + "/sample/SampleServiceImpl.java");
    }
    public String getControllerSampleFileStr(){
        return FileHelper.readToString(sampleFilePath + "/sample/SampleController.java");
    }

    public String getPK() throws Exception {
        String pkField = "";// 默认主键只有一个
        // 主键
        ResultSet l_column_key = l_conn.getMetaData().getPrimaryKeys(null, null, table_name.toUpperCase());
        while (l_column_key.next()) {
            for (int i = 1; i <= l_column_key.getMetaData().getColumnCount(); i++) {
                if (!StringUnit.isNullOrEmpty(l_column_key.getString("column_name"))) {
                    pkField = l_column_key.getString("column_name").toLowerCase();
                    break;
                }
            }
        }
        if(StringUnit.isNullOrEmpty(pkField)) {
            throw new Exception(table_name + " 表没有主键");
        }

        return pkField;
    }
}
