package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.book;

public class bookDao {
	private Connection con;
	private static final String URL = "jdbc:mysql://localhost:3306/board";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "mysql";
	private static bookDao instance;

	public static bookDao getInstance() {
		if (instance == null) {
			instance = new bookDao();
		}
		return instance;
	}

	public bookDao() {
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

	public void insertBook(book book) {
		String sql = "insert into book values('대여자없음', ?, ?, default, null, null, ?, ?, '대여가능', '반납완료', 0)";
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book.getB_name());
			pstmt.setString(2, book.getB_writer());
			pstmt.setString(3, book.getB_sort());
			pstmt.setString(4, book.getB_num());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void updateLendBook(book book) {
		String sql = "update book set b_lendStatus = '대여불가', b_lendDate = now(), b_returnDate = null, p_id = ?, b_returnStatus = '반납가능' where b_num = ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(2, book.getB_num());
			pstmt.setString(1, book.getB_id());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void updateReturnBook(book book) {
		String sql = "update book set b_returnStatus = '반납완료', b_returnDate = now(), p_id = '대여자없음', b_lendStatus = '대여가능', b_overdue = 0 where b_num = ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book.getB_num());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void updateOverDueBook(book book) {
		String sql = "update book set b_overdue = ? where b_num = ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(2, book.getB_num());
			pstmt.setLong(1, book.getB_overdue());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deleteBook(String b_num) {
		String sql = "delete from book where b_num = ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, b_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 도서 검색 쿼리 실행
	public List<book> searchBook(String search_Word) {

		String sql = "select * from book where b_name like ? or b_writer like ? or " + "b_sort like ? or b_num = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<book> list = new ArrayList<book>();
		book book = null;// 리턴할 객체의 참조변수

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search_Word + "%");
			;
			pstmt.setString(2, "%" + search_Word + "%");
			;
			pstmt.setString(3, "%" + search_Word + "%");
			;
			pstmt.setString(4, search_Word);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				book = new book();
				book.setB_num(rs.getString("b_num"));
				book.setB_name(rs.getString("b_name"));
				book.setB_writer(rs.getString("b_writer"));
				book.setB_inputDate(rs.getDate("b_inputDate"));
				book.setB_sort(rs.getString("b_sort"));
				book.setB_lendStatus(rs.getString("b_lendStatus"));
				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 다썼으면 닫아주기
			try {
				if (pstmt != null & !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public book selectOneBookNum(String b_num) {
		String sql = "select * from book where b_num = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		book book = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, b_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				book = new book();
				book.setB_id(rs.getString("p_id"));
				book.setB_name(rs.getString("b_name"));
				book.setB_writer(rs.getString("b_writer"));
				book.setB_inputDate(rs.getDate("b_inputDate"));
				book.setB_lendDate(rs.getDate("b_lendDate"));
				book.setB_returnDate(rs.getDate("b_returnDate"));
				book.setB_sort(rs.getString("b_sort"));
				book.setB_num(rs.getString("b_num"));
				book.setB_lendStatus(rs.getString("b_lendStatus"));
				book.setB_returnStatus(rs.getString("b_returnStatus"));
				book.setB_overdue(rs.getLong("b_overdue"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return book;
	}
	
	public book selectOneBookID(String p_id) {
		String sql = "select * from book where p_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		book book = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, p_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				book = new book();
				book.setB_id(rs.getString("p_id"));
				book.setB_name(rs.getString("b_name"));
				book.setB_writer(rs.getString("b_writer"));
				book.setB_inputDate(rs.getDate("b_inputDate"));
				book.setB_lendDate(rs.getDate("b_lendDate"));
				book.setB_returnDate(rs.getDate("b_returnDate"));
				book.setB_sort(rs.getString("b_sort"));
				book.setB_num(rs.getString("b_num"));
				book.setB_lendStatus(rs.getString("b_lendStatus"));
				book.setB_returnStatus(rs.getString("b_returnStatus"));
				book.setB_overdue(rs.getLong("b_overdue"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return book;
	}
	
	public List<book> selectAllBook() {
		String sql = "select * from book";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		book book = null;
		List<book> list = new ArrayList<book>();

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				book = new book();
				book.setB_name(rs.getString("b_name"));
				book.setB_writer(rs.getString("b_writer"));
				book.setB_inputDate(rs.getDate("b_inputDate"));
				book.setB_sort(rs.getString("b_sort"));
				book.setB_num(rs.getString("b_num"));
				book.setB_lendStatus(rs.getString("b_lendStatus"));
				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<book> selectUserBook(String p_id) {

		String sql = "select * from book b join member m using(p_id) where p_id = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<book> bookList = new ArrayList<book>();

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, p_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				book book = new book();
				book.setB_id(rs.getString("p_id"));
				book.setB_num(rs.getString("b_num"));
				book.setB_name(rs.getString("b_name"));
				book.setB_writer(rs.getString("b_writer"));
				book.setB_sort(rs.getString("b_sort"));
				book.setB_lendDate(rs.getDate("b_lendDate"));
				book.setB_returnDate(rs.getDate("b_returnDate"));
				book.setB_returnStatus(rs.getString("b_returnStatus"));
				book.setB_overdue(rs.getLong("b_overdue"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 다썼으면 닫아주기
			try {
				if (pstmt != null & !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bookList;
	}

//	public book recentBookNumOne() {
//		book book = new book();
//		book.setB_num("00-000");
//		return book;
//	}

	public book recentBookNumTwo() {
		String sql = "select * from book order by b_inputDate desc limit 1;";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		book book = null;

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				book = new book();
				book.setB_num(rs.getString("b_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return book;
	}

}
