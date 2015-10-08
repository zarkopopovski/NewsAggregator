package com.ctx.newsaggregator;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewsAggregatorService {

	private static final Integer MAX_MEDIUMS_PER_EXECUTION = 5;
	private static final String OPERATION_MODE = "operation_mode";
	private static final String MODE_SEQUENTIAL = "sequential"; 
	private static final String MODE_PARALLEL = "parallel"; 
	private static final String MEDIUMS_DATA = "mediums_data"; 

	public static void main(String[] args) {

		NewsAggregatorDB.getInstance().findAllSavedNews();

		String configData = null;

		List<List<JSONObject>>listOfListsWithJSONObjects = null;

		try {
			configData = FileUtils.readFileToString(new File("config_temp.json"));

			if (configData != null && !configData.equals("")) {

				JSONObject serviceJSONConfigDataASJSON = new JSONObject(configData);
				
				JSONArray configDataAsJSON = serviceJSONConfigDataASJSON.getJSONArray(MEDIUMS_DATA);
				
				System.out.println("MEDIUMS FOR EXECUTION: " + configDataAsJSON.length());
				
				if (serviceJSONConfigDataASJSON.getString(OPERATION_MODE).equals(MODE_PARALLEL)) {
					
					if (configDataAsJSON.length() > MAX_MEDIUMS_PER_EXECUTION) {
						
						listOfListsWithJSONObjects = new LinkedList<List<JSONObject>>();

						int numberOfJsonObjects = configDataAsJSON.length();

						int maxNumberOfObjectsPerList = Math.round((numberOfJsonObjects / MAX_MEDIUMS_PER_EXECUTION));

						int counter = 0;

						List<JSONObject>jsonObjectsList = null;

						for (int i = 0; i <  numberOfJsonObjects; i++) {

							if (jsonObjectsList == null) {
								jsonObjectsList = new LinkedList<JSONObject>();
							}

							JSONObject mediumObject = configDataAsJSON.getJSONObject(i);
							jsonObjectsList.add(mediumObject);
							counter++;

							if (i == (numberOfJsonObjects - 1)) {
								listOfListsWithJSONObjects.add(jsonObjectsList);
								jsonObjectsList = null;
								counter = 0;
							} else {

								if (counter == maxNumberOfObjectsPerList) {

									listOfListsWithJSONObjects.add(jsonObjectsList);
									jsonObjectsList = new LinkedList<JSONObject>();
									counter = 0;

								}

							}

						}

						for (List<JSONObject>listOfJSONObjects : listOfListsWithJSONObjects) {
							new NewsAggregatorThreadedRunner(listOfJSONObjects).run();
						}
						
					} else {
						System.out.println("For parallel execution min mediums are: " + MAX_MEDIUMS_PER_EXECUTION);
					}

				} else if (serviceJSONConfigDataASJSON.getString(OPERATION_MODE).equals(MODE_SEQUENTIAL)) {

					for (int i = 0; i < configDataAsJSON.length(); i++) {

						JSONObject mediumObject = configDataAsJSON.getJSONObject(i);

						NewsAggregatorEngine.getInstance().parseMediumJSONObject(mediumObject);

					}

				}


			} else {
				System.out.println("Error reading config json");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
