package Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Dao.bookDao;
import Model.book;

public class bookService {
	private static bookDao bookDao;

	public bookService() {
		bookDao = bookDao.getInstance();
	}

//	public boolean insertServiceOne(String b_name, String b_writer, String b_sort) {
//		System.out.println("북서비스20 "+b_name);
//		System.out.println("북서비스20 "+b_writer);
//		if (!b_name.isEmpty() && !b_writer.isEmpty()) {
//			book book = new book();
//			book.setB_name(b_name);
//			book.setB_writer(b_writer);
//			book.setB_sort(b_sort);
//			String recent_b_num = bookDao.recentBookNumOne().getB_num();
//			String temp = recent_b_num.substring(3, 6);
//			int tempInt = Integer.parseInt(temp) + 1;
//			String s = String.format("%03d", tempInt);
//			if (b_sort.equals("총류")) {
//				book.setB_num("00-" + s);
//				bookDao.insertBook(book);
//				return true;
//			} else if (b_sort.equals("인문학")) {
//				book.setB_num("01-" + s);
//				bookDao.insertBook(book);
//				return true;
//			} else if (b_sort.equals("종교")) {
//				book.setB_num("02-" + s);
//				bookDao.insertBook(book);
//				return true;
//			} else if (b_sort.equals("사회과학")) {
//				book.setB_num("03-" + s);
//				bookDao.insertBook(book);
//				return true;
//			} else if (b_sort.equals("자연과학")) {
//				book.setB_num("04-" + s);
//				bookDao.insertBook(book);
//				return true;
//			} else if (b_sort.equals("기술과학")) {
//				book.setB_num("05-" + s);
//				bookDao.insertBook(book);
//				return true;
//			} else if (b_sort.equals("예술")) {
//				book.setB_num("06-" + s);
//				bookDao.insertBook(book);
//				return true;
//			} else if (b_sort.equals("언어")) {
//				book.setB_num("07-" + s);
//				bookDao.insertBook(book);
//				return true;
//			} else if (b_sort.equals("문학")) {
//				book.setB_num("08-" + s);
//				bookDao.insertBook(book);
//				return true;
//			} else if (b_sort.equals("역사")) {
//				book.setB_num("09-" + s);
//				bookDao.insertBook(book);
//				return true;
//			} else if (b_sort.equals("기타")) {
//				book.setB_num("10-" + s);
//				bookDao.insertBook(book);
//				return true;
//			} else {
//				return false;
//			}
//		} else {
//			return false;
//		}
//	}

	public boolean insertServiceTwo(String b_name, String b_writer, String b_sort) {
		System.out.println("북서비스84 "+b_name);
		System.out.println("북서비스84 "+b_writer);
		if (!b_name.isEmpty() && !b_writer.isEmpty()) {
			book book = new book();
			book.setB_name(b_name);
			book.setB_writer(b_writer);
			book.setB_sort(b_sort);
			String s;
			if (bookDao.recentBookNumTwo()==null) {
				String recent_b_num = "00-000";
				String temp = recent_b_num.substring(3, 6);
				int tempInt = Integer.parseInt(temp) + 1;
				s = String.format("%03d", tempInt);
			} else {
				String recent_b_num = bookDao.recentBookNumTwo().getB_num();
				String temp = recent_b_num.substring(3, 6);
				int tempInt = Integer.parseInt(temp) + 1;
				s = String.format("%03d", tempInt);
			}
			if (b_sort.equals("총류")) {
				book.setB_num("00-" + s);
				bookDao.insertBook(book);
				return true;
			} else if (b_sort.equals("인문학")) {
				book.setB_num("01-" + s);
				bookDao.insertBook(book);
				return true;
			} else if (b_sort.equals("종교")) {
				book.setB_num("02-" + s);
				bookDao.insertBook(book);
				return true;
			} else if (b_sort.equals("사회과학")) {
				book.setB_num("03-" + s);
				bookDao.insertBook(book);
				return true;
			} else if (b_sort.equals("자연과학")) {
				book.setB_num("04-" + s);
				bookDao.insertBook(book);
				return true;
			} else if (b_sort.equals("기술과학")) {
				book.setB_num("05-" + s);
				bookDao.insertBook(book);
				return true;
			} else if (b_sort.equals("예술")) {
				book.setB_num("06-" + s);
				bookDao.insertBook(book);
				return true;
			} else if (b_sort.equals("언어")) {
				book.setB_num("07-" + s);
				bookDao.insertBook(book);
				return true;
			} else if (b_sort.equals("문학")) {
				book.setB_num("08-" + s);
				bookDao.insertBook(book);
				return true;
			} else if (b_sort.equals("역사")) {
				book.setB_num("09-" + s);
				bookDao.insertBook(book);
				return true;
			} else if (b_sort.equals("기타")) {
				book.setB_num("10-" + s);
				bookDao.insertBook(book);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean deleteService(String b_num) {
		if (bookDao.selectOneBookNum(b_num) != null) {
			if (bookDao.selectOneBookNum(b_num).getB_lendStatus().equals("대여가능")) {
				bookDao.deleteBook(b_num);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// 파라미터 : b_num, p_id / 리턴값 : 대여 성공여부
	public boolean updateLendService(String b_num, String p_id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		int yyyy = cal.get(Calendar.YEAR);
		int MM = cal.get(Calendar.MONTH);
		int dd = cal.get(Calendar.DATE);
		cal.set(yyyy, MM, dd);

		String today = sdf.format(cal.getTime());
		Date todayDate = null;
		try {
			todayDate = sdf.parse(today);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (bookDao.selectOneBookID(p_id) == null) {
			if (bookDao.selectOneBookNum(b_num) != null) {
				if (bookDao.selectUserBook(p_id).size() < 3) { // 대여가능 회원 확인 (3권까지 대여가능)
					if (bookDao.selectOneBookNum(b_num).getB_lendStatus().equals("대여가능")) { // '대여가능'한 책인지 여부 확인
						book book = new book();
						book.setB_id(p_id);
						book.setB_num(b_num);
						bookDao.updateLendBook(book);
						long diff = todayDate.getTime() - bookDao.selectOneBookNum(b_num).getB_lendDate().getTime();
						long diffDays = diff / (24 * 60 * 60 * 1000);
						if (diffDays - 14 > 0) {
							book.setB_overdue(diffDays - 14);
							bookDao.updateOverDueBook(book);
						}
						return true;
					} else
						return false;
				} else
					return false;
			} else
				return false;
		} else if (bookDao.selectOneBookID(p_id) != null) {
			if (bookDao.selectOneBookNum(b_num) != null) {
				if (bookDao.selectUserBook(p_id).size() < 3 && bookDao.selectOneBookID(p_id).getB_overdue() == 0) { // 대여가능
																													// 회원
																													// 확인
																													// (3권까지
																													// 대여가능
																													// or
																													// 연체자
																													// 대여
																													// 불가)
					if (bookDao.selectOneBookNum(b_num).getB_lendStatus().equals("대여가능")) { // '대여가능'한 책인지 여부 확인
						book book = new book();
						book.setB_id(p_id);
						book.setB_num(b_num);
						bookDao.updateLendBook(book);
						long diff = todayDate.getTime() - bookDao.selectOneBookNum(b_num).getB_lendDate().getTime();
						long diffDays = diff / (24 * 60 * 60 * 1000);
						if (diffDays - 14 > 0) {
							book.setB_overdue(diffDays - 14);
							bookDao.updateOverDueBook(book);
						}
						return true;
					} else
						return false;
				} else
					return false;
			} else
				return false;
		} else {
			return false;
		}
	}

	// 파라미터 : b_num, p_id / 리턴값 : 반납 성공여부
	public boolean updateReturnService(String b_num, String p_id) {
		// 파라미터로 받은 id값과 b_num으로 조회한 책 대여정보 內 id값의 일치 여부 확인
		if (bookDao.selectOneBookNum(b_num) != null) {
			if (bookDao.selectOneBookNum(b_num).getB_id().equals(p_id)) {
				if (bookDao.selectOneBookNum(b_num).getB_returnStatus().equals("반납가능")) {
					book book = new book();
					book.setB_num(b_num);
					bookDao.updateReturnBook(book);
					return true;
				} else {
					return false;
				}
			} else { // 접속한 사용자가 대여한 사용자가 맞는지 확인하는 조건문
				return false;
			}
		} else { // 책 존재여부 조건문
			return false;
		}
	}

	// 도서 검색을 처리하는 기능
	public List<book> searchBookService(String search_Word) {
		return bookDao.searchBook(search_Word);
	}

	public book selectOneService(String b_num) {
		return bookDao.selectOneBookNum(b_num);
	}

	public book selectOneServiceID(String p_id) {
		return bookDao.selectOneBookID(p_id);
	}

	public List<book> selectAllService() {
		return bookDao.selectAllBook();
	}

	public List<book> selectUserBookService(String p_id) {
		return bookDao.selectUserBook(p_id);
	}
}
