package com.imageuploader.data.model;

public class PartInformationModel {
	
	private String partNumber;
	private String partName;
	private String kanbanNumber;
	private String supplierCode;
	private String expeditorID;
	private String depoCode;
	private boolean partSequencedFlag;
	private String location;
	/**
	 * @return the partNumber
	 */
	public String getPartNumber() {
		return partNumber;
	}
	/**
	 * @param partNumber the partNumber to set
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	/**
	 * @return the partName
	 */
	public String getPartName() {
		return partName;
	}
	/**
	 * @param partName the partName to set
	 */
	public void setPartName(String partName) {
		this.partName = partName;
	}
	/**
	 * @return the supplierCode
	 */
	public String getSupplierCode() {
		return supplierCode;
	}
	/**
	 * @param supplierCode the supplierCode to set
	 */
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	/**
	 * @return the expeditorID
	 */
	public String getExpeditorID() {
		return expeditorID;
	}
	/**
	 * @param expeditorID the expeditorID to set
	 */
	public void setExpeditorID(String expeditorID) {
		this.expeditorID = expeditorID;
	}
	/**
	 * @return the depoCode
	 */
	public String getDepoCode() {
		return depoCode;
	}
	/**
	 * @param depoCode the depoCode to set
	 */
	public void setDepoCode(String depoCode) {
		this.depoCode = depoCode;
	}
	/**
	 * @return the partSequencedFlag
	 */
	public boolean isPartSequencedFlag() {
		return partSequencedFlag;
	}
	/**
	 * @param partSequencedFlag the partSequencedFlag to set
	 */
	public void setPartSequencedFlag(boolean partSequencedFlag) {
		this.partSequencedFlag = partSequencedFlag;
	}
	/**
	 * @return the kanbanNumber
	 */
	public String getKanbanNumber() {
		return kanbanNumber;
	}
	/**
	 * @param kanbanNumber the kanbanNumber to set
	 */
	public void setKanbanNumber(String kanbanNumber) {
		this.kanbanNumber = kanbanNumber;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
}