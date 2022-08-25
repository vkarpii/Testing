package com.webapp.database.user;

public class UserConstants {
    UserConstants(){}
    static final String USER_ID = "user_id";
    static final String ROLE_ID = "role_id";
    static final String LOGIN = "login";
    static final String FIRST_NAME = "first_name";
    static final String LAST_NAME = "last_name";
    static final String EMAIL = "email";
    static final String BLOCKED = "blocked";

    public static final String GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = ?";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    public static final String INSERT_USER = "insert into user (role_id,login,password,email,first_name,last_name,blocked) values(?,?,?,?,?,?,?)";
    public static final String INSERT_GROUP_ALL = "insert into group_user (group_id,user_id) values(?,?)";
    public static final String GET_RESULTS = "select result.result, test.test_name\n" +
            "\tfrom result join test\n" +
            "\ton result.test_id = test.test_id \n" +
            "    where result.user_id = ?";
    public static final String GET_ATTEMPS = "SELECT test_attemps \n" +
            "\tFROM testingdb.result\n" +
            "\t\tWHERE user_id = ? AND test_id = ?";
    public static final String GET_RESULT_BY_TEST_USER = "SELECT * \n" +
            "\tFROM result\n" +
            "\t\tWHERE user_id = ? AND test_id = ?";
    public static final String INSERT_RESULT = "insert into result (user_id,test_id,result,test_attemps,test_time) values(?,?,?,?,?)";
    public static final String UPDATE_RESULT = "UPDATE result\n" +
            "SET result = ?, test_attemps = ?\n" +
            "WHERE user_id = ? AND test_id = ?";
    public static final String GET_CURRENT_RESULT = "SELECT result \n" +
            "\tFROM result\n" +
            "\t\tWHERE user_id = ? AND test_id = ?;";
    public static final String USER_UPDATE = "UPDATE `user`\n" +
            "\tSET role_id = ?, login = ?, email = ?,first_name = ?, last_name = ?\n" +
            "\tWHERE user_id = ?";
    public static final String USER_COUNT = "select count(*)\n" +
            "\tfrom user";
    public static final String GET_LIMIT_USERS = "select SQL_CALC_FOUND_ROWS * from user limit ";

    private static final String UPDATE_USER = "update `user`\n";
    public static final String CHANGE_BLOCK = UPDATE_USER +
            "\tset blocked = ?\n" +
            "    where user_id = ?";
    public static final String CHANGE_ROLE = UPDATE_USER +
            "\tset role_id = ?\n" +
            "    where user_id = ?";
    public static final String DELETE_USER = "delete\n" +
            "\tfrom user\n" +
            "\twhere user_id = ?";
    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";
    public static final String UPDATE_PASSWORD = UPDATE_USER +
            "\tset `password` = ?\n" +
            "    where login = ?";
    public static final String GET_EMAIL_AND_PASSWORD_BY_LOGIN = "SELECT email,`password` \n" +
            "\tfrom `user`\n" +
            "    where login = ?";
    public static final String GET_GROUPS_BY_USER_ID = "SELECT `group`.group_id,`group`.group_name\n" +
            "\tfrom group_user\n" +
            "\tjoin `group` on group_user.group_id = `group`.group_id\n" +
            "\twhere group_user.user_id = ?";
    static final String LOG_ERROR = "Error :{}";
}
