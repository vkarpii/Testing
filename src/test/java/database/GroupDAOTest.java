package database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.webapp.database.DataSource;
import com.webapp.database.group.GroupDAO;
import com.webapp.entity.Group;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static com.webapp.database.group.GroupConstants.*;

@ExtendWith(MockitoExtension.class)
class GroupDAOTest {

    @Mock
    Connection connection;
    @Mock
    PreparedStatement statement;
    @Mock
    ResultSet resultSet;

    @Test
    void checkInstance(){
        GroupDAO groupDAO = GroupDAO.getInstance();
        Assertions.assertEquals(groupDAO,GroupDAO.getInstance());
    }

    @Test
    void createTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            Group group = new Group("New group");
            Group group2 = new Group("Some name");
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(INSERT_GROUP, Statement.RETURN_GENERATED_KEYS))
                    .thenReturn(statement);
            mocked.when(statement::getGeneratedKeys).thenReturn(resultSet);
            GroupDAO groupDAO = GroupDAO.getInstance();
            Assertions.assertTrue(groupDAO.create(group));
            Assertions.assertTrue(groupDAO.create(group2));
        }
    }
    @Test
    void getAllTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(GET_ALL_GROUPS))
                            .thenReturn(statement);
            mocked.when(statement::executeQuery)
                    .thenReturn(resultSet);
            Assertions.assertEquals(GroupDAO.getInstance().getAll(),new ArrayList<Group>());

        }
    }
    @Test
    void checkNameTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(CHECK_GROUP_NAME))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery)
                    .thenReturn(resultSet);
            Assertions.assertFalse(GroupDAO.getInstance().checkName(new Group("12345")));
        }
    }
    @Test
    void getEntityByIdTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(SELECT_GROUP_BY_ID))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery)
                    .thenReturn(resultSet);
            Assertions.assertEquals(GroupDAO.getInstance().getEntityById(999),new Group());
        }
    }
    @Test
    void deleteByNameTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(DELETE_GROUP))
                    .thenReturn(statement);
            Assertions.assertTrue(GroupDAO.getInstance().deleteByName(""));
        }
    }
    @Test
    void addUserToGroupTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(ADD_USER_TO_GROUP))
                    .thenReturn(statement);
            Assertions.assertTrue(GroupDAO.getInstance().addUserToGroup(99,99));
        }
    }
    @Test
    void deleteUserFromGroupTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(DELETE_USER_FROM_GROUP))
                    .thenReturn(statement);
            Assertions.assertTrue(GroupDAO.getInstance().deleteUserFromGroup(99,99));
        }
    }
}
