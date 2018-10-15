package Service;

import java.util.List;

import Dao.bookDao;
import Dao.memberDao;
import Model.book;
import Model.member;

public class memberService {
	private member member;
	private memberDao memberDao;
	private bookDao bookDao;
	private book book;
	
	public memberService() {
		memberDao = memberDao.getInstance();
		bookDao = bookDao.getInstance();
	}

	public boolean join(String p_id, String p_pw, String p_name, String p_phone, String p_addr, String p_age,
			Boolean p_gender, Boolean p_admin) {
		if (memberDao.selectOneMember(p_id) == null) {
			member = new member();
			member.setP_id(p_id);
			member.setP_pw(p_pw);
			member.setP_name(p_name);
			member.setP_phone(p_phone);
			member.setP_addr(p_addr);
			member.setP_age(p_age);
			member.setP_gender(p_gender);
			member.setP_admin(p_admin);
			memberDao.insertMember(member);
			return true;
		} else {
			return false;
		}
	}

	public boolean login(String p_id, String p_pw, Boolean p_admin) {
		if (memberDao.selectOneMember(p_id) != null) {
			member = memberDao.selectOneMember(p_id);
			if (member.getP_pw().equals(p_pw)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean deleteMember(String id) {
		if (memberDao.selectOneMember(id) != null) {
			if (bookDao.selectUserBook(id).isEmpty()) {
				memberDao.deleteMember(id);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean updateMember(String p_id, String p_name, String p_phone, String p_addr, String p_age) {
		if (memberDao.selectOneMember(p_id) != null) {
			member = new member();
			member.setP_id(p_id);
			member.setP_name(p_name);
			member.setP_phone(p_phone);
			member.setP_addr(p_addr);
			member.setP_age(p_age);
			memberDao.updateMember(member);
			return true;
		} else {
			return false;
		}
	}

	public member getMemberOne(String p_id) {
		return memberDao.selectOneMember(p_id);
	}

	public List<member> getMemberAll() {
		return memberDao.selectAllMember();
	}
}
