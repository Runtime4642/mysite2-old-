package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.GuestBookVo;

public class GuestBookDao extends Dao{
	
	
	public List<GuestBookVo> getList()
	{
		
			List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		
		
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		try {
			 conn = getConnection();
			 
			 String sql = "select no , name , password , message,reg_date from guestbook order by no desc";
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 while(rs.next())
			 {
				 long no = rs.getLong(1);
				 String name = rs.getString(2);
				 String password = rs.getString(3);
				 String message = rs.getString(4);
				 String date = rs.getString(5);
				 
				 GuestBookVo vo = new GuestBookVo();
				 vo.setNo(no);
				 vo.setName(name);
				 vo.setPassword(password);
				 vo.setDate(date);
				 vo.setMessage(message);
				 list.add(vo);
			 }
			 return list;
			 
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} 
		finally 
		{
				try {
					if(conn !=null)
					conn.close();
					if(rs !=null)
						rs.close();
					if(pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		
		
		return list;
		
		
		
		
	}
	public boolean insert(GuestBookVo vo)
	{
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=getConnection();
			
			String sql = "insert into guestbook values(null,?,?,?,current_timestamp())";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());

			
			int count = pstmt.executeUpdate();
			result = count ==1;
			
		}catch (SQLException e) {
			System.out.println("error:"+e);
		} 
		finally 
		{
				try {
					if(conn !=null)
					conn.close();
					if(pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		
		
		
		return result;
	}

	public boolean delete(String no,String password)
	{
	boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=getConnection();
			
			String sql = "delete from guestbook where no=? and password=?";	
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1,(long)Integer.parseInt(no));
			pstmt.setString(2, password);	
			int count = pstmt.executeUpdate();
			result = count ==1;
			
		}catch (SQLException e) {
			System.out.println("error:"+e);
		} 
		finally 
		{
				try {
					if(conn !=null)
					conn.close();
					if(pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		
		
		
		return result;
	}

	}


