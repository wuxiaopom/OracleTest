package com.yc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransationTest {
	public static void main(String[] args) {
		
		/**
		 * ģ��ת�˳���
		 * */
		Connection conn = null;
		try {
			// 1.��������
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.�õ�����
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "SCOTT", "tiger");


			// ����������
			conn.setAutoCommit(false);// ���ò���Ĭ���ύ


			Statement sm = conn.createStatement();
			// ��scott��sal�м�ȥ100
			sm.executeUpdate("update myemp set sal=sal-100 where ename='SCOTT'");   //sal = 3000.00
			int i = 7 / 0;                          //��java.lang.ArithmeticException: / by zero�쳣
			// ��smith��sal����100
			sm.executeUpdate("update myemp set sal=sal+100 where ename='SMITH'");   //sal = 800.00

			// �ύ����
			conn.commit();
			// �رմ򿪵���Դ
			sm.close();
			conn.close();
		} catch (Exception e) {
			// ��������쳣���ͻع�
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
}
