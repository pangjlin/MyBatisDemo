package com.ksc.mybatis.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class JdbcStudentService{
	//加载JDBC驱动
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/mybatis";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	public static void main(String[] args){
		//获取数据库连接
		JdbcStudentService service = new JdbcStudentService();
		Student existingStudent = service.findStudentById(1);
		System.out.println(existingStudent);
		long ts = System.currentTimeMillis();
		Student newStudent = new Student(0,"student_"+ts,"student_"+ts+"@gmail.com",new Date());
		service.createStudent(newStudent);
		System.out.println(newStudent);
		int updateStudId = 3;
		Student updateStudent = service.findStudentById(updateStudId);
		ts = System.currentTimeMillis();
		updateStudent.setEmail("student_"+ts+"@gmail.com");
		service.updateStudent(updateStudent);
	}
	
	public Student findStudentById(int studId){
		Student student = null;
		Connection conn = null;
		try{
			conn = getDatabaseConnection();
			String sql = "select * from students where stud_id=?";
			//创建PreparedStatement对象
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//设置传入参数
			pstmt.setInt(1, studId);
			//执行SQL语句
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				student = new Student();
				student.setStudId(rs.getInt("stud_id"));
				student.setName(rs.getString("name"));
				student.setEmail(rs.getString("email"));
				student.setDob(rs.getDate("dob"));
			}	
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
		finally{
			if(conn!= null){
				try {
					conn.close();
				} catch (SQLException e){ }
			}
		}
		return student;
	}
	
	public void createStudent(Student student){
		Connection conn = null;
		try{
			conn = getDatabaseConnection();
			String sql = "INSERT INTO STUDENTS(STUD_ID,NAME,EMAIL,DOB) VALUES(?,?,?,?)";
			//创建PreparedStatement对象
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//设置传入参数
			pstmt.setInt(1, student.getStudId());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getEmail());
			pstmt.setDate(4, new java.sql.Date(student.getDob().getTime()));
			//执行SQL语句
			pstmt.executeUpdate();
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
		finally{
			if(conn!= null){
				try {
					conn.close();
				} catch (SQLException e){ }
			}
		}
	}
	
	public void updateStudent(Student student){
		Connection conn = null;
		try{
			conn = getDatabaseConnection();
			conn = getDatabaseConnection();
			String sql = "UPDATE STUDENTS SET NAME=?,EMAIL=?,DOB=? WHERE STUD_ID=?";
			//创建PreparedStatement对象
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//设置传入参数
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getEmail());
			pstmt.setDate(3, new java.sql.Date(student.getDob().getTime()));
			pstmt.setInt(4, student.getStudId());
			//执行SQL语句
			pstmt.executeUpdate();
		} catch (SQLException e){
			throw new RuntimeException(e.getCause());
		}
		finally{
			if(conn!= null){
				try {
					conn.close();
				} catch (SQLException e){ }
			}
		}
	}
	
	protected Connection getDatabaseConnection() throws SQLException{
		try{
			Class.forName(JdbcStudentService.DRIVER);
			return DriverManager.getConnection(JdbcStudentService.URL, 
												JdbcStudentService.USERNAME, 
												JdbcStudentService.PASSWORD);
		} catch (SQLException e){
			throw e;
		} catch (Exception e){
			throw new RuntimeException(e.getCause());
		} 
	}
}
