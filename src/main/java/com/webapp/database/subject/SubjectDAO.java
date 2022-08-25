package com.webapp.database.subject;

import com.webapp.database.AbstractDAO;
import com.webapp.database.DataSource;
import com.webapp.entity.Subject;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;

import static com.webapp.database.subject.SubjectConstants.*;

@Slf4j
public class SubjectDAO extends AbstractDAO<Subject,Integer,String> {
    private static SubjectDAO subjectDAO;
    private SubjectDAO() {
    }

    public static synchronized SubjectDAO getInstance() {
        if (subjectDAO == null) {
            subjectDAO = new SubjectDAO();
        }
        return subjectDAO;
    }

    public boolean checkName(Subject newSubject) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_SUBJECT_NAME)) {
            preparedStatement.setString(1,newSubject.getName());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    @Override
    public ArrayList<Subject> getAll() {
        ArrayList<Subject> subjects = null;
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SUBJECTS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            subjects = new ArrayList<>();
            while (resultSet.next()){
                Subject subject = new Subject();
                subject.setId(resultSet.getInt(SUBJECT_ID));
                subject.setName(resultSet.getString(SUBJECT_NAME));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
        return subjects;
    }

    @Override
    public boolean update(Subject entity) {
        return false;
    }

    @Override
    public Subject getEntityById(Integer id) {
        Subject subject = new Subject();
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBJECT_BY_ID)) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                subject.setId(id);
                subject.setName(resultSet.getString(SUBJECT_NAME));
            }

        } catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
        return subject;
    }

    @Override
    public Subject getEntityByString(String firstParam, String secondParam) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(Subject entity) {
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1,entity.getId());
            preparedStatement.setString(2,entity.getName());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                entity.setId(resultSet.getInt(1));
            }
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    public boolean deleteByName(String name) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUBJECT)){
            preparedStatement.setString(1,name);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }
}
