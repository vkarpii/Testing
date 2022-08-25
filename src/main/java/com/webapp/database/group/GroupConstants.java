package com.webapp.database.group;

public class GroupConstants {
    GroupConstants(){}
    static final String GROUP_ID = "group_id";
    static final String GROUP_NAME = "group_name";

    public static final String GET_ALL_GROUPS = "SELECT * FROM `group`";
    public static final String SELECT_GROUP_BY_ID = "SELECT * FROM `group` WHERE group_id = ?";
    public static final String INSERT_GROUP = "insert into `group` (group_id,group_name) values(?,?)";
    public static final String DELETE_GROUP = "DELETE FROM `group` WHERE group_name = ?";
    public static final String CHECK_GROUP_NAME = "select * from `group` where group_name = ?";
    public static final String ADD_USER_TO_GROUP = "insert into group_user (group_id,user_id) \n" +
            "values(?,?)";
    public static final String DELETE_USER_FROM_GROUP = "delete from group_user " +
            "            where group_id = ? and user_id = ?";
    static final String LOG_ERROR = "Error :{}";
}
