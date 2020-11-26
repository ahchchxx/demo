package com.etsoft.comm.db;

import com.etsoft.comm.tool.StringUnit;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBHelper {
    private static  Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    //加载驱动
    static {
        try {
            //方式一  固定写死  不方便更改数据库操作
//          Class.forName("com.mysql.jdbc.Driver"); //Oracle驱动为: oracle.jdbc.driver.OracleDriver

        	//方式二  读取配置文件操作  如果需要更换数据库只需要修改配置文件即可   推荐
            try {
                Class.forName(MyProperties.getInstance().getProperty("driver"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //驱动管理连接指定数据库  获取连接对象
    public static Connection getConnection() {
        try {
            //方式一   固定写死  不方便更改数据库操作
            //驱动管理指定数据库并连接          参数说明  端口信息  数据库账户  密码
//          conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "a");
        	
            //方式二  读取配置文件操作  如果需要更换数据库只需要修改配置文件即可   推荐
//            	Properties props = new Properties();
//            	props.put("remarksReporting", "true"); // 不配置的话，得不到备注
//            	conn = DriverManager.getConnection(MyProperties.getInstance().getProperty("url"), props);

//        	Properties props = new Properties();
//        	props.put("remarksReporting", "true"); // 不配置的话，得不到备注
//            String url = "jdbc:Access:///" + System.getProperty("user.dir") + "/App_Data/Web.mdb";
//        	conn = DriverManager.getConnection(url, props);

			MyProperties property = MyProperties.getInstance();
			Properties props = new Properties();
			props.put("remarksReporting", "true"); // 不配置的话，得不到备注
			String url = property.getProperty("url")
					+ "&user=" + property.getProperty("username")
					+ "&password=" + property.getProperty("password");
			conn = DriverManager.getConnection(url, props);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
//	public static Connection getConn() {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
//			Properties props = new Properties();
//			props.put("remarksReporting", "true"); // 不配置的话，得不到备注
//			props.put("user", "knflp");
//			props.put("password", "knflp123");
//			
//			//Connection l_ret = DriverManager.getConnection("jdbc:oracle:thin:@172.16.1.106:1521/mcp", props);
//			Connection l_ret = DriverManager.getConnection ("jdbc:oracle:thin:@172.21.126.163:1521:orcl",props);
//					//"knflp", "knflp123");
//			
//			l_ret.setAutoCommit(true);
//			return l_ret;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return null;
//	}

    //关闭所有对象、连接、预处理、结果集
    private static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) {
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    /**
	 * 设置预处理的参数
	 * @param ps      预处理
	 * @param params 参数集合
	 * @throws SQLException 
	 */
	private static void setParams(PreparedStatement ps, List<Object> params) throws SQLException {
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
		}
	}

//	/**
//	 * 多条语句的增、删、改     说明:这些语句要么同时成功，要么都失败
//	 * @param sqls   多条sql语句
//	 * @param params 执行参数
//	 * @return
//	 */
//	public static int excuteQueryList(List<String> sqls, List<Object> params) {
//	    int result = 0;
//	    //获取连接
//	    conn = getConnection();
//	    try {
//	        //多条语句的执行涉及到事务    设置事务提交方式为手动
//			conn.setAutoCommit(false);
//			// 判断sql语句集合
//			if (sqls != null && sqls.size() > 0) {
//				// 循环每一条语句执行
//				for (int i = 0; i < sqls.size(); i++) {
//					ps = conn.prepareStatement(sqls.get(i));
//					// 设置参数
//					ps.setObject(i + 1, params.get(i));
//					// 执行并反馈
//					result = ps.executeUpdate();
//				}
//			}
//			// 手动提交数据
//			conn.commit();
//	    } catch (SQLException e) {
//	        //出现错误则回滚数据
//	        try {
//	            conn.rollback();
//	        } catch (SQLException e1) {
//	            e1.printStackTrace();
//	        }
//	        e.printStackTrace();
//	    } finally {
//	        //回复事务自动提交
//	        try {
//	            conn.setAutoCommit(true);
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	        //关闭所有对象
//	        closeAll(conn, ps, rs);
//	    }
//	    return result;
//	}

	/**单条语句的 增、删、改   说明:在执行成功后函数返回的结果为非零整数
     * @param sqlStr 需要执行的 sql 语句
     * @param params 执行 sql 语句的参数
     * @return
     */
    public static int executeNonQuery(String sqlStr, List<Object> params) {
		int result = 0;
		// 获取连接
		conn = getConnection();
		// 预处理
		try {
			ps = conn.prepareStatement(sqlStr);
			// 设置参数得到结果集
			setParams(ps, params);
			// 预处理执行得到结果反馈
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭所有对象
			closeAll(conn, ps, rs);
		}
		return result;
    }


//    /**
//     * 查询sql语句单条结果
//     * @param sql   查询的sql语句
//     * @param params执行sql所需参数
//     * @return
//     */
//	public static Map<String, Object> getSingleObject(String sqlStr, List<Object> params) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		// 获取连接
//		conn = getConnection();
//		// 预处理
//		try {
//			ps = conn.prepareStatement(sqlStr);
//			// 设置参数
//			setParams(ps, params);
//			// 执行查询得到结果集
//			rs = ps.executeQuery();
//			// 获取数据库该表所有字段名
//			List<String> names = getAllColumnName(rs);
//			if (rs.next()) {
//				// 循环names
//				for (String name : names) {
//					map.put(name, rs.getObject(name));
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			// 关闭对象
//			closeAll(conn, ps, rs);
//		}
//		return map;
//    }

	public static <T> T getSingleObject(String sqlStr, List<Object> params, Class<T> cls) {
		T instanceT = null;
		// 获取连接
		conn = getConnection();
		// 预处理
		try {
			ps = conn.prepareStatement(sqlStr);
			// 设置参数
			setParams(ps, params);
			// 执行查询得到结果集
			rs = ps.executeQuery();
			// 获取数据库该表所有字段名
			List<String> names = getAllColumnName(rs);
			if (rs.next()) {
				instanceT = cls.newInstance();
				for (int i = 0; i < names.size(); i++) {
					String col_name = names.get(i);// 获得列名
					
					Object value = rs.getObject(col_name);// 获得列所对应的值
					
					Field field = instanceT.getClass().getDeclaredField(StringUnit.getCamelName(col_name, false));// 获得实体对象的字段
					//field.setAccessible(true);// 给私有属性设置可访问权
					
					if(null == value) {// 字段值为 null，不赋值
						continue;
					}
					String col_type = value.getClass().getName();
//					System.out.println(col_type);
					if(col_type.equals("java.math.BigDecimal")) {
						field.set(instanceT, ((BigDecimal)value).longValue());// 给对象的私有属性赋值
					} else {
						field.set(instanceT, value);// 给对象的私有属性赋值
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭对象
			closeAll(conn, ps, rs);
		}
		
		return instanceT;
	}
	

//    /**
//     * 查询sql语句多条结果
//     * @param sql
//     * @param params
//     * @return
//     */
//	public static List<Map<String, Object>> executeQuery(String sqlStr, List<Object> params) {
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		// 连接
//		conn = getConnection();
//		// 预处理
//		try {
//			ps = conn.prepareStatement(sqlStr);
//			// 设置参数
//			setParams(ps, params);
//			// 执行查询得到结果集
//			rs = ps.executeQuery();
//			// 获取所有列名
//			List<String> names = getAllColumnName(rs);
//			while (rs.next()) { // 注意这里不能用if 因为使用if的话只能执行一次 最后得到的结果就成了查一条数据
//				Map<String, Object> map = new HashMap<String, Object>();
//				// 循环迭代
//				for (String name : names) {
//					map.put(name, rs.getObject(name));
//				}
//				list.add(map);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//    }
	
//    /**
//     * 查询sql语句多条结果
//     * @param sql
//     * @param params
//     * @return
//     */
//	public static List<LinkedHashMap<String, Object>> executeQuery(String sqlStr, List<Object> params) {
//		List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
//		// 连接
//		conn = getConnection();
//		// 预处理
//		try {
//			ps = conn.prepareStatement(sqlStr);
//			// 设置参数
//			setParams(ps, params);
//			// 执行查询得到结果集
//			rs = ps.executeQuery();
//			// 获取所有列名
//			List<String> names = getAllColumnName(rs);
//			while (rs.next()) { // 注意这里不能用if 因为使用if的话只能执行一次 最后得到的结果就成了查一条数据
//				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
//				// 循环迭代
//				for (String name : names) {
//					map.put(name, rs.getObject(name));
//				}
//				list.add(map);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//    }
	
    /**
     * 查询sql语句多条结果
     * @param sql
     * @param params
     * @return
     */
//	public static Table executeQueryTable(String sqlStr, List<Object> params) {
//		Table table = null;
//		// 连接
//		conn = getConnection();
//		// 预处理
//		try {
//			ps = conn.prepareStatement(sqlStr);
//			// 设置参数
//			setParams(ps, params);
//			// 执行查询得到结果集
//			rs = ps.executeQuery();
//			// 获取所有列名
//			//List<String> names = getAllColumnName(rs);
//			//table = new Table(names.toArray(new String[names.size()]));
//			ResultSetMetaData rsmd = rs.getMetaData();
//			String[] names = new String[rsmd.getColumnCount()];
//			String[] colNames = new String[rsmd.getColumnCount()];// 驼峰命名，Table的列名
//			for (int i = 0; i < rsmd.getColumnCount(); i++) {
//				names[i] = rsmd.getColumnName(i + 1);
//				colNames[i] = StringUnit.getCamelName(names[i], false);
//			}
//			table = new Table(colNames);
//			while (rs.next()) { // 注意这里不能用if 因为使用if的话只能执行一次 最后得到的结果就成了查一条数据
//				Row row = table.addRow();
//				// 循环迭代
//				//for (String name : names) {
//				for(int i = 0; i< names.length; i++) {
//					String name = names[i];
//					String colName = colNames[i];
//					Object objVal = rs.getObject(name);
//					if(objVal == null) {
//						continue;
//					}
//
//					if(objVal instanceof java.sql.Timestamp) {
//						// row.addColumn(colName, Unit.getDate(objVal.toString())); // 光有日期
////						String v = Unit.getString((Date) objVal, Unit.DATA_FULL_FORMAT);
////						if(v.endsWith("00:00:00")) {
////							v = v.substring(0,8);
////						}
//						String v = Unit.getString((Date) objVal, Unit.DATA_FULL_FORMAT);
//						row.addColumn(colName, v);
//					}
//					else {
//						row.addColumn(colName, objVal.toString());
//					}
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return table;
//    }
	/**
     * 使用泛型方法和反射机制进行封装
     * @param sqlStr
     * @param params
     * @param cls
     * @return
     */
	public static <T> List<T> executeQueryList(String sqlStr, List<Object> params, Class<T> cls) {
		List<T> listData = new ArrayList<T>();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sqlStr);// 装载sql语句
			// 设置参数
			setParams(ps, params);
			rs = ps.executeQuery();
			// 把查询出来的记录封装成对应的实体类对象
			ResultSetMetaData rsd = rs.getMetaData();// 获得列对象,通过此对象可以得到表的结构，包括，列名，列的个数，列的数据类型
			while (rs.next()) {
				T m = cls.newInstance();
				for (int i = 0; i < rsd.getColumnCount(); i++) {
					String col_name = rsd.getColumnName(i + 1);// 获得列名
					
					Object value = rs.getObject(col_name);// 获得列所对应的值
					
					Field field = cls.getDeclaredField(col_name);// 获得实体对象的字段
					field.setAccessible(true);// 给私有属性设置可访问权
					
					field.set(m, value);// 给对象的私有属性赋值
				}
				listData.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭对象
			closeAll(conn, ps, rs);
		}
		return listData;
    }
//	public static Item executeScalar(String sqlStr, List<Object> params) {
//		Item item = null;
//		try {
//			conn = getConnection();
//			ps = conn.prepareStatement(sqlStr);// 装载sql语句
//			// 设置参数
//			setParams(ps, params);
//			rs = ps.executeQuery();
//			if (rs.next()) {
//				item = new Item(rs.getObject(1));// 获得列所对应的值，下标从  1 开始
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 关闭对象
//			closeAll(conn, ps, rs);
//		}
//
//		return item;
//	}
	
    /**
     * 根据结果集获取数据库中的所有列表名
     * @param rs
     * @return
     */
	private static List<String> getAllColumnName(ResultSet rs) {
//		// 把查询出来的记录封装成对应的实体类对象
//		ResultSetMetaData rsd = rs.getMetaData();// 获得列对象,通过此对象可以得到表的结构，包括，列名，列的个数，列的数据类型
		List<String> names = new ArrayList<String>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				names.add(rsmd.getColumnName(i + 1));// 这里不能直接转 驼峰命名
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return names;
	}

}