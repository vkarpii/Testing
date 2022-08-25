package com.webapp.database.user;

import com.webapp.database.AbstractDAO;
import com.webapp.database.DataSource;
import com.webapp.entity.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;

import static com.webapp.database.test.TestConstants.LOG_ERROR;
import static com.webapp.database.user.UserConstants.*;

@Slf4j
public class UserDAO extends AbstractDAO<User,Integer,String> {
    private static UserDAO userDAO;
    private UserDAO() {
    }

    public static synchronized UserDAO getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }
    @Override
    public ArrayList<User> getAll() {
        return new ArrayList<>();
    }

    @Override
    public boolean update(User entity) {
        try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(USER_UPDATE)) {
            preparedStatement.setInt(1,entity.getRole().getId());
            preparedStatement.setString(2,entity.getLogin());
            preparedStatement.setString(3,entity.getEmail());
            preparedStatement.setString(4,entity.getFirst_name());
            preparedStatement.setString(5,entity.getLast_name());
            preparedStatement.setInt(6,entity.getId());
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    @Override
    public User getEntityById(Integer id) {
        User user = null;
        try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt(USER_ID));
                user.setBlocked(resultSet.getBoolean(BLOCKED));
                user.setLogin(resultSet.getString(LOGIN));
                user.setFirst_name(resultSet.getString(FIRST_NAME));
                user.setLast_name(resultSet.getString(LAST_NAME));
                user.setEmail(resultSet.getString(EMAIL));
                user.setRole(Role.getRole(resultSet.getInt(ROLE_ID)));
            }
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return user;
    }

    @Override
    public User getEntityByString(String firstParam, String secondParam){
        User user = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD)){
            preparedStatement.setString(1, firstParam);
            preparedStatement.setString(2, secondParam);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt(USER_ID));
                user.setRole(Role.getRole(resultSet.getInt(ROLE_ID)));
                user.setLogin(resultSet.getString(LOGIN));
                user.setFirst_name(resultSet.getString(FIRST_NAME));
                user.setLast_name(resultSet.getString(LAST_NAME));
                user.setEmail(resultSet.getString(EMAIL));
                user.setBlocked(resultSet.getBoolean(BLOCKED));
            }

        } catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
        return user;
    }
    public boolean checkEntityByLogin(String login){
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN)){
            preparedStatement.setString(1,login);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    @Override
    public boolean create(User entity) {
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1,entity.getId());
            preparedStatement.setInt(1,entity.getRole().getId());
            preparedStatement.setString(2,entity.getLogin());
            preparedStatement.setString(3,entity.getPassword());
            entity.setPassword("");
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getFirst_name());
            preparedStatement.setString(6, entity.getLast_name());
            preparedStatement.setBoolean(7,entity.isBlocked());
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

    public boolean addEntityToGroupAll(User user){
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROUP_ALL)){
            preparedStatement.setInt(1,1);
            preparedStatement.setInt(2,user.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }
    public ArrayList<Result> getEntityResult(User user) {
        ArrayList<Result> results = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RESULTS)){
            preparedStatement.setInt(1,user.getId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Result result = new Result();
                result.setResult(resultSet.getDouble(1));
                result.setNameOfTest(resultSet.getString(2));
                results.add(result);
            }
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return results;
    }
    public int getAttemps(Test test, User user) {
        int result = 0;
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ATTEMPS)) {
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setInt(2,test.getId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                result = resultSet.getInt(1);
            return result;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return result;
    }
    public boolean checkResult(Test test, User user) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RESULT_BY_TEST_USER)){
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setInt(2,test.getId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }
    public boolean addResult(Test test, User user,double result) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESULT)){
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setInt(2,test.getId());
            preparedStatement.setDouble(3,result);
            preparedStatement.setInt(4,1);
            preparedStatement.setInt(5,test.getMaxTime());
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }
    public double getCurrResult(Test test, User user) {
        int result = 0;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CURRENT_RESULT)){
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setInt(2,test.getId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                result = resultSet.getInt(1);
        } catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return result;
    }
    public boolean updateResult(Test test, User user, double result,int attemps) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESULT)){
            preparedStatement.setDouble(1, result);
            preparedStatement.setInt(2,attemps);
            preparedStatement.setInt(3,user.getId());
            preparedStatement.setInt(4,test.getId());
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
             PreparedStatement preparedStatement = connection.prepareStatement(USER_COUNT);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()){
                result = resultSet.getInt(1);
            }
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return result;
    }

    public ArrayList<User> getLimit(int offset, int noOfRecords) {
        ArrayList<User> users = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_LIMIT_USERS  + offset + ", " + noOfRecords);
            while (resultSet.next()){
                User newUser = new User();

                newUser.setId(resultSet.getInt(USER_ID));
                newUser.setLogin(resultSet.getString(LOGIN));
                newUser.setEmail(resultSet.getString(EMAIL));
                newUser.setFirst_name(resultSet.getString(FIRST_NAME));
                newUser.setLast_name(resultSet.getString(LAST_NAME));
                newUser.setRole(Role.getRole(resultSet.getInt(ROLE_ID)));
                newUser.setBlocked(resultSet.getBoolean(BLOCKED));
                users.add(newUser);
            }
            resultSet.close();
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return users;
    }

    public boolean changeBlock(int idUser, String event) {
        boolean block = event.equals("block");
        try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_BLOCK)) {
            preparedStatement.setBoolean(1,block);
            preparedStatement.setInt(2,idUser);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    public boolean changeRole(int idUser, String event) {
        int idRole = event.equals("up")? 2:1;
        try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ROLE)) {
            preparedStatement.setInt(1,idRole);
            preparedStatement.setInt(2,idUser);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    public boolean updatePassword(String login, String newPassword) {
        try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)) {
            preparedStatement.setString(1,newPassword);
            preparedStatement.setString(2,login);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return false;
    }

    public User getUserByLogin(String login) {
        User user = null;
        try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_EMAIL_AND_PASSWORD_BY_LOGIN)) {
            preparedStatement.setString(1,login);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setEmail(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
            }
        }catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return user;
    }

    public ArrayList<Group> getGroupsByUserId(int idUser) {
        ArrayList<Group> groups = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_GROUPS_BY_USER_ID)) {
            preparedStatement.setInt(1,idUser);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Group group = new Group();
                group.setId(resultSet.getInt(1));
                group.setName(resultSet.getString(2));
                groups.add(group);
            }
        } catch (SQLException e){
            log.error(LOG_ERROR,e.getMessage());
        }
        return groups;
    }
}
