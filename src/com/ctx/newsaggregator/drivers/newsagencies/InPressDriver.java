package com.ctx.newsaggregator.drivers.newsagencies;

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

public class InPressDriver implements IDriver {

	@Override
	public List<String> parseCategory(String categoryName, String categoryURL) {
		// TODO Auto-generated method stub
		
		List<String>linksByCategoryList = null;
		
		try {
			
			Document doc = Jsoup.connect(categoryURL).timeout(Constants.MAX_DELAY_TIME * 1000).get();
			
			Elements links = doc.select("a[class=WB_INPRESS_MoreLink]");
			
			if (links != null && links.size() > 0) {
				
				linksByCategoryList = new ArrayList<String>();
				
				for (Element element : links) {
					
					String newsLink = element.toString();
					
					newsLink = newsLink.substring(newsLink.indexOf("\"?") + 1);
					newsLink = newsLink.substring(0, newsLink.indexOf("\">"));
					
					if (!linksByCategoryList.contains(newsLink)) {
						linksByCategoryList.add(newsLink);
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
			
			Elements wholeNewsElements = doc.select("td[class=WB_INPRESS_Normal]");
			Elements titleElement = wholeNewsElements.select("P[class=WB_INPRESS_Naslov]");
			
			if (titleElement.size() == 0) {
				titleElement = wholeNewsElements.select("span[class=WB_INPRESS_Naslov]");
			}
			
			Elements subTitleElement = wholeNewsElements.select("SPAN[class=WB_INPRESS_Podnaslov]");
			Elements newsElements = wholeNewsElements.select("p");
			
			if (newsElements != null) {
				
				StringBuffer bufferedBody = new StringBuffer();
				
				for (Element element : newsElements) {
					if (element.toString().contains("WB_INPRESS_Naslov") || element.toString().contains("WB_INPRESS_Podnaslov")) {
						continue;
					} else {
						bufferedBody.append(element.text());
					}
				}
				
				try {
					parsetData = new NewsEntity();
					parsetData.setNewsTitle(titleElement.text());
					parsetData.setNewsBody(subTitleElement.text() + " " + bufferedBody.toString());
					parsetData.setNewsURL(newsURL);
					
					//System.out.println("URL: " + newsURL + " HASH: " + NewsAggregatorUtility.StringToSHA1Hash(newsURL));
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
