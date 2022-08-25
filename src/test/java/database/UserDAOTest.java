package database;

import com.webapp.database.DataSource;
import com.webapp.database.user.UserDAO;
import com.webapp.entity.Role;
import com.webapp.entity.User;
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

import static com.webapp.database.user.UserConstants.*;

@ExtendWith(MockitoExtension.class)
class UserDAOTest {
    @Mock
    Connection connection;
    @Mock
    PreparedStatement statement;
    @Mock
    ResultSet resultSet;

    @Test
    void checkInstance(){
        UserDAO userDAO = UserDAO.getInstance();
        Assertions.assertEquals(userDAO,UserDAO.getInstance());
    }
    @Test
    void updateTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(USER_UPDATE))
                    .thenReturn(statement);
            User user = new User("login","first","passs","second","example@gmail.com", Role.STUDENT,true);
            user.setId(99);
            Assertions.assertTrue(UserDAO.getInstance().update(user));
        }
    }
    @Test
    void getEntityByStringTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertNull(UserDAO.getInstance().getEntityByString("login","pass"));
        }
    }
    @Test
    void getEntityByIdTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(GET_USER_BY_ID))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertNull(UserDAO.getInstance().getEntityById(99));
        }
    }
    @Test
    void checkEntityByLoginTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(GET_USER_BY_LOGIN))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertFalse(UserDAO.getInstance().checkEntityByLogin("login"));
        }
    }
    @Test
    void deleteTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(DELETE_USER))
                    .thenReturn(statement);
            Assertions.assertTrue(UserDAO.getInstance().delete(99));
        }
    }
    @Test
    void createTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS))
                    .thenReturn(statement);
            mocked.when(statement::getGeneratedKeys).thenReturn(resultSet);
            Assertions.assertTrue(UserDAO.getInstance().create(new User("log","first","pass","-","example@gmail.com",Role.STUDENT,true)));
        }
    }
    @Test
    void addEntityToGroupAllTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(INSERT_GROUP_ALL))
                    .thenReturn(statement);
            User user = new User();
            user.setId(99);
            Assertions.assertTrue(UserDAO.getInstance().addEntityToGroupAll(user));
        }
    }
    @Test
    void getEntityResultTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(GET_RESULTS))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            User user = new User();
            user.setId(99);
            Assertions.assertEquals(UserDAO.getInstance().getEntityResult(user),new ArrayList<>());
        }
    }
    @Test
    void getAttempsTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(GET_ATTEMPS))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            User user = new User();
            user.setId(99);
            com.webapp.entity.Test test = new com.webapp.entity.Test();
            test.setId(99);
            Assertions.assertEquals(UserDAO.getInstance().getAttemps(test,user),0);
        }
    }
    @Test
    void checkResultTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(GET_RESULT_BY_TEST_USER))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            User user = new User();
            user.setId(99);
            com.webapp.entity.Test test = new com.webapp.entity.Test();
            test.setId(99);
            Assertions.assertFalse(UserDAO.getInstance().checkResult(test,user));
        }
    }
    @Test
    void addResultTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(INSERT_RESULT))
                    .thenReturn(statement);
            User user = new User();
            user.setId(99);
            com.webapp.entity.Test test = new com.webapp.entity.Test();
            test.setId(99);
            Assertions.assertTrue(UserDAO.getInstance().addResult(test,user,100.0));
        }
    }
    @Test
    void getCurrResultTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(GET_CURRENT_RESULT))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            User user = new User();
            user.setId(99);
            com.webapp.entity.Test test = new com.webapp.entity.Test();
            test.setId(99);
            Assertions.assertEquals(UserDAO.getInstance().getCurrResult(test,user),0);
        }
    }
    @Test
    void updateResultTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(UPDATE_RESULT))
                    .thenReturn(statement);
            User user = new User();
            user.setId(99);
            com.webapp.entity.Test test = new com.webapp.entity.Test();
            test.setId(99);
            Assertions.assertTrue(UserDAO.getInstance().updateResult(test,user,100.0,1));
        }
    }
    @Test
    void getCountTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(USER_COUNT))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertEquals(UserDAO.getInstance().getCount(),0);
        }
    }
    @Test
    void getLimit(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(connection::createStatement)
                    .thenReturn(statement);
            mocked.when(() ->
                    statement.executeQuery(GET_LIMIT_USERS  + 2 + ", " + 2)).thenReturn(resultSet);
            Assertions.assertEquals(UserDAO.getInstance().getLimit(2,2),new ArrayList<>());
        }
    }
    @Test
    void changeBlockTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(CHANGE_BLOCK))
                    .thenReturn(statement);
            Assertions.assertTrue(UserDAO.getInstance().changeBlock(99,"block"));
        }
    }
    @Test
    void changeRoleTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(CHANGE_ROLE))
                    .thenReturn(statement);
            Assertions.assertTrue(UserDAO.getInstance().changeRole(99,"down"));
        }
    }
    @Test
    void updatePasswordTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(UPDATE_PASSWORD))
                    .thenReturn(statement);
            Assertions.assertTrue(UserDAO.getInstance().updatePassword("log","newpass"));
        }
    }
    @Test
    void getUserByLoginTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(GET_EMAIL_AND_PASSWORD_BY_LOGIN))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertNull(UserDAO.getInstance().getUserByLogin("log"));
        }
    }
    @Test
    void getGroupsByUserIdTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(GET_GROUPS_BY_USER_ID))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertEquals(UserDAO.getInstance().getGroupsByUserId(99),new ArrayList<>());
        }
    }
}
