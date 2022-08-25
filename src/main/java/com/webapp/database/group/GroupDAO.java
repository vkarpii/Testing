package com.webapp.database.group;

import com.webapp.database.AbstractDAO;
import com.webapp.database.DataSource;
import com.webapp.entity.Group;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;

import static com.webapp.database.group.GroupConstants.*;

@Slf4j
public class GroupDAO extends AbstractDAO<Group,Integer,String> {
    private static GroupDAO groupDAO;
    private GroupDAO() {
    }

    public static synchronized GroupDAO getInstance() {
        if (groupDAO == null) {
            groupDAO = new GroupDAO();
        }
        return groupDAO;
    }

    public boolean checkName(Group newGroup) {
        try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_GROUP_NAME)) {
            preparedStatement.setString(1,newGroup.getName());
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
    public ArrayList<Group> getAll() {
        ArrayList<Group> groups = null;
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_GROUPS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            groups = new ArrayList<>();
            while (resultSet.next()){
                Group group = new Group();
                group.setId(resultSet.getInt(GROUP_ID));
                group.setName(resultSet.getString(GROUP_NAME));
                groups.add(group);
            }
        } catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
        return groups;
    }

    @Override
    public boolean update(Group entity) {
        return false;
    }

    @Override
    public Group getEntityById(Integer id) {
        Group group = null;
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GROUP_BY_ID)) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            group = new Group();
            if (resultSet.next()){
                group.setId(id);
                group.setName(resultSet.getString(GROUP_NAME));
            }

        } catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
        return group;
    }

    @Override
    public Group getEntityByString(String firstParam, String secondParam) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(Group entity) {
            try(Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROUP, Statement.RETURN_GENERATED_KEYS)) {
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
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GROUP)){
            preparedStatement.setString(1,name);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    public boolean addUserToGroup(int idUser, int group) {
        return userAndGroup(idUser, group, ADD_USER_TO_GROUP);
    }

    private boolean userAndGroup(int idUser, int group, String addUserToGroup) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addUserToGroup)) {
            preparedStatement.setInt(1,group);
            preparedStatement.setInt(2,idUser);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    public boolean deleteUserFromGroup(int idUser, int group) {
        return userAndGroup(idUser, group, DELETE_USER_FROM_GROUP);
    }
}
