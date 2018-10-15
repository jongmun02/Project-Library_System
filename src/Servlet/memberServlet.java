package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.member;
import Service.bookService;
import Service.memberService;

public class memberServlet extends HttpServlet {
	private member member;
	private memberService memberService;
	private bookService bookService;

	public memberServlet() {
		memberService = new memberService();
		bookService = new bookService();
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
		resp.setContentType("text/html; charset = UTF-8");
		PrintWriter pw = resp.getWriter();

		if (reqUri.equals(contextPath + "/first.do")) {
				resp.sendRedirect("first.jsp");
		} else if (reqUri.equals(contextPath + "/loginForm.do")) {
			if (req.getSession().getAttribute("id") == null) {
				resp.sendRedirect("loginForm.jsp");
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
		} else if (reqUri.equals(contextPath + "/joinForm.do")) {
			if (req.getSession().getAttribute("id") == null) {
				resp.sendRedirect("joinForm.jsp");
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
		} else if (reqUri.equals(contextPath + "/join.do")) {
			if (req.getSession().getAttribute("id") == null) {
				String id = req.getParameter("id");
				String pass = req.getParameter("pw");
				String pwCheck = req.getParameter("pwCheck");
				String name = req.getParameter("name");
				String phone = req.getParameter("phone");
				String addr = req.getParameter("addr");
				String age = req.getParameter("age");
				String check = req.getParameter("gender");
				String check2 = req.getParameter("admin");
				Boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
				Boolean admin = Boolean.parseBoolean(req.getParameter("admin"));
				
				if (memberService.getMemberOne(id) == null && id != "") {
					if (pass != "" && pwCheck != "" && pass.equals(pwCheck)) {
						if(check == null || check2 == null) {
							pw.println("<script>");
							pw.println("alert('성별 또는 권한을 선택하세요')");
							pw.println("location.href='joinForm.do'");
							pw.println("</script>");
						}
					else if (gender || !gender) {
							if (admin) {
								memberService.join(id, pass, name, phone, addr, age, gender, admin);
								pw.println("<script>");
								pw.println("alert('관리자 회원가입 성공')");
								pw.println("location.href='loginForm.do'");
								pw.println("</script>");
							} else if (!admin) {
								memberService.join(id, pass, name, phone, addr, age, gender, admin);
								pw.println("<script>");
								pw.println("alert('사용자 회원가입 성공')");
								pw.println("location.href='loginForm.do'");
								pw.println("</script>");
							} 
						} 
					} else {
						pw.println("<script>");
						pw.println("alert('비밀번호를 다시 입력하세요.')");
						pw.println("location.href='joinForm.do'");
						pw.println("</script>");
					}
				} else if (memberService.getMemberOne(id) != null) {
					pw.println("<script>");
					pw.println("alert('이미 사용중인 아이디입니다.')");
					pw.println("location.href='joinForm.do'");
					pw.println("</script>");
				} else {
					pw.println("<script>");
					pw.println("alert('아이디를 입력하세요.')");
					pw.println("location.href='joinForm.do'");
					pw.println("</script>");
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
		} else if (reqUri.equals(contextPath + "/login.do")) {
			String id = req.getParameter("id");
			String pass = req.getParameter("pw");
			if (memberService.getMemberOne(id) != null) {
				member = memberService.getMemberOne(id);
				if (member.getP_pw().equals(pass)) {
					req.getSession().setAttribute("id", id);
					req.getSession().setAttribute("pw", pass);
					req.getSession().setAttribute("admin", member.isP_admin());
					req.getRequestDispatcher("loginResult.jsp").forward(req, resp);
				} else {
					pw.println("<script>");
					pw.println("alert('비밀번호가 틀렸습니다.')");
					pw.println("location.href='loginForm.do'");
					pw.println("</script>");
				}
			} else {
				pw.println("<script>");
				pw.println("alert('존재하지 않는 아이디입니다.')");
				pw.println("location.href='loginForm.do'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/adminCheck.do")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('관리자 페이지로 이동합니다.')");
				pw.println("location.href='main.do'");
				pw.println("</script>");
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('사용자 페이지로 이동합니다.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/main.do")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				resp.sendRedirect("main.jsp");
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('관리자 페이지입니다. 페이지에 접속하려면 관리자로 로그인하세요.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/memberMgm.do")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				resp.sendRedirect("memberMgm.jsp");
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('관리자 페이지입니다. 페이지에 접속하려면 관리자로 로그인하세요.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/selectOneMember.do")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				String search = req.getParameter("search");
				System.out.println("멤버서블릿 205  "+search);
				System.out.println(memberService.getMemberOne(search));
				if (memberService.getMemberOne(search) != null) {
					if (!memberService.getMemberOne(search).isP_admin() || req.getSession().getAttribute("id")
							.equals(memberService.getMemberOne(search).getP_id())) {
						req.setAttribute("search", memberService.getMemberOne(search));
						req.getRequestDispatcher("selectOneMember.jsp").forward(req, resp);
					} else {
						pw.println("<script>");
						pw.println("alert('자신을 제외한 다른 관리자의 정보는 볼 수 없습니다.')");
						pw.println("location.href='memberMgm.do'");
						pw.println("</script>");
					}
				} else {
					pw.println("<script>");
					pw.println("alert('검색 결과가 없습니다. 정확한 ID값을 입력하셔야 합니다.')");
					pw.println("location.href='memberMgm.do'");
					pw.println("</script>");
				}
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('관리자 페이지입니다. 페이지에 접속하려면 관리자로 로그인하세요.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/selectAllMember.do")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				req.setAttribute("list", memberService.getMemberAll());
				req.getRequestDispatcher("selectAllMember.jsp").forward(req, resp);
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('관리자 페이지입니다. 페이지에 접속하려면 관리자로 로그인하세요.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/updateMemberForm.do")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				String id = req.getParameter("id");
				req.setAttribute("id", id);
				req.getRequestDispatcher("updateMemberForm.jsp").forward(req, resp);
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('관리자 페이지입니다. 페이지에 접속하려면 관리자로 로그인하세요.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/updateMember.do")) {
			String id = req.getParameter("id");
			String pass = req.getParameter("pw");
			String pwCheck = req.getParameter("pwCheck");
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			String addr = req.getParameter("addr");
			String age = req.getParameter("age");

			if (memberService.getMemberOne(id) != null) {
				if (req.getSession().getAttribute("pw").equals(pass) && pass.equals(pwCheck) && pass != ""
						&& pwCheck != "") {
					memberService.updateMember(id, name, phone, addr, age);
					pw.println("<script>");
					pw.println("alert('회원정보가 수정되었습니다.')");
					pw.println("location.href='memberMgm.do'");
					pw.println("</script>");
				} else {
					pw.println("<script>");
					pw.println("alert('비밀번호를 다시 입력하세요.')");
					pw.println("location.href='selectAllMember.do'");
					pw.println("</script>");
				}
			} else {
				pw.println("<script>");
				pw.println("alert('아이디를 다시 입력하세요.')");
				pw.println("location.href='selectAllMember.do'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/deleteMemberForm.do")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else if ((Boolean) req.getSession().getAttribute("admin")) {
				String id = req.getParameter("id");
				req.setAttribute("id", id);
				req.getRequestDispatcher("deleteMemberForm.jsp").forward(req, resp);
			} else if (!(Boolean) req.getSession().getAttribute("admin")) {
				pw.println("<script>");
				pw.println("alert('관리자 페이지입니다. 페이지에 접속하려면 관리자로 로그인하세요.')");
				pw.println("location.href='userBookMgm.go'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/deleteMember.do")) {
			String id = req.getParameter("id");
			String pass = req.getParameter("pw");
			String pwCheck = req.getParameter("pwCheck");

			if (memberService.getMemberOne(id) != null) {
				if (req.getSession().getAttribute("pw").equals(pass) && pass.equals(pwCheck) && pass != ""
						&& pwCheck != "") {
					if (memberService.deleteMember(id)) {
						if (req.getSession().getAttribute("id").equals(id)) {
							req.getSession().invalidate();
							pw.println("<script>");
							pw.println("alert('회원정보가 삭제되었습니다. 처음으로 돌아갑니다.')");
							pw.println("location.href='first.do'");
							pw.println("</script>");
						} else {
							pw.println("<script>");
							pw.println("alert('회원정보가 삭제되었습니다.')");
							pw.println("location.href='memberMgm.do'");
							pw.println("</script>");
						}
					} else {
						pw.println("<script>");
						pw.println("alert('해당 사용자가 대여중인 책이 있습니다.')");
						pw.println("location.href='selectAllMember.do'");
						pw.println("</script>");
					}
				} else {
					pw.println("<script>");
					pw.println("alert('비밀번호를 다시 입력하세요.')");
					pw.println("location.href='selectAllMember.do'");
					pw.println("</script>");
				}
			} else {
				pw.println("<script>");
				pw.println("alert('아이디를 다시 입력하세요.')");
				pw.println("location.href='selectAllMember.do'");
				pw.println("</script>");
			}
		} else if (reqUri.equals(contextPath + "/memberBook.do")) {
			if (req.getSession().getAttribute("id") == null) {
				pw.println("<script>");
				pw.println("alert('잘못된 접근입니다.')");
				pw.println("location.href='first.do'");
				pw.println("</script>");
			} else {
				String id = req.getParameter("id");
				if (!req.getSession().getAttribute("id").equals(id)
						|| !(Boolean) req.getSession().getAttribute("admin")) {
					req.setAttribute("id", id);
					req.setAttribute("bookList", bookService.selectUserBookService(id));
					req.getRequestDispatcher("memberBook.jsp").forward(req, resp);
				} else {
					if ((Boolean) req.getSession().getAttribute("admin")) {
						pw.println("<script>");
						pw.println("alert('관리자는 대여정보가 없습니다. 회원관리 페이지로 돌아갑니다.')");
						pw.println("location.href='memberMgm.do'");
						pw.println("</script>");
					}
				}
			}
		}
	}
}
