package com.ctx.newsaggregator;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class NewsAggregatorThreadedRunner extends Thread {

	List<JSONObject>listOfMediums = null;
	
	public NewsAggregatorThreadedRunner(List<JSONObject>listOfMediums) {
		this.listOfMediums = listOfMediums;
	}
	
	@Override
	public void run() {
		
		for (int i = 0; i < this.listOfMediums.size(); i++) {
			
			JSONObject mediumObject = this.listOfMediums.get(i);
			
			try {
				NewsAggregatorEngine.getInstance().parseMediumJSONObject(mediumObject);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		super.run();
	}
	
}
