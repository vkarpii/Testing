package com.webapp.database.subject;

public class SubjectConstants {
    SubjectConstants(){}
    static final String SUBJECT_ID = "sub_id";
    static final String SUBJECT_NAME = "sub_name";
    public static final String GET_ALL_SUBJECTS = "SELECT * FROM `subject`";
    public static final String CHECK_SUBJECT_NAME = "select * from `subject` where sub_name = ?";
    public static final String INSERT_SUBJECT = "insert into `subject` (sub_id,sub_name) values(?,?)";
    public static final String DELETE_SUBJECT = "DELETE FROM `subject` WHERE sub_name = ?";
    public static final String SELECT_SUBJECT_BY_ID = "SELECT * FROM `subject` WHERE sub_id = ?";
    static final String LOG_ERROR = "Error :{}";
}
