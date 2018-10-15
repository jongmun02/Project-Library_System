package Servlet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.book;
import Service.bookService;
import Service.memberService;

public class bookServlet extends HttpServlet {
	private book book;
	private bookService bookservice;
	private memberService memberservice;
//	private int count = 1; // 첫 입고 여부 알리는 변수
	BufferedWriter writer;
	
	public bookServlet() {
		bookservice = new bookService();
		memberservice = new memberService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProc(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProc(req, resp);
	}

	public void doProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String contextPath = req.getContextPath();
		String reqUri = req.getRequestURI();
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = resp.getWriter();

		if (reqUri.equals(contextPath + "/selectOneBook1.go")) {
			if (req.getSession().getAttribute("id") == null) {
				req.setCharacterEncoding("UTF-8");
				String search_Word = req.getParameter("search");
				if (bookservice.searchBookService(search_Word).isEmpty() || search_Word.isEmpty()
						|| search_Word.trim().length() == 0) {
					pw.println("<script>");
					pw.println("alert('검색 결과가 없습니다.')");
					pw.println("location.href='first.jsp'");
					pw.println("</script>");
				} else {
					req.setAttribute("bookList", bookservice.searchBookService(search_Word));
					req.getRequestDispatcher("selectOneBook1.jsp").forward(req, resp);
				}
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='main.do'");
				pw.println("</script>");
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/selectAllBook1.go")) {
			if (req.getSession().getAttribute("id") == null) {
				req.setAttribute("bookList", bookservice.selectAllService());
				req.getRequestDispatcher("selectOneBook1.jsp").forward(req, resp);
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='main.do'");
				pw.println("</script>");
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/adminBookMgm.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				resp.sendRedirect("adminBookMgm.jsp");
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('관리자 페이지입니다. 페이지에 접속하려면 관리자로 로그인하세요.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/selectAllBook2.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				req.setAttribute("bookList", bookservice.selectAllService());
				req.getRequestDispatcher("selectOneBook2.jsp").forward(req, resp);
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('관리자 페이지입니다. 페이지에 접속하려면 관리자로 로그인하세요.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/insertBook.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				req.setCharacterEncoding("UTF-8");
				String b_name = req.getParameter("b_name");
				String b_writer = req.getParameter("b_writer");
				String b_sort = req.getParameter("b_sort");
//				if (count != 1) {
//					if (bookservice.insertServiceTwo(b_name, b_writer, b_sort)) {
//						count++;
//						pw.println("<script>");
//						pw.println("alert('입고 처리가 완료됐습니다.')");
//						pw.println("location.href='adminBookMgm.go'");
//						pw.println("</script>");
//					} else {
//						pw.println("<script>");
//						pw.println("alert('입고 처리에 실패했습니다. 도서명과 저자명을 입력해주세요.')");
//						pw.println("location.href='insertBookForm.go'");
//						pw.println("</script>");
//					}
//				} else {
					if (bookservice.insertServiceTwo(b_name, b_writer, b_sort)) {
//						count++;
						pw.println("<script>");
						pw.println("alert('입고 처리가 완료됐습니다.')");
						pw.println("location.href='adminBookMgm.go'");
						pw.println("</script>");
					} else {
						pw.println("<script>");
						pw.println("alert('입고 처리에 실패했습니다. 도서명과 저자명을 입력해주세요.')");
						pw.println("location.href='insertBookForm.go'");
						pw.println("</script>");
					}
//				}
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('관리자 페이지입니다. 페이지에 접속하려면 관리자로 로그인하세요.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/insertBookForm.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				resp.sendRedirect("insertBookForm.jsp");
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('관리자 페이지입니다. 페이지에 접속하려면 관리자로 로그인하세요.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/selectOneBook2.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				req.setCharacterEncoding("UTF-8");
				String search_Word = req.getParameter("search_Word");
				if (bookservice.searchBookService(search_Word).isEmpty() || search_Word.isEmpty()
						|| search_Word.trim().length() == 0) {
					pw.println("<script>");
					pw.println("alert('검색 결과가 없습니다.')");
					pw.println("location.href='adminBookMgm.jsp'");
					pw.println("</script>");
				} else {
					req.setAttribute("bookList", bookservice.searchBookService(search_Word));
					req.getRequestDispatcher("selectOneBook2.jsp").forward(req, resp);
				}
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('관리자 페이지입니다. 페이지에 접속하려면 관리자로 로그인하세요.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/deleteBook.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				String b_num = req.getParameter("b_num");
				String p_pw = req.getParameter("p_pw"); // 삭제폼에서 입력받은 p_pw 값
				if (req.getSession().getAttribute("pw").equals(p_pw)) { // 입력받은
																		// pw와
																		// 세션의
																		// pw 일치
																		// 확인
					if (bookservice.deleteService(b_num)) { // 삭제 성공여부 확인
//						count--;
						pw.println("<script>");
						pw.println("alert('출고 처리가 완료됐습니다.')");
						pw.println("location.href='selectAllBook2.go'");
						pw.println("</script>");
					} else {
						pw.println("<script>");
						pw.println("alert('출고 처리에 실패했습니다. 책 번호를 확인해주세요.')");
						pw.println("location.href='selectAllBook2.go'");
						pw.println("</script>");
					}
				} else {
					pw.println("<script>");
					pw.println("alert('비밀번호가 틀렸습니다.')");
					pw.println("location.href='selectAllBook2.go'");
					pw.println("</script>");
				}
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('관리자 페이지입니다. 페이지에 접속하려면 관리자로 로그인하세요.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/deleteBookForm.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				String b_num = req.getParameter("b_num");
				req.setAttribute("b_num", b_num);
				req.getRequestDispatcher("deleteBookForm.jsp").forward(req, resp);
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('관리자 페이지입니다. 페이지에 접속하려면 관리자로 로그인하세요.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/userBookMgm.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('사용자 페이지입니다. 페이지에 접속하려면 사용자로 로그인하세요.')");
				pw.println("location.href='main.do'");
				pw.println("</script>");
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				resp.sendRedirect("userBookMgm.jsp");
			}
		} else if (reqUri.equals(contextPath + "/selectAllBook3.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('사용자 페이지입니다. 페이지에 접속하려면 사용자로 로그인하세요.')");
				pw.println("location.href='main.do'");
				pw.println("</script>");
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				req.setAttribute("bookList", bookservice.selectAllService());
				req.getRequestDispatcher("selectOneBook3.jsp").forward(req, resp);
			}
		} else if (reqUri.equals(contextPath + "/selectOneBook3.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('사용자 페이지입니다. 페이지에 접속하려면 사용자로 로그인하세요.')");
				pw.println("location.href='main.do'");
				pw.println("</script>");
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				req.setCharacterEncoding("UTF-8");
				String search_Word = req.getParameter("search");
				if (bookservice.searchBookService(search_Word).isEmpty() || search_Word.isEmpty()
						|| search_Word.trim().length() == 0) {
					pw.println("<script>");
					pw.println("alert('검색 결과가 없습니다.')");
					pw.println("location.href='userBookMgm.jsp'");
					pw.println("</script>");
				} else {
					req.setAttribute("bookList", bookservice.searchBookService(search_Word));
					req.getRequestDispatcher("selectOneBook3.jsp").forward(req, resp);
				}
			}
		} else if (reqUri.equals(contextPath + "/selectAllBook_Lend.go")) {
			req.setAttribute("bookList", bookservice.selectAllService());
			req.getRequestDispatcher("selectOneBook_Lend.jsp").forward(req, resp);
		} else if (reqUri.equals(contextPath + "/selectOneBook_Lend.go")) {
			req.setCharacterEncoding("UTF-8");
			String search_Word = req.getParameter("search");
			if (bookservice.searchBookService(search_Word).isEmpty() || search_Word.isEmpty()
					|| search_Word.trim().length() == 0) {
				pw.println("<script>");
				pw.println("alert('검색 결과가 없습니다.')");
				pw.println("location.href='updateLendForm.jsp'");
				pw.println("</script>");
			} else {
				req.setAttribute("bookList", bookservice.searchBookService(search_Word));
				req.getRequestDispatcher("selectOneBook_Lend.jsp").forward(req, resp);
			}
		} else if (reqUri.equals(contextPath + "/selectAllBook_Return.go")) {
			String p_id = (String) req.getSession().getAttribute("id");
			req.setAttribute("bookList", bookservice.selectAllService());
			req.getRequestDispatcher("selectOneBook_Return.jsp").forward(req, resp);
		} else if (reqUri.equals(contextPath + "/selectOneBook_Return.go")) {
			req.setCharacterEncoding("UTF-8");
			String search_Word = req.getParameter("search");
			if (bookservice.searchBookService(search_Word).isEmpty() || search_Word.isEmpty()
					|| search_Word.trim().length() == 0) {
				pw.println("<script>");
				pw.println("alert('검색 결과가 없습니다.')");
				pw.println("location.href='updateReturnForm.jsp'");
				pw.println("</script>");
			} else {
				String p_id = (String) req.getSession().getAttribute("id");
				req.setAttribute("bookList", bookservice.searchBookService(search_Word));
				req.getRequestDispatcher("selectOneBook_Return.jsp").forward(req, resp);
			}
		} else if (reqUri.equals(contextPath + "/updateLendForm.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('사용자 페이지입니다. 페이지에 접속하려면 사용자로 로그인하세요.')");
				pw.println("location.href='main.do'");
				pw.println("</script>");
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				String b_num = req.getParameter("b_num");
				req.setAttribute("b_num", b_num);
				req.getRequestDispatcher("updateLendForm.jsp").forward(req, resp);
			}
		} else if (reqUri.equals(contextPath + "/updateLend.go")) {
			req.setCharacterEncoding("UTF-8");
			String b_num = req.getParameter("b_num");
			String p_id = (String) req.getSession().getAttribute("id");
			if (bookservice.updateLendService(b_num, p_id)) {
				writer = new BufferedWriter(new FileWriter("C:\\BitCamp\\WorkSpace2\\LibTeamProject_final\\Log.txt", true));
				writer.write(p_id +"님이 "+ b_num + "번 도서를 대여했습니다." + "\t" + new Date());
				writer.newLine();
				writer.flush();
				writer.close();
				pw.println("<script>");
				pw.println("alert('도서 대여 처리가 완료됐습니다.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			} else {
				pw.println("<script>");
				pw.println("alert('대여 실패입니다. 책 번호 혹은 대여 중인 책 권수, 연체일수를 확인해주세요. (최대 3권 대여가능)')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/updateReturnForm.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('사용자 페이지입니다. 페이지에 접속하려면 사용자로 로그인하세요.')");
				pw.println("location.href='main.do'");
				pw.println("</script>");
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				String b_num = req.getParameter("b_num");
				req.setAttribute("b_num", b_num);
				req.getRequestDispatcher("updateReturnForm.jsp").forward(req, resp);
			}
		} else if (reqUri.equals(contextPath + "/updateReturn.go")) {
			req.setCharacterEncoding("UTF-8");
			String b_num = req.getParameter("b_num");
			String p_id = (String) req.getSession().getAttribute("id");
			if (bookservice.updateReturnService(b_num, p_id)) {
				writer = new BufferedWriter(new FileWriter("C:\\BitCamp\\WorkSpace2\\LibTeamProject_final\\Log.txt", true));
				writer.write(p_id +"님이 "+ b_num + "번 도서를 반납했습니다." + "\t" + new Date());
				writer.newLine();
				writer.flush();
				writer.close();
				
				pw.println("<script>");
				pw.println("alert('도서 반납 처리가 완료됐습니다.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			} else {
				pw.println("<script>");
				pw.println("alert('반납 실패입니다.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/selfDeleteForm.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('사용자 페이지입니다. 페이지에 접속하려면 사용자로 로그인하세요.')");
				pw.println("location.href='main.do'");
				pw.println("</script>");
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				resp.sendRedirect("selfDeleteForm.jsp");
			}
		} else if (reqUri.equals(contextPath + "/selfDelete.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('사용자 페이지입니다. 페이지에 접속하려면 사용자로 로그인하세요.')");
				pw.println("location.href='main.do'");
				pw.println("</script>");
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				String id = (String) req.getSession().getAttribute("id");
				String p_pw = req.getParameter("p_pw");
				if (req.getSession().getAttribute("pw").equals(p_pw)) {
					if (memberservice.deleteMember(id)) {
						req.getSession().invalidate();
						pw.println("<script>");
						pw.println("alert('회원 탈퇴 처리가 완료됐습니다.')");
						pw.println("location.href='loginForm.do'");
						pw.println("</script>");
					} else {
						pw.println("<script>");
						pw.println("alert('회원 탈퇴에 실패했습니다. 대여 중인 책이 있는지 확인해주세요.')");
						pw.println("location.href='selfDeleteForm.go'");
						pw.println("</script>");
					}
				} else {
					pw.println("<script>");
					pw.println("alert('회원 탈퇴에 실패했습니다. 비밀번호를 확인해주세요.')");
					pw.println("location.href='selfDeleteForm.go'");
					pw.println("</script>");
				}
			}
		} else if (reqUri.equals(contextPath + "/Logout.go")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else {
				req.getSession().invalidate();
				pw.println("<script>");
				pw.println("alert('로그아웃..')");
				pw.println("location.href='loginForm.do'");
				pw.println("</script>");
			}
		}
	} // doProc 메소드의 끝.
} // public class의 끝.
