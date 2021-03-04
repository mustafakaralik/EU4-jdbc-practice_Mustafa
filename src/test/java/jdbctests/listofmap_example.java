package jdbctests;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listofmap_example {
    String dbUrl = "jdbc:oracle:thin:@54.89.247.16:1521:xe";
    String dbUsername ="hr";
    String dbPassword ="hr";

    @Test
    public void countNavigate() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); //--Those two needed for goıng up and down and dont update database
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary,job_id from employees where rownum<6");




        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String,Object> row1 =new HashMap<>(); //key =column name always String but data in row may be any thing int vs.

        //move to first row
        resultSet.next();
        row1.put(rsMetadata.getColumnName(1),resultSet.getString(1));
        row1.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row1.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row1.put(rsMetadata.getColumnName(4),resultSet.getString(4));


        System.out.println(row1.toString());

        Map<String,Object> row2 =new HashMap<>();

        //move to second row
        resultSet.next();
        row2.put(rsMetadata.getColumnName(1),resultSet.getString(1));
        row2.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row2.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row2.put(rsMetadata.getColumnName(4),resultSet.getString(4));

        System.out.println(row2.toString());

        System.out.println(row2.get("SALARY"));

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);

        //get the steven last name directly from the list
        System.out.println(queryData.get(0).get("LAST_NAME")); //0 ilk row u temsil ediyor

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }

}
