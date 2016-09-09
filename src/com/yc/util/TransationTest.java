package com.yc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransationTest {
	public static void main(String[] args) {
		
		/**
		 * 模拟转账程序
		 * */
		Connection conn = null;
		try {
			// 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.得到连接
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "SCOTT", "tiger");


			// 加入事务处理
			conn.setAutoCommit(false);// 设置不能默认提交


			Statement sm = conn.createStatement();
			// 从scott的sal中减去100
			sm.executeUpdate("update myemp set sal=sal-100 where ename='SCOTT'");   //sal = 3000.00
			int i = 7 / 0;                          //报java.lang.ArithmeticException: / by zero异常
			// 给smith的sal加上100
			sm.executeUpdate("update myemp set sal=sal+100 where ename='SMITH'");   //sal = 800.00

			// 提交事务
			conn.commit();
			// 关闭打开的资源
			sm.close();
			conn.close();
		} catch (Exception e) {
			// 如果发生异常，就回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
}
