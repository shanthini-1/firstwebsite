package com.chainsys.miniproject.pojo;

import java.util.Date;

public class Appointment {
	private int appointment_id ;
	private Date appointment_date;
	private String patient_name;
	private long pat_phone_no;
	private int doc_id;
	private float fee_collected;
	
	public int getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	public Date getAppointment_date() {
		return appointment_date;
	}
	public void setAppointment_date(Date appointment_date) {
		this.appointment_date = appointment_date;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public long getPat_phone_no() {
		return pat_phone_no;
	}
	public void setPat_phone_no(long pat_phone_no) {
		this.pat_phone_no = pat_phone_no;
	}
	public int getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}
	public float getFee_collected() {
		return fee_collected;
	}
	public void setFee_collected(float fee_collected) {
		this.fee_collected = fee_collected;
	}
	

  
}
