package com.ctx.newsaggregator;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Document;

import com.ctx.newsaggregator.drivers.DriverFactory;
import com.ctx.newsaggregator.drivers.IDriver;
import com.ctx.newsaggregator.entities.CategoryEntity;
import com.ctx.newsaggregator.entities.NewsEntity;

public class NewsAggregatorEngine {
	
	private static NewsAggregatorEngine instance;
	public static NewsAggregatorEngine getInstance() {
		if (instance == null) {
			instance = new NewsAggregatorEngine();
		}
		
		return instance;
	}
	
	public void parseMediumJSONObject(JSONObject mediumObject) throws JSONException {
		String mediumName = mediumObject.getString(Constants.CONFIG_KEY_MEDIUM_NAME);
		String mediumBaseURL = mediumObject.getString(Constants.CONFIG_KEY_BASE_URL);
		List<String>newsPerCategoryList = null;
		
		System.out.println("Medium Name: " + mediumName);
		
		IDriver scraperDriver = DriverFactory.findDriverByName(mediumName);
		
		if (scraperDriver != null) {
			
			JSONArray mediumCategories = mediumObject.getJSONArray(Constants.CONFIG_KEY_CATEGORIES);
			
			if (mediumCategories.length() == 0) {
				
				List<CategoryEntity>categoriesList = scraperDriver.parseHomeForCategoryLinks(mediumBaseURL);
				
				if (categoriesList != null) {
				
					for (CategoryEntity categoryEntity : categoriesList) {
						
						newsPerCategoryList = scraperDriver.parseCategory(
								categoryEntity.getCategoryName(), (mediumBaseURL + categoryEntity.getCategoryURL()));
					
						if (newsPerCategoryList != null && newsPerCategoryList.size() > 0) {
							NewsEntity parsedPageEntity = null;
							for (String newsURLForParsing : newsPerCategoryList) {
								String urlForParsing = "";
								if (!newsURLForParsing.startsWith("http")) {
									urlForParsing = (mediumBaseURL + newsURLForParsing);
								} else {
									urlForParsing = newsURLForParsing;
								}
								
								try {
									if (!(NewsAggregatorDB.getInstance().listOfNewsID.contains(NewsAggregatorUtility.StringToSHA1Hash(urlForParsing)))) {
									
										parsedPageEntity = scraperDriver.parseNewsPerCategory(urlForParsing);
	
										if (parsedPageEntity != null) {
											System.out.println(parsedPageEntity.propertiesAsSHA1() + " " + parsedPageEntity.getNewsTitle());
	
											Boolean isExecuted = NewsAggregatorDB.getInstance().saveNewNewsFromMedium(mediumName, parsedPageEntity);
	
											if (isExecuted) {
												NewsAggregatorDB.getInstance().saveNewsIndexToDB(parsedPageEntity);
												NewsAggregatorDB.getInstance().listOfNewsID.add(parsedPageEntity.propertiesAsSHA1());
											}
	
										}
										
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							
						}
						
					}
					
				} else {
					System.out.println("Error with parsing categories from medium: " + mediumName);
				}
				
			} else {
				
				for (int i = 0; i < mediumCategories.length(); i++) {
					
					JSONObject newsCategoryObject = mediumCategories.getJSONObject(i);
					
					newsPerCategoryList = scraperDriver.parseCategory(
							newsCategoryObject.getString(Constants.CONFIG_KEY_CATEGORY_NAME), 
							newsCategoryObject.getString(Constants.CONFIG_KEY_CATEGORY_URL));
					
					if (newsPerCategoryList != null && newsPerCategoryList.size() > 0) {
						NewsEntity parsedPageEntity = null;
						for (String newsURLForParsing : newsPerCategoryList) {
							String urlForParsing = "";
							if (!newsURLForParsing.startsWith("http")) {
								urlForParsing = (mediumBaseURL + newsURLForParsing);
							} else {
								urlForParsing = newsURLForParsing;
							}
							
							try {
								if (!(NewsAggregatorDB.getInstance().listOfNewsID.contains(NewsAggregatorUtility.StringToSHA1Hash(urlForParsing)))) {

									parsedPageEntity = scraperDriver.parseNewsPerCategory(urlForParsing);

									if (parsedPageEntity != null) {
										
										parsedPageEntity.setNewsCategory(newsCategoryObject.getString(Constants.CONFIG_KEY_CATEGORY_NAME));
										
										System.out.println(parsedPageEntity.propertiesAsSHA1() + " " + parsedPageEntity.getNewsTitle());

										Boolean isExecuted = NewsAggregatorDB.getInstance().saveNewNewsFromMedium(mediumName, parsedPageEntity);

										if (isExecuted) {
											NewsAggregatorDB.getInstance().saveNewsIndexToDB(parsedPageEntity);
											NewsAggregatorDB.getInstance().listOfNewsID.add(parsedPageEntity.propertiesAsSHA1());
										}

									}
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

					}
					
				}
				
			}
		} else {
			System.err.println("No suitable driver for this medium");
		}
		
				

		
		
	}
	
	
	
}
