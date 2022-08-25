package com.webapp.database;

import com.webapp.entity.User;
import java.util.ArrayList;

import static com.webapp.database.test.TestConstants.GET_LIMIT_TESTS;
import static com.webapp.entity.Role.*;

public class MainQueryBuilder {

    public static String queryBuild(ArrayList<String> results, User user,int offset, int noOfRecords,String filter){
        String orderBy = "";
        if (results != null){
            orderBy = " order by ";
            for (int i =0; i!= results.size();i++)
                if (results.get(i) != null)
                    orderBy += results.get(i) + ", ";
            System.out.println(orderBy);
            if (orderBy.equals(" order by "))
                orderBy = "";
            else
                orderBy = orderBy.substring(0,orderBy.length() - 2);
        }
        int id = user.getId();
        String where = "";
        System.out.println(user.getRole());
        System.out.println(user.getRole() == ADMIN);
        if (user.getRole() == STUDENT)
            where = "where test_group.group_id  IN(select group_user.group_id\n" +
                    "from group_user\n" +
                    "join `user` on `user`.user_id = group_user.user_id\n" +
                    "where `user`.user_id = " + id + ") ";
        if (filter != null)
            where += "  and  test.sub_id = " + filter;
        System.out.println(GET_LIMIT_TESTS + where + " group by test.test_id " + orderBy + " limit " + offset + ", " + noOfRecords);
        return GET_LIMIT_TESTS + where + " group by test.test_id " + orderBy + " limit " + offset + ", " + noOfRecords;
    }
}
