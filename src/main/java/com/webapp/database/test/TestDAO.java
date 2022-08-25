package com.webapp.database.test;

import com.webapp.database.AbstractDAO;
import com.webapp.database.DataSource;
import com.webapp.entity.Answer;
import com.webapp.entity.Question;
import com.webapp.entity.Subject;
import com.webapp.entity.Test;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;

import static com.webapp.database.test.TestConstants.*;

@Slf4j
public class TestDAO extends AbstractDAO<Test,Integer,String> {
    private static TestDAO testDAO;
    private TestDAO() {
    }

    public static synchronized TestDAO getInstance() {
        if (testDAO == null) {
            testDAO = new TestDAO();
        }
        return testDAO;
    }
    @Override
    public ArrayList<Test> getAll() {
        ArrayList<Test> tests = null;
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_TESTS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            tests = new ArrayList<>();
            while (resultSet.next()) {
                Test test = new Test();
                test.setId(resultSet.getInt(TEST_ID));
                test.setLocaleId(resultSet.getInt(LOCALE_ID));
                test.setName(resultSet.getString(TEST_NAME));
                test.setMaxTime(resultSet.getInt(TEST_MAX_TIME));
                test.setMaxAttemps(resultSet.getInt(TEST_MAX_ATTEMPS));
                test.setComplexity(resultSet.getInt(COMPLEXITY));
                tests.add(test);
            }
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return tests;
    }

    @Override
    public boolean update(Test entity) {
        return false;
    }

    @Override
    public Test getEntityById(Integer id) {
        Test test = null;
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TEST_BY_ID)) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            test = new Test();
            if (resultSet.next()){
                test.setId(resultSet.getInt(TEST_ID));
                test.setLocaleId(resultSet.getInt(LOCALE_ID));
                test.setName(resultSet.getString(TEST_NAME));
                test.setMaxTime(resultSet.getInt(TEST_MAX_TIME));
                test.setMaxAttemps(resultSet.getInt(TEST_MAX_ATTEMPS));
                test.setComplexity(resultSet.getInt(COMPLEXITY));
            }
        } catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
        return test;
    }

    @Override
    public Test getEntityByString(String firstParam, String secondParam) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        try(Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TEST)) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    @Override
    public boolean create(Test entity) {
        entity.setId(0);
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEST, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setInt(1,entity.getId());
            preparedStatement.setInt(2,entity.getLocaleId());
            preparedStatement.setInt(3,entity.getSubject().getId());
            preparedStatement.setString(4,entity.getName());
            preparedStatement.setInt(5,entity.getMaxTime());
            preparedStatement.setInt(6,entity.getMaxAttemps());
            preparedStatement.setInt(7,entity.getComplexityBoolean());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                entity.setId(resultSet.getInt(1));
            }
            return true;
        } catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    public boolean addQuestions(Test test) {
        ArrayList<Question> questions = test.getQuestions();
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUESTION, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i != questions.size();i++) {
                questions.get(i).setId(0);
                preparedStatement.setInt(1, questions.get(i).getId());
                preparedStatement.setString(2, questions.get(i).getText());
                preparedStatement.setBoolean(3, questions.get(i).isCheckbox());
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    questions.get(i).setId(resultSet.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
    return false;
    }
    public boolean addTestQuestion(Test test) {
        ArrayList<Question> questions = test.getQuestions();
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEST_QUESTION)) {
            preparedStatement.setInt(2,test.getId());
            for (int i = 0; i != questions.size();i++){
                preparedStatement.setInt(1,questions.get(i).getId());
                preparedStatement.execute();
            }
            return true;
        } catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }
    public boolean addAnswers(Test test) {
        ArrayList<Question> questions = test.getQuestions();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ANSWER, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i != questions.size(); i++) {
                ArrayList<Answer> answers = questions.get(i).getAnswers();
                for (int j = 0; j != answers.size(); j++) {
                    answers.get(j).setId(0);
                    preparedStatement.setInt(1, answers.get(j).getId());
                    preparedStatement.setString(2, answers.get(j).getText());
                    preparedStatement.setBoolean(3, answers.get(j).getCorrect());
                    preparedStatement.execute();
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next()) {
                        answers.get(j).setId(resultSet.getInt(1));
                    }

                }
            }
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
    return false;
    }
    public boolean addQuestionAnswer(Test test) {
        ArrayList<Question> questions = test.getQuestions();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUESTION_ANSWER)){
            for (int i = 0; i != questions.size();i++){
                ArrayList<Answer> answers = questions.get(i).getAnswers();
                preparedStatement.setInt(2,questions.get(i).getId());
                for (int j = 0;j!= answers.size();j++){
                    preparedStatement.setInt(1,answers.get(j).getId());
                    preparedStatement.execute();
                }
            }
            return true;
        } catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }
    public boolean addTestGroup(Test test) {
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEST_GROUP)) {
            preparedStatement.setInt(1,test.getId());
            preparedStatement.setInt(2,test.getGroup().getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }
    public boolean getQuestionsForTest(Test test){
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUESTIONS_BY_TEST)) {
            preparedStatement.setInt(1,test.getId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Question> questions = new ArrayList<>();
            while (resultSet.next()){
                Question question = new Question();
                question.setId(resultSet.getInt(1));
                question.setText(resultSet.getString(2));
                question.setCheckbox(resultSet.getBoolean(3));
                questions.add(question);
            }
            test.setQuestions(questions);
            return true;
        } catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }
    public boolean getAnswersForQuestions(Test test) {
        ArrayList<Question> questions = test.getQuestions();
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ANSWERS_BY_QUESTIONS)) {
            for (int i = 0;i!=questions.size();i++){
                preparedStatement.setInt(1,questions.get(i).getId());
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.executeQuery();
                ArrayList<Answer> answers = new ArrayList<>();
                while (resultSet.next()){
                    Answer answer = new Answer();
                    answer.setId(resultSet.getInt(1));
                    answer.setText(resultSet.getString(2));
                    answer.setCorrect(resultSet.getBoolean(3));
                    answers.add(answer);
                }
                questions.get(i).setAnswers(answers);
            }
            return true;
        }catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    public boolean deleteResults(int idTest) {
        try(Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESULTS)) {
            preparedStatement.setInt(1,idTest);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    public int getCount() {
        int result = 0;
        try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(TEST_COUNT);
        ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()){
                result = resultSet.getInt(1);
            }
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return result;
    }

    public ArrayList<Test> getLimit(String query) {
        ArrayList<Test> tests = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Test newTest = new Test();
                newTest.setId(resultSet.getInt(TEST_ID));
                newTest.setLocaleId(resultSet.getInt(LOCALE_ID));
                newTest.setName(resultSet.getString(TEST_NAME));
                newTest.setMaxTime(resultSet.getInt(TEST_MAX_TIME));
                newTest.setMaxAttemps(resultSet.getInt(TEST_MAX_ATTEMPS));
                newTest.setComplexity(resultSet.getInt(COMPLEXITY));
                newTest.setNumberOfQueries(resultSet.getInt("number_of_queries"));
                newTest.setSubject(new Subject(resultSet.getInt("sub_id"),resultSet.getString("sub_name")));
                tests.add(newTest);
            }
            resultSet.close();
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return tests;
    }

    public int getCountByLIKE(String pattern) {
        int count = 0;
        try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNT_BY_LIKE + pattern)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                count = resultSet.getInt(1);
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return count;
    }

    public ArrayList<Test> getLimitByLIKE(int offset, int noOfRecords, String pattern,int id,int role) {
        ArrayList<Test> tests = new ArrayList<>();
        String sql = GET_LIMIT_TESTS_BY_LIKE;
        if (role == 2){
            sql += ") and test_name like '";
        } else {
            sql += "join `user` on `user`.user_id = group_user.user_id\n" +
                    "where `user`.user_id = " + id + ") and test_name like '";
        }
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql + pattern + " group by test.test_id " + " limit "  + offset + ", " + noOfRecords)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Test newTest = new Test();
                newTest.setId(resultSet.getInt(TEST_ID));
                newTest.setLocaleId(resultSet.getInt(LOCALE_ID));
                newTest.setName(resultSet.getString(TEST_NAME));
                newTest.setMaxTime(resultSet.getInt(TEST_MAX_TIME));
                newTest.setMaxAttemps(resultSet.getInt(TEST_MAX_ATTEMPS));
                newTest.setComplexity(resultSet.getInt(COMPLEXITY));
                newTest.setNumberOfQueries(resultSet.getInt("number_of_queries"));
                newTest.setSubject(new Subject(resultSet.getInt("sub_id"),resultSet.getString("sub_name")));
                tests.add(newTest);
            }
            resultSet.close();
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return tests;
    }

    public boolean deleteAllForGroup(String name) {
        try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TESTS_FOR_GROUP)) {
            preparedStatement.setString(1,name);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    public boolean deleteAllForSubject(String name) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TESTS_FOR_SUBJECT)) {
            preparedStatement.setString(1,name);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }
}
