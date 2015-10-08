package com.ctx.newsaggregator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.ctx.newsaggregator.entities.NewsEntity;
import com.mysql.jdbc.PreparedStatement;

public class NewsAggregatorDB {
	
	private static NewsAggregatorDB instance = null;
	private Connection dbConnection = null;
	private Statement queryStatement = null;
	private java.sql.PreparedStatement preparedStatement = null;
	private ResultSet queryResultSet = null;
	
	public List<String>listOfNewsID = null;
	
	private static final Integer QUERY_PASS = 1;
	
	public static NewsAggregatorDB getInstance() {
		
		if (instance == null) {
			instance = new NewsAggregatorDB();
		}
		
		return instance;
	}
	
	private Connection openDBConnection() {
		if (dbConnection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				dbConnection = DriverManager
						.getConnection("jdbc:mysql://localhost/newsbase?useUnicode=true&characterEncoding=UTF-8","root","");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dbConnection;
	}
	
	public Boolean saveNewNewsFromMedium(String mediumName, NewsEntity entity) {
		
		Connection con = openDBConnection();
		
		Boolean isCorrectlyExecuted = false;
		
		String newsHashID = entity.propertiesAsSHA1();
		
		Calendar c = Calendar.getInstance();
	
		try {
			preparedStatement = con.prepareStatement("INSERT INTO news_pool(id, title, body, medium_name, category_name, url, date_created) VALUES(?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, newsHashID);
			preparedStatement.setString(2, entity.getNewsTitle());
			preparedStatement.setString(3, entity.getNewsBody());
			preparedStatement.setString(4, mediumName);
			preparedStatement.setString(5, ((entity.getNewsCategory() != null)?entity.getNewsCategory():"-"));
			preparedStatement.setString(6, entity.getNewsURL());
			
			preparedStatement.setDate(7, new Date(c.getTimeInMillis()));
			
			if (preparedStatement.executeUpdate() == QUERY_PASS) {
				isCorrectlyExecuted = true;
			}
			
			preparedStatement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isCorrectlyExecuted;
		
	}
	
	public Boolean saveNewsIndexToDB(NewsEntity entity) {
		
		Connection con = openDBConnection();
		
		Boolean isCorrectlyExecuted = true;
		
		String newsHashID = entity.propertiesAsSHA1();
		
		String query = "INSERT INTO news_indexes(news_index, date_created) VALUES('"+newsHashID+"', NOW());";
	
		try {
			queryStatement = con.createStatement();
			
			isCorrectlyExecuted = queryStatement.execute(query);
			
			queryStatement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isCorrectlyExecuted;
		
	}
	
	public void saveListOfNewsFromMedium(List<NewsEntity>listOfMediums) {
		
		
		
		
		
		
		
	}

	public List<String>findAllSavedNews() {
		
		listOfNewsID = new LinkedList<String>();
		
		Connection con = openDBConnection();
		
		String query = "SELECT news_index FROM news_indexes ORDER BY date_created DESC";
		
		try {
			queryStatement = con.createStatement();
			
			queryResultSet = queryStatement.executeQuery(query);
			
			while (queryResultSet.next()) {
				
				listOfNewsID.add(queryResultSet.getString("news_index"));
				
			}
			
			queryResultSet.close();
			queryStatement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listOfNewsID;
	}
	
	public Boolean saveNewsAsClassified(NewsTypes newsType, NewsEntity newsEntity) {
		
		
		
		return false;
	}

}
