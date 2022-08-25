package com.webapp.database.test;

public class TestConstants {
    TestConstants(){}
    static final String TEST_ID = "test_id";
    static final String LOCALE_ID = "locale_id";
    static final String TEST_NAME = "test_name";
    static final String TEST_MAX_TIME = "test_max_time";
    static final String TEST_MAX_ATTEMPS = "test_max_attemps";
    static final String COMPLEXITY = "complexity";
    public static final String GET_ALL_TESTS = "SELECT * FROM test";
    public static final String INSERT_TEST = "insert into test (test_id,locale_id,sub_id,test_name,test_max_time,test_max_attemps,complexity) values(?,?,?,?,?,?,?)";
    public static final String INSERT_QUESTION = "insert into question (qa_id,qa_text,checkbox) values(?,?,?)";
    public static final String INSERT_TEST_QUESTION = "insert into test_question (qa_id,test_id) values(?,?)";
    public static final String INSERT_ANSWER = "insert into answer (ans_id,ans_text,correct) values(?,?,?)";
    public static final String INSERT_QUESTION_ANSWER = "insert into question_answer (ans_id,qa_id) values(?,?)";
    public static final String INSERT_TEST_GROUP = "insert into test_group (test_id,group_id) values(?,?)";
    public static final String GET_TEST_BY_ID = "SELECT * FROM test WHERE test_id = ?";
    public static final String SELECT_QUESTIONS_BY_TEST = "select question.qa_id,question.qa_text,question.checkbox\n" +
            "            from question join test_question\n" +
            "            on question.qa_id = test_question.qa_id\n" +
            "            where test_question.test_id = ?";
    public static final String SELECT_ANSWERS_BY_QUESTIONS = "select answer.ans_id,answer.ans_text,answer.correct\n" +
            "            from answer join question_answer\n" +
            "            on answer.ans_id = question_answer.ans_id\n" +
            "            where question_answer.qa_id = ?";
    public static final String DELETE_TEST = "delete\n" +
            "\tfrom test\n" +
            "\twhere test_id = ?";
    public static final String DELETE_RESULTS = "delete\n" +
            "\tfrom result\n" +
            "    where test_id = ?";
    public static final String DELETE_TESTS_FOR_GROUP = "delete test,test_group\n" +
            "\tfrom test\n" +
            "    join test_group on test.test_id = test_group.test_id\n" +
            "    join `group` on `group`.group_id = test_group.group_id\n" +
            "\twhere `group`.group_name = ?";
    public static final String DELETE_TESTS_FOR_SUBJECT = "delete test\n" +
            "\tfrom test\n" +
            "    join `subject` on `subject`.sub_id = test.sub_id\n" +
            "\twhere `subject`.sub_name = ?";
    public static final String TEST_COUNT = "select count(*)\n" +
            "\tfrom test";
    public static final String GET_LIMIT_TESTS = "select SQL_CALC_FOUND_ROWS  test.* , count(test_question.qa_id) as 'number_of_queries', `subject`.*\n" +
            "            from test_question\n" +
            "            join test on test.test_id = test_question.test_id\n" +
            "            join `subject` on test.sub_id = `subject`.sub_id \n" +
            "            join test_group on test.test_id = test_group.test_id \n";
    static final String SELECT_FOUND_ROWS = "SELECT FOUND_ROWS()";
    public static final String GET_COUNT_BY_LIKE = "select count(*) from test where test_name like '";

    static final String GET_LIMIT_TESTS_BY_LIKE = "select SQL_CALC_FOUND_ROWS  test.* , count(test_question.qa_id) as 'number_of_queries', `subject`.*\n" +
            "            from test_question\n" +
            "            join test on test.test_id = test_question.test_id\n" +
            "            join `subject` on test.sub_id = `subject`.sub_id\n" +
            "            join test_group on test.test_id = test_group.test_id" +
            "               where test_group.group_id  IN(select group_user.group_id\n" +
            "\t\t\t\t\t\t\t\t\tfrom group_user\n";
    public static final String LOG_ERROR = "Error :{}";

}
