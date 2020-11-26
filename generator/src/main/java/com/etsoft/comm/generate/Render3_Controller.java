package com.etsoft.comm.generate;

import com.etsoft.comm.tool.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Render3_Controller {

    public static List<String> ignoreFieldList = new ArrayList<>();
    static {
        ignoreFieldList.add("id");
        ignoreFieldList.add("del_flag");
    }

    public static void create(GContext gCtx) throws Exception {
        String fileStr = gCtx.getControllerSampleFileStr();

        // 开始替换
        fileStr = fileStr.replaceFirst("##className#", gCtx.getClassName(false));
        fileStr = fileStr.replaceFirst("##ClassNameSuffix#", gCtx.getControllerClassNameSuffix());
        fileStr = fileStr.replace("##ClassEntityName#", gCtx.getEntityClassNameSuffix());
        fileStr = fileStr.replace("##ClassServiceName#", gCtx.getServiceClassNameSuffix());
        // fileStr = fileStr.replace("##pkFieldCamel#", StringUnit.getCamelName(pkField, false));

        // Pagination Query List
        ResultSet l_rset = gCtx.getL_rset();
        MyStringBuilder sb = new MyStringBuilder();
        for (int i = 1; i <= l_rset.getMetaData().getColumnCount(); i++) {
            String col_name = l_rset.getMetaData().getColumnName(i).toLowerCase();
            if (ignoreFieldList.indexOf(col_name) > -1) { // exsit in the ignore fields
                continue;
            }
            String colName = StringUnit.getCamelName(col_name, false);
            String ColName = StringUnit.getCamelName(col_name, true);

            String javaType = TypeHelper.getTypeFromDb2Java(l_rset.getMetaData(), i);
            if ("Date".equals(javaType)) {
                // sb.appendLine("\t\tif (bean.get" + ColName + "() != null) {");
                // sb.appendLine("\t\t\tqw.eq(\"" + col_name + "\", bean.get" + ColName + "());");
                // sb.appendLine("\t\t}");
                sb.appendLine("\t\tif (!StringUtils.isEmpty(request.getParameter(\"" + colName + "_01\"))) {");
                sb.appendLine("\t\t\tString[] dateRange = request.getParameter(\"" + colName + "_01\").split(\",\");");
                sb.appendLine("\t\t\tif (dateRange.length > 1) {// date range");
                sb.appendLine("\t\t\t\tDate end = DateUtil.parse(dateRange[1]);");
                sb.appendLine("\t\t\t\tqw.between(\"" + col_name + "\", dateRange[0], DateUtil.endOfDay(end));");
                sb.appendLine("\t\t\t} else if (dateRange.length == 1) {// date");
                sb.appendLine("\t\t\t\tDate end = DateUtil.parse(dateRange[0]);");
                sb.appendLine("\t\t\t\tqw.between(\"" + col_name + "\", dateRange[0], DateUtil.endOfDay(end));");
                sb.appendLine("\t\t\t}");
                sb.appendLine("\t\t}");
            } else if ("String".equals(javaType)) {
                sb.appendLine("\t\tif (!StringUtils.isEmpty(bean.get" + ColName + "())) {");
                sb.appendLine("\t\t\tqw.like(\"" + col_name + "\", bean.get" + ColName + "());");
                sb.appendLine("\t\t}");
            } else {
                sb.appendLine("\t\tif (bean.get" + ColName + "() != null) {");
                sb.appendLine("\t\t\tqw.eq(\"" + col_name + "\", bean.get" + ColName + "());");
                sb.appendLine("\t\t}");
            }
        }
        fileStr = fileStr.replaceFirst("\t\t##PaginationQueryList#", sb.toString());

        // 输出内容
        FileHelper.writeFile(gCtx.getControllerFileFullPath(), gCtx.getTable_name(), fileStr);
    }

}
