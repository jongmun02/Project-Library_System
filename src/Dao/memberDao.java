package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.member;

public class memberDao {
	private Connection con;
	private static final String URL = "jdbc:mysql://localhost:3306/board";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "mysql";
	private static memberDao instance;

	public static memberDao getInstance() {
		if (instance == null) {
			instance = new memberDao();
		}
		return instance;
	}

	public memberDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertMember(member member) {
		String sql = "insert into member values(null, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getP_id());
			pstmt.setString(2, member.getP_pw());
			pstmt.setString(3, member.getP_name());
			pstmt.setString(4, member.getP_phone());
			pstmt.setString(5, member.getP_addr());
			pstmt.setString(6, member.getP_age());
			pstmt.setBoolean(7, member.isP_gender());
			pstmt.setBoolean(8, member.isP_admin());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void updateMember(member member) {
		String sql = "update member set p_name = ?, p_phone = ?, p_addr = ?, p_age = ? where p_id = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(5, member.getP_id());
			pstmt.setString(1, member.getP_name());
			pstmt.setString(2, member.getP_phone());
			pstmt.setString(3, member.getP_addr());
			pstmt.setString(4, member.getP_age());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void deleteMember(String p_id) {
		String sql = "delete from member where p_id = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, p_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public member selectOneMember(String p_id) {
	
		String sql = "select * from member where p_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		member member = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, p_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new member();
				member.setP_num(rs.getInt("p_num"));
				member.setP_id(rs.getString("p_id"));
				member.setP_pw(rs.getString("p_pw"));
				member.setP_name(rs.getString("p_name"));
				member.setP_phone(rs.getString("p_phone"));
				member.setP_addr(rs.getString("p_addr"));
				member.setP_age(rs.getString("p_age"));
				member.setP_gender(rs.getBoolean("p_gender"));
				member.setP_admin(rs.getBoolean("p_admin"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return member;
	}
	
	public List<member> selectAllMember() {
		String sql = "select * from member";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		member member = null;
		List<member> list = new ArrayList<>();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new member();
				member.setP_num(rs.getInt("p_num"));
				member.setP_id(rs.getString("p_id"));
				member.setP_pw(rs.getString("p_pw"));
				member.setP_name(rs.getString("p_name"));
				member.setP_phone(rs.getString("p_phone"));
				member.setP_addr(rs.getString("p_addr"));
				member.setP_age(rs.getString("p_age"));
				member.setP_gender(rs.getBoolean("p_gender"));
				member.setP_admin(rs.getBoolean("p_admin"));
				list.add(member);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
