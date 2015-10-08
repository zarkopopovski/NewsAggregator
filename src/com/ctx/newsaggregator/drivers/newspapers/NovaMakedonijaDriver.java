package com.ctx.newsaggregator.drivers.newspapers;

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

public class NovaMakedonijaDriver implements IDriver {

	@Override
	public List<String> parseCategory(String categoryName, String categoryURL) {
		// TODO Auto-generated method stub
		
		List<String>linksByCategoryList = null;
		
		try {
			
			//Document doc = Jsoup.connect(categoryURL).get();
			String webAsString = NewsAggregatorUtility.fetchWebAsString(categoryURL);
			Document doc = Jsoup.parse(webAsString);
			
			Elements firstTopLink = doc.getElementsByClass("headerNewsTdOneSecond");
			Elements newsLinks = doc.getElementsByClass("headerNewsTdSecond");
			
			if (firstTopLink != null && firstTopLink.size() > 0) {
				
				linksByCategoryList = new ArrayList<String>();
				
				String topLinkToString = firstTopLink.toString();
				topLinkToString = topLinkToString.substring(topLinkToString.indexOf("=\"NewsDetal") + 2);
				topLinkToString = topLinkToString.substring(0, topLinkToString.indexOf("\">"));
				topLinkToString = topLinkToString.replace("&amp;", "&");
				
				linksByCategoryList.add(topLinkToString);
				
			}
			
			if (newsLinks != null && newsLinks.size() > 0) {
				
				if (linksByCategoryList == null) {
					linksByCategoryList = new ArrayList<String>();
				}
				
				for (Element element : newsLinks) {
					
					String linkToString = element.toString();
					linkToString = linkToString.substring(linkToString.indexOf("=\"NewsDetal") + 2);
					linkToString = linkToString.substring(0, linkToString.indexOf("\">"));
					linkToString = linkToString.replace("&amp;", "&");
					
					linksByCategoryList.add(linkToString);
					
				}
				
			}
			
		} catch (Exception e) {
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
			
			Elements titleElement = doc.select("h1");
			Elements newsBody = doc.select("p");
			
			parsetData = new NewsEntity();
			parsetData.setNewsTitle(titleElement.text());
			parsetData.setNewsBody(newsBody.text());
			parsetData.setNewsURL(newsURL);
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
