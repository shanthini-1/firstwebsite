package com.chainsys.miniproject.pojo;

import java.util.Date;

public class Doctors {
	private int doc_id;
	private String doc_name;
	private Date doc_dob;
	private String doc_email;
	private String speciality;
	private String city;
	private long doc_phone_no;
	private float standard_fee;
	
	public int getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public String getDoc_email() {
		return doc_email;
	}
	public void setDoc_email(String doc_email) {
		this.doc_email = doc_email;
	}
	public Date getDoc_dob() {
		return doc_dob;
	}
	public void setDoc_dob(Date doc_dob) {
		this.doc_dob = doc_dob;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getDoc_phone_no() {
		return doc_phone_no;
	}
	public void setDoc_phone_no(long doc_phone_no) {
		this.doc_phone_no = doc_phone_no;
	}
	public float getStandard_fee() {
		return standard_fee;
	}
	public void setStandard_fee(float standard_fee) {
		this.standard_fee = standard_fee;
	}


}
