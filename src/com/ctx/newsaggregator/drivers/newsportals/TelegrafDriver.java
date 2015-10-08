package com.ctx.newsaggregator.drivers.newsportals;

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

public class TelegrafDriver implements IDriver {

	@Override
	public List<String> parseCategory(String categoryName, String categoryURL) {
		// TODO Auto-generated method stub
		
		List<String>linksByCategoryList = null;
		
		try {
			
			Document doc = Jsoup.connect(categoryURL).timeout(Constants.MAX_DELAY_TIME * 1000).get();
			
			Elements links = doc.select("a[class=more fl]");
			
			Elements oddLILinks = doc.select("li[class=odd cf]");
			Elements finalOddLinks = oddLILinks.select("a");
			
			Elements evenLILinks = doc.select("li[class=even cf]");
			Elements finalEventLinks = evenLILinks.select("a");
			
			if (links != null && links.size() > 0) {
				
				linksByCategoryList = new ArrayList<String>();
				
				for (Element element : links) {
					
					String newsLink = element.toString();
					
					newsLink = newsLink.substring(newsLink.indexOf("=\"") + 2);
					newsLink = newsLink.substring(0, newsLink.indexOf("\""));
					
					linksByCategoryList.add(newsLink);
					
				}
				
			}
			
			if (finalOddLinks != null && finalOddLinks.size() > 0) {
				
				for (Element element : finalOddLinks) {
					
					String newsLink = element.toString();
					
					newsLink = newsLink.substring(newsLink.indexOf("=\"") + 2);
					newsLink = newsLink.substring(0, newsLink.indexOf("\""));
					
					linksByCategoryList.add(newsLink);
					
				}
				
			}
			
			if (finalEventLinks != null && finalEventLinks.size() > 0) {
				
				for (Element element : finalEventLinks) {
					
					String newsLink = element.toString();
					
					newsLink = newsLink.substring(newsLink.indexOf("=\"") + 2);
					newsLink = newsLink.substring(0, newsLink.indexOf("\""));
					
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
			
			Elements allNewsElements = doc.select("div[id=content]");
			Elements titleElement = allNewsElements.select("h1");
			Elements subTitleElement = allNewsElements.select("h2");
			Elements newsElements = allNewsElements.select("p");
			
			if (newsElements != null) {
				
				try {
					parsetData = new NewsEntity();
					parsetData.setNewsTitle(titleElement.text());
					parsetData.setNewsBody(subTitleElement.text() + " " + newsElements.text());
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
