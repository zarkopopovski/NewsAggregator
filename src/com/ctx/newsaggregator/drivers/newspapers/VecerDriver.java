package com.ctx.newsaggregator.drivers.newspapers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

public class VecerDriver implements IDriver {

	@Override
	public List<String> parseCategory(String categoryName, String categoryURL) {
		// TODO Auto-generated method stub
		
		List<String>linksByCategoryList = null;
		
		try {
			
			Document doc = Jsoup.connect(categoryURL)
					.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:24.0) Gecko/20100101 Firefox/24.0")
					.referrer("http://daily.mk/html/emb_vecer.html")
					.timeout(Constants.MAX_DELAY_TIME * 1000).get();
			
			String categoryDoc = doc.html();
			
			Elements links = doc.select("a[class=WB_VECER_ArticleTitle]");
			
			if (links != null && links.size() > 0) {
				
				linksByCategoryList = new ArrayList<String>();
				
				for (Element element : links) {
					
					String newsLink = element.attr("href");
					
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
			Document doc = Jsoup.connect(newsURL)
					.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:24.0) Gecko/20100101 Firefox/24.0")
					.referrer("http://daily.mk/html/emb_vecer.html")
					.timeout(Constants.MAX_DELAY_TIME * 1000).get();
			
			doc.outputSettings().charset(Charset.forName("UTF-8"));
			doc.normalise();
			
			Elements titleElement = doc.select("SPAN[id=ArticleTitle]");
			Elements newsElements = doc.select("td[class=WB_VECER_Normal]").select("P");
			newsElements.select("a, img").remove();
			
			if (newsElements != null) {
				
				try {
					parsetData = new NewsEntity();
					parsetData.setNewsTitle(titleElement.text());
					parsetData.setNewsBody(newsElements.text());
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
		
		try {
			
			Document doc = Jsoup.connect(baseURL)
					.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:24.0) Gecko/20100101 Firefox/24.0")
					.referrer("http://vecer.com.mk/html/emb_vecer.html")
					.timeout(Constants.MAX_DELAY_TIME * 1000).get();
			
			Elements categoryElements = doc.select("a[class=WB_VECER_MainMenu]");
			
			if (categoryElements != null && categoryElements.size() > 0) {
				
				categoryList = new ArrayList<CategoryEntity>();
				
				for (Element element : categoryElements) {
					
					String linkAsString = element.attr("href");
					
					String titleOfLink = element.text();
					
					categoryList.add(new CategoryEntity(titleOfLink, linkAsString));
					
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return categoryList;
		
	}

}
