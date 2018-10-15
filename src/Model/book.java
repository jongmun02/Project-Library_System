package Model;

import java.util.Date;

public class book {
	private String b_id;
	private String b_name;
	private String b_writer;
	private Date b_inputDate;
	private Date b_lendDate;
	private Date b_returnDate;
	private String b_sort;
	private String b_num;
	private String b_lendStatus;
	private String b_returnStatus;
	private long b_overdue;
	
	public String getB_id() {
		return b_id;
	}
	public void setB_id(String b_id) {
		this.b_id = b_id;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getB_writer() {
		return b_writer;
	}
	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}
	public Date getB_inputDate() {
		return b_inputDate;
	}
	public void setB_inputDate(Date b_inputDate) {
		this.b_inputDate = b_inputDate;
	}
	public Date getB_lendDate() {
		return b_lendDate;
	}
	public void setB_lendDate(Date b_lendDate) {
		this.b_lendDate = b_lendDate;
	}
	public Date getB_returnDate() {
		return b_returnDate;
	}
	public void setB_returnDate(Date b_returnDate) {
		this.b_returnDate = b_returnDate;
	}
	public String getB_sort() {
		return b_sort;
	}
	public void setB_sort(String b_sort) {
		this.b_sort = b_sort;
	}
	public String getB_num() {
		return b_num;
	}
	public void setB_num(String b_num) {
		this.b_num = b_num;
	}
	public String getB_lendStatus() {
		return b_lendStatus;
	}
	public void setB_lendStatus(String b_lendStatus) {
		this.b_lendStatus = b_lendStatus;
	}
	public String getB_returnStatus() {
		return b_returnStatus;
	}
	public void setB_returnStatus(String b_returnStatus) {
		this.b_returnStatus = b_returnStatus;
	}
	public long getB_overdue() {
		return b_overdue;
	}
	public void setB_overdue(long b_overdue) {
		this.b_overdue = b_overdue;
	}
	@Override
	public String toString() {
		return "book [b_id=" + b_id + ", b_name=" + b_name + ", b_writer=" + b_writer + ", b_inputDate=" + b_inputDate
				+ ", b_lendDate=" + b_lendDate + ", b_returnDate=" + b_returnDate + ", b_sort=" + b_sort + ", b_num="
				+ b_num + ", b_lendStatus=" + b_lendStatus + ", b_returnStatus=" + b_returnStatus + ", b_overdue="
				+ b_overdue + "]";
	}
}
