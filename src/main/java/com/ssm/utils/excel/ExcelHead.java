package com.ssm.utils.excel;

/**
 * excel 表头字段对应关系（文档与pojo名不一致的时候使用）
 * 
 * @author swm
 *
 */
public class ExcelHead {
	private String excelName; // Excel名
	private String entityName; // 实体类属性名
	private boolean required = false; // true 值必填
	
	public String getExcelName() {
		return excelName;
	}
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public ExcelHead() {
		
	}

	public ExcelHead(String excelName, String entityName) {
		this.excelName = excelName;
		this.entityName = entityName;
	}
	
	public ExcelHead(String excelName, String entityName, boolean required) {
		super();
		this.excelName = excelName;
		this.entityName = entityName;
		this.required = required;
	}
	
}
