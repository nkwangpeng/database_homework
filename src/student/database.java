package student;

import java.util.*;
import java.sql.*;

import javax.swing.*;

public class database {
	static String driverName = "com.mysql.cj.jdbc.Driver";
	static String dbURL = "jdbc:mysql://localhost:3306/student?useSSL=false&serverTimezone=UTC";
	static String userName = "root"; // Ĭ���û���
	static String userPwd = "wp010214"; // ����
	public static Connection conn = null;

	public static void openDB() {
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbURL, userName, userPwd);
			if (conn.isClosed()) {
				JOptionPane.showMessageDialog(null, "Database is closed!", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insert(int s1, String s2, String s3, String s4, int s5) throws SQLException {
		openDB();
		try {
			String sql = "insert into student values('" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "')";
			Statement statement = conn.createStatement();
			int a = statement.executeUpdate(sql);
			if (a == 1) {
				JOptionPane.showMessageDialog(null, "����ɹ�", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "����ʧ��", "ʧ��", JOptionPane.INFORMATION_MESSAGE);
			closeDB();
		}
	}

	public static void update(int a1,String s2,String s3, String s4,int a5) throws SQLException {
		openDB();
		CallableStatement callableStatement = conn.prepareCall("{call updatestudent (?,?,?)}");
		callableStatement.setInt(1, a1);
		callableStatement.setString(2, s4);
		callableStatement.setInt(3, a5);
		int a = callableStatement.executeUpdate();
		if (a == 1) {
			JOptionPane.showMessageDialog(null, "���³ɹ�", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "����ʧ��", "ʧ��", JOptionPane.INFORMATION_MESSAGE);
		}
		closeDB();
	}

	public static void delete(int id) throws SQLException {
		openDB();
		Statement stmt = conn.createStatement();
		try {
			conn.setAutoCommit(false);//�޸������ύ��ʽ
			stmt = conn.createStatement();
			int a =stmt.executeUpdate("delete from score where student_id='" + id + "'");
			int b =stmt.executeUpdate("delete from student where id='" + id + "'");
			conn.commit();// �ύ����
			conn.setAutoCommit(true);// �ָ�JDBC�����Ĭ���ύ��ʽ
			if (a!=0&&b!=0) //ѧ�żȴ�����student���У��ִ�����score����
			{
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
			} 
			else//��ѧ�������ڣ�ɾ��ʧ��
			{
				JOptionPane.showMessageDialog(null, "ɾ��ʧ��", "ʧ��", JOptionPane.INFORMATION_MESSAGE);
			}
			stmt.close();
		} catch (Exception exc) {
			//��SQL���ִ���쳣��ɾ��ʧ��
			JOptionPane.showMessageDialog(null, "ɾ��ʧ��", "ʧ��", JOptionPane.INFORMATION_MESSAGE);
			conn.rollback();// �ع�JDBC����
			exc.printStackTrace();
			stmt.close();
		}
	}

	public static void closeDB() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	}
}
