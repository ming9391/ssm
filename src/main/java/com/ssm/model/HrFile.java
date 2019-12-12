package com.ssm.model;

import java.util.Date;

public class HrFile {

	private String employee_no;
    private String given_name;
    private String surname;
    private String chinese_name;
    private String alias_name;
    private String email;
    private String employee_status;
    private Date employee_status_date;
    private String division;
    private String section;
    private String department;
    private String position;
    private Date date_join;
    private String staff_grade_code;
    private String staff_grade_description;

    public String getEmployee_no() {
        return employee_no;
    }

    public void setEmployee_no(String employee_no) {
        this.employee_no = employee_no;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getChinese_name() {
        return chinese_name;
    }

    public void setChinese_name(String chinese_name) {
        this.chinese_name = chinese_name;
    }

    public String getAlias_name() {
        return alias_name;
    }

    public void setAlias_name(String alias_name) {
        this.alias_name = alias_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployee_status() {
        return employee_status;
    }

    public void setEmployee_status(String employee_status) {
        this.employee_status = employee_status;
    }

    public Date getEmployee_status_date() {
        return employee_status_date;
    }

    public void setEmployee_status_date(Date employee_status_date) {
        this.employee_status_date = employee_status_date;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getDate_join() {
        return date_join;
    }

    public void setDate_join(Date date_join) {
        this.date_join = date_join;
    }

    public String getStaff_grade_code() {
        return staff_grade_code;
    }

    public void setStaff_grade_code(String staff_grade_code) {
        this.staff_grade_code = staff_grade_code;
    }

    public String getStaff_grade_description() {
        return staff_grade_description;
    }

    public void setStaff_grade_description(String staff_grade_description) {
        this.staff_grade_description = staff_grade_description;
    }

}
