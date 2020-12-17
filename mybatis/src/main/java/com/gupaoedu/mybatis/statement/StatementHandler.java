package com.gupaoedu.mybatis.statement;

import com.gupaoedu.mybatis.config.GpConfiguration;
import com.gupaoedu.mybatis.config.MapperRegistory;
import com.gupaoedu.mybatis.result.ResultSetHandler;

import java.sql.*;

/**
 * Created by James
 * From 咕泡学院出品
 * 老师咨询 QQ 2904270631
 * <p>
 * 数据库操作和结果处理分发
 */
public class StatementHandler {
    private final GpConfiguration configuration;

    private final ResultSetHandler resultSetHandler;

    public StatementHandler(GpConfiguration configuration) {
        this.configuration = configuration;
        resultSetHandler = new ResultSetHandler(configuration);
    }

    public <E> E query(MapperRegistory.MapperData mapperData, Object parameter) throws Exception {
        try {
            //JDBC
            Connection conn = getConnection();
            //TODO ParameterHandler
            PreparedStatement pstmt = conn.prepareStatement(String.format(mapperData.getSql(),
                    Integer.parseInt(String.valueOf(parameter))));
            pstmt.execute();
            //ResultSetHandler
            this.configuration.newExecutor(resultSetHandler);
            return (E) resultSetHandler.handle(pstmt.getResultSet(), mapperData.getType());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Connection getConnection() throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.118.131:3306/xboot?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
