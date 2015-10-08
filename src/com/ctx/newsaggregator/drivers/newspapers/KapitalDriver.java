package com.ctx.newsaggregator.drivers.newspapers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ctx.newsaggregator.Constants;
import com.ctx.newsaggregator.NewsAggregatorUtility;
import com.ctx.newsaggregator.drivers.IDriver;
import com.ctx.newsaggregator.entities.CategoryEntity;
import com.ctx.newsaggregator.entities.NewsEntity;

public class KapitalDriver implements IDriver {

	@Override
	public List<String> parseCategory(String categoryName, String categoryURL) {
		// TODO Auto-generated method stub
		
		List<String>linksByCategoryList = null;
		
		try {
			
			Document doc = Jsoup.connect(categoryURL).timeout(Constants.MAX_DELAY_TIME * 1000).get();
			
			Elements links = doc.select("a[class=comment]");
			
			if (links != null && links.size() > 0) {
				
				linksByCategoryList = new ArrayList<String>();
				
				for (Element element : links) {
					
					String newsLink = element.toString();
					
					newsLink = newsLink.substring(newsLink.indexOf("href=\"") + 6);
					newsLink = newsLink.substring(0, newsLink.indexOf("aspx\"") + 4);
					
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
			
			Elements titleElement = doc.select("title");
			String titleName = titleElement.text();
			
			if (titleName.contains("-")) {
				titleName = titleName.substring((titleName.indexOf("-") + 1)).trim();
			}
		
			Elements newsElements = doc.select("p");
			
			StringBuffer body = new StringBuffer();
			Vector<String> bodyVector = new Vector<String>(5);
			
			if (newsElements != null && newsElements.size() > 0) {
				for (Element element : newsElements) {
					if (element.toString().contains("<label>")) {
						bodyVector.remove(bodyVector.size() - 1);
						break;
					}
					bodyVector.add(element.toString());
				}
			}
			
			parsetData = new NewsEntity();
			parsetData.setNewsTitle(titleName);
			parsetData.setNewsBody(bodyVector.toString());
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
