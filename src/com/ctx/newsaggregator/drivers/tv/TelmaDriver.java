package com.ctx.newsaggregator.drivers.tv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ctx.newsaggregator.Constants;
import com.ctx.newsaggregator.NewsAggregatorUtility;
import com.ctx.newsaggregator.drivers.IDriver;
import com.ctx.newsaggregator.entities.CategoryEntity;
import com.ctx.newsaggregator.entities.NewsEntity;

public class TelmaDriver implements IDriver {

	@Override
	public List<String> parseCategory(String categoryName, String categoryURL) {
		// TODO Auto-generated method stub
		
		List<String>linksByCategoryList = null;
		
		try {
			
			Document doc = Jsoup.connect(categoryURL).timeout(Constants.MAX_DELAY_TIME * 1000).get();
			
			Elements links = doc.getElementsByClass("ostanati_vesti");
			
			if (links != null && links.size() > 0) {
				
				linksByCategoryList = new ArrayList<String>();
				
				for (Element element : links) {
					
					Elements innerElements = element.getElementsByTag("td");
					
					if (innerElements != null && innerElements.size() > 0) {
						
						Element linkElement = (innerElements.size() > 1)?innerElements.get(1):innerElements.get(0);
						
						if (linkElement != null) {
							String secretNewsURL = linkElement.toString();
							
							secretNewsURL = secretNewsURL.substring(secretNewsURL.indexOf("href=\"") + 6);
							secretNewsURL = secretNewsURL.substring(0, secretNewsURL.indexOf("\">"));
							secretNewsURL = secretNewsURL.replace("&amp;", "&");
							
							linksByCategoryList.add(secretNewsURL);
						}
						
					}
					
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return linksByCategoryList;
	}

	@Override
	public NewsEntity parseNewsPerCategory(String newsURL) {
		// TODO Auto-generated method stub
		
		NewsEntity parsetData = null;
		
		try {
			Document doc = Jsoup.connect(newsURL).timeout(Constants.MAX_DELAY_TIME * 1000).get();
			doc.outputSettings().charset(Charset.forName("UTF-8"));
			doc.normalise();
			
			Elements titleElement = doc.select("h1[class=titlemain]");
			Elements newsElements = doc.select("div[class=akt_wrap2]");
			if (newsElements != null) {
				Element divContainer = newsElements.get(0);
				Elements textElements = divContainer.getElementsByTag("p");
				
				try {
					parsetData = new NewsEntity();
					parsetData.setNewsTitle(titleElement.text());
					parsetData.setNewsBody(textElements.text());
					parsetData.setNewsURL(newsURL);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return parsetData;
	}

	@Override
	public List<CategoryEntity> parseHomeForCategoryLinks(String baseURL) {
		// TODO Auto-generated method stub
		
		List<CategoryEntity>categoryList = null;
		
		
		
		return categoryList;
	}

}
