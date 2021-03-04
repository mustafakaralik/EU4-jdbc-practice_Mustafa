package jdbctests;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class jdbc_example {

    String dbUrl = "jdbc:oracle:thin:@54.89.247.16:1521:xe";
    String dbUsername ="hr";
    String dbPassword ="hr";


    @Test
    public void test1() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); //--Those two needed for goıng up and down and dont update database
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }
        //====================================================================
        resultSet = statement.executeQuery("select * from regions");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }
        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
        @Test
        public void countNavigate() throws SQLException{
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); //--Those two needed for goıng up and down and dont update database
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //how to find how many rows we have for the query
        //go to llast row firstly
        resultSet.last();
        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);

        //we need to  move beforeFirst row to get full list since wea are at the last row right now
        resultSet.beforeFirst();
           while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }



        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void metaDataExample () throws SQLException{
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); //--Those two needed for goıng up and down and dont update database
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from regions");

    //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata= connection.getMetaData();

        System.out.println("User =" + dbMetadata.getUserName());
        System.out.println("Database Product Name = "+  dbMetadata.getDatabaseProductName());
        System.out.println("Database Product Version = "+  dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver Name =" + dbMetadata.getDriverName());
        System.out.println("Driver version = "+ dbMetadata.getDriverVersion());

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();






//        //how many columns we have?
//        int colCount = rsMetadata.getColumnCount();
//        System.out.println(colCount);
//
//        //column names
//        System.out.println(rsMetadata.getColumnName(1));
//        System.out.println(rsMetadata.getColumnName(2));
//
//        //print all the column names dynamically
//        for (int i = 1; i < colCount; i++) {
//            System.out.println(rsMetadata.getColumnName(i));
//        }



        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
