package database;

import com.mysql.cj.interceptors.QueryInterceptor;
import com.webapp.database.DataSource;
import com.webapp.database.subject.SubjectDAO;
import com.webapp.database.test.TestDAO;
import com.webapp.entity.Answer;
import com.webapp.entity.Question;
import com.webapp.entity.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static com.webapp.database.test.TestConstants.*;

@ExtendWith(MockitoExtension.class)
public class TestDAOTest {
    @Mock
    Connection connection;
    @Mock
    PreparedStatement statement;
    @Mock
    ResultSet resultSet;

    @Test
    void checkInstance(){
        TestDAO testDAO = TestDAO.getInstance();
        Assertions.assertEquals(testDAO,TestDAO.getInstance());
    }
    @Test
    void getAllTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(GET_ALL_TESTS))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertEquals(TestDAO.getInstance().getAll(),new ArrayList<com.webapp.entity.Test>());
        }
    }
    @Test
    void getEntityByIdTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(GET_TEST_BY_ID))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertEquals(TestDAO.getInstance().getEntityById(99),new com.webapp.entity.Test());
        }
    }
    @Test
    void deleteTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(DELETE_TEST))
                    .thenReturn(statement);
            Assertions.assertTrue(TestDAO.getInstance().delete(99));
        }
    }
    @Test
    void createTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(INSERT_TEST, Statement.RETURN_GENERATED_KEYS))
                    .thenReturn(statement);
            mocked.when(statement::getGeneratedKeys).thenReturn(resultSet);
            Assertions.assertTrue(TestDAO.getInstance().create(new com.webapp.entity.Test("Test",99,99,1)));
        }
    }
    @Test
    void addQuestionsTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(INSERT_QUESTION, Statement.RETURN_GENERATED_KEYS))
                    .thenReturn(statement);
            mocked.when(statement::getGeneratedKeys).thenReturn(resultSet);
            com.webapp.entity.Test test = new com.webapp.entity.Test();
            ArrayList<Question> questions = new ArrayList<>();
            ArrayList<Answer> answers = new ArrayList<>();
            answers.add(new Answer("answer",true));
            answers.add(new Answer("answer2",false));
            questions.add(new Question("question",answers));
            test.setQuestions(questions);
            Assertions.assertTrue(TestDAO.getInstance().addQuestions(test));
        }
    }
    @Test
    void addTestQuestionTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(INSERT_TEST_QUESTION))
                    .thenReturn(statement);
            Assertions.assertTrue(TestDAO.getInstance().addTestQuestion(new com.webapp.entity.Test()));
        }
    }
    @Test
    void addAnswersTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(INSERT_ANSWER, Statement.RETURN_GENERATED_KEYS))
                    .thenReturn(statement);
            mocked.when(statement::getGeneratedKeys).thenReturn(resultSet);
            com.webapp.entity.Test test = new com.webapp.entity.Test();
            ArrayList<Question> questions = new ArrayList<>();
            ArrayList<Answer> answers = new ArrayList<>();
            answers.add(new Answer("answer",true));
            answers.add(new Answer("answer2",false));
            questions.add(new Question("question",answers));
            test.setQuestions(questions);
            Assertions.assertTrue(TestDAO.getInstance().addAnswers(test));
        }
    }
    @Test
    void addQuestionAnswerTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(INSERT_QUESTION_ANSWER))
                    .thenReturn(statement);
            Assertions.assertTrue(TestDAO.getInstance().addQuestionAnswer(new com.webapp.entity.Test()));
        }
    }
    @Test
    void addTestGroupTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(INSERT_TEST_GROUP))
                    .thenReturn(statement);
            Assertions.assertTrue(TestDAO.getInstance().addTestGroup(new com.webapp.entity.Test()));
        }
    }
    @Test
    void getQuestionsForTestTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(SELECT_QUESTIONS_BY_TEST))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertTrue(TestDAO.getInstance().getQuestionsForTest(new com.webapp.entity.Test()));
        }
    }
    @Test
    void getAnswersForQuestionsTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(SELECT_ANSWERS_BY_QUESTIONS))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            com.webapp.entity.Test test = new com.webapp.entity.Test();
            test.setId(99);
            test.setName("Test");
            ArrayList<Question> questions = new ArrayList<>();
            questions.add(new Question("question",new ArrayList<>()));
            test.setQuestions(questions);
            Assertions.assertTrue(TestDAO.getInstance().getAnswersForQuestions(test));
        }
    }
    @Test
    void deleteResultsTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(DELETE_RESULTS))
                    .thenReturn(statement);
            Assertions.assertTrue(TestDAO.getInstance().deleteResults(99));
        }
    }
    @Test
    void getCountTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(TEST_COUNT))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertEquals(TestDAO.getInstance().getCount(),0);
        }
    }
    @Test
    void getLimitTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            String query = "select * from testingdb.`test` limit 0,2";
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(query))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertEquals(TestDAO.getInstance().getLimit(query),new ArrayList<>());
        }
    }
    @Test
    void getCountByLIKETest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            String pattern = "%aaa%'";
            mocked.when(() -> connection.prepareStatement(GET_COUNT_BY_LIKE + pattern))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertEquals(TestDAO.getInstance().getCountByLIKE(pattern),0);
        }
    }
    @Test
    void deleteAllForGroupTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(DELETE_TESTS_FOR_GROUP))
                    .thenReturn(statement);
            Assertions.assertTrue(TestDAO.getInstance().deleteAllForGroup("test"));
        }
    }
    @Test
    void deleteAllForSubjectTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(DELETE_TESTS_FOR_SUBJECT))
                    .thenReturn(statement);
            Assertions.assertTrue(TestDAO.getInstance().deleteAllForSubject("test"));
        }
    }

}
