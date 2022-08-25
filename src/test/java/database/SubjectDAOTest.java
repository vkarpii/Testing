package database;

import com.webapp.database.DataSource;
import com.webapp.database.group.GroupDAO;
import com.webapp.database.subject.SubjectDAO;
import com.webapp.entity.Group;
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

import static com.webapp.database.subject.SubjectConstants.*;

@ExtendWith(MockitoExtension.class)
class SubjectDAOTest {
    @Mock
    Connection connection;
    @Mock
    PreparedStatement statement;
    @Mock
    ResultSet resultSet;

    @Test
    void checkInstance(){
        SubjectDAO subjectDAO = SubjectDAO.getInstance();
        Assertions.assertEquals(subjectDAO,SubjectDAO.getInstance());
    }

    @Test
    void checkNameTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(CHECK_SUBJECT_NAME))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertFalse(SubjectDAO.getInstance().checkName(new Subject("Error")));
        }
    }
    @Test
    void getAllTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(GET_ALL_SUBJECTS))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertEquals(SubjectDAO.getInstance().getAll(),new ArrayList<Subject>());
        }
    }
    @Test
    void getEntityByIdTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(SELECT_SUBJECT_BY_ID))
                    .thenReturn(statement);
            mocked.when(statement::executeQuery).thenReturn(resultSet);
            Assertions.assertEquals(SubjectDAO.getInstance().getEntityById(99),new Subject());
        }
    }
    @Test
    void createTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(INSERT_SUBJECT, Statement.RETURN_GENERATED_KEYS))
                    .thenReturn(statement);
            mocked.when(statement::getGeneratedKeys).thenReturn(resultSet);
            Assertions.assertTrue(SubjectDAO.getInstance().create(new Subject("Test")));
        }
    }
    @Test
    void deleteByNameTest(){
        try (MockedStatic<DataSource> mocked = Mockito.mockStatic(DataSource.class)) {
            mocked.when(DataSource::getConnection).thenReturn(connection);
            mocked.when(() -> connection.prepareStatement(DELETE_SUBJECT))
                    .thenReturn(statement);
            Assertions.assertTrue(SubjectDAO.getInstance().deleteByName("Test"));
        }
    }
}
