package com.imageuploader.data.model;

import java.util.Date;

public class PartExpiryModel {
	private String partNumber;
	private boolean isExpiredFlag;
	private Date expiredDate;
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public boolean isExpiredFlag() {
		return isExpiredFlag;
	}
	public void setExpiredFlag(boolean isExpiredFlag) {
		this.isExpiredFlag = isExpiredFlag;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
}
