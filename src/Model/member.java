package Model;

public class member {
	private int p_num;
	private String p_id;
	private String p_pw;
	private String p_name;
	private String p_phone;
	private String p_addr;
	private String p_age;
	private boolean p_gender;
	private boolean p_admin;
	
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getP_pw() {
		return p_pw;
	}
	public void setP_pw(String p_pw) {
		this.p_pw = p_pw;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_phone() {
		return p_phone;
	}
	public void setP_phone(String p_phone) {
		this.p_phone = p_phone;
	}
	public String getP_addr() {
		return p_addr;
	}
	public void setP_addr(String p_addr) {
		this.p_addr = p_addr;
	}
	public String getP_age() {
		return p_age;
	}
	public void setP_age(String p_age) {
		this.p_age = p_age;
	}
	public boolean isP_gender() {
		return p_gender;
	}
	public void setP_gender(boolean p_gender) {
		this.p_gender = p_gender;
	}
	public boolean isP_admin() {
		return p_admin;
	}
	public void setP_admin(boolean p_admin) {
		this.p_admin = p_admin;
	}
	@Override
	public String toString() {
		return "member [p_num=" + p_num + ", p_id=" + p_id + ", p_pw=" + p_pw + ", p_name=" + p_name + ", p_phone="
				+ p_phone + ", p_addr=" + p_addr + ", p_age=" + p_age + ", p_gender=" + p_gender + ", p_admin="
				+ p_admin + "]";
	}
}
