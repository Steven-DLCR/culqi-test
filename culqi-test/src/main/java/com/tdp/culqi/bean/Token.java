package com.tdp.culqi.bean;

public class Token 
{
	private String pan;
	private int expYear;
	private int expMonth;
	
	public Token() {
		super();
	}
	
	public Token(String pan, int expYear, int expMonth) {
		super();
		this.pan = pan;
		this.expYear = expYear;
		this.expMonth = expMonth;
	}
	
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public int getExpYear() {
		return expYear;
	}
	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}
	public int getExpMonth() {
		return expMonth;
	}
	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}
}
