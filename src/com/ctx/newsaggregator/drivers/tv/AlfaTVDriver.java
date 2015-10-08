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

public class AlfaTVDriver implements IDriver {

	@Override
	public List<String> parseCategory(String categoryName, String categoryURL) {
		// TODO Auto-generated method stub
		
		List<String>linksByCategoryList = null;
		
		try {
			
			Document doc = Jsoup.connect(categoryURL).timeout(Constants.MAX_DELAY_TIME * 1000).get();
			
			Elements links = doc.select("a[class=more video]");
			
			if (links != null && links.size() > 0) {
				
				linksByCategoryList = new ArrayList<String>();
				
				for (Element element : links) {
					
					String newsLink = element.toString();
					
					newsLink = newsLink.substring(newsLink.indexOf("=\".") + 4);
					newsLink = newsLink.substring(0, newsLink.indexOf("\">"));
					
					linksByCategoryList.add(newsLink);
					
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
			
			Elements divElements = doc.select("div[class=news]");
			Elements titleElement = divElements.select("span[id=naslov_lbl]");
			Elements introElement = divElements.select("span[id=info_lbl]");
			
			String [] bodyNews = divElements.toString().split("\n");
			StringBuffer newsBuffer = new StringBuffer();
			
			boolean stringsAreFound = false;
			
			for (int i = 0; i < bodyNews.length; i++) {
				
				if (bodyNews[i].contains("<!-- AddThis Button END -->")) {

					for (int j = (i + 1); j < bodyNews.length; j++) {
						
						if (bodyNews[j].contains("UpdatePanel2")) {
							stringsAreFound = true;
							break;
						}
						
						newsBuffer.append(bodyNews[j]);
						
					}
					
				}
				
				if (stringsAreFound) {
					break;
				}
				
			}
			
			String newsString = newsBuffer.toString();
			newsString = newsString.replace("</div>", "");
			newsString = introElement.toString() +  " " + newsString;
			
			try {
				parsetData = new NewsEntity();
				parsetData.setNewsTitle(titleElement.text());
				parsetData.setNewsBody(newsString);
				parsetData.setNewsURL(newsURL);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
