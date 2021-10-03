package com.amol;

import java.sql.*;

public class Main {

  public static void main(String[] args) {
    String url = "";
    String user = "";
    String password = "";
    Connection connection = null;
    Statement statement = null;
    try {
      connection = DriverManager.getConnection(url, user, password);

      statement = connection.createStatement();
      String sql = "select * from cameras";
      ResultSet resultSet = statement.executeQuery(sql);
      ResultSetMetaData rsMetaData = resultSet.getMetaData();
      int columnCount = rsMetaData.getColumnCount();

      while (resultSet.next()) {
        StringBuilder row = new StringBuilder();
        StringBuilder header = new StringBuilder();
        for (int i = 1; i <= columnCount; i++) {
          row.append(resultSet.getString(rsMetaData.getColumnName(i)) + ",");
          //System.out.println((rsMetaData.getColumnName(i) + " - " + resultSet.getString(rsMetaData.getColumnName(i))));

        }
        System.out.println(row);
      }

    } catch (SQLException exception) {
      exception.printStackTrace();
    } finally {
      if (statement != null) {
        try {
          statement.close();
        } catch (SQLException exception) {
          exception.printStackTrace();
        }
      }

      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException exception) {
          exception.printStackTrace();
        }
      }
    }
  }
}

