package com.ctx.newsaggregator.entities;

import org.apache.commons.lang3.StringEscapeUtils;

import com.ctx.newsaggregator.NewsAggregatorUtility;
import com.mysql.jdbc.StringUtils;

public class NewsEntity {
	private String newsTitle;
	private String newsBody;
	private String newsCategory;
	private String newsURL;
	
	private String hashedProperties = "";
	
	public NewsEntity(){}

	public NewsEntity(String newsTitle, String newsBody, String newsCategory, String newsURL) {
		super();
		this.newsTitle = newsTitle;
		this.newsBody = newsBody;
		this.newsCategory = newsCategory;
		this.newsURL = newsURL;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		if (newsTitle.contains("\"")) {
			//newsTitle = StringEscapeUtils.escapeJava(newsTitle);
		} else if (newsTitle.contains("'")) {
			//newsTitle = StringEscapeUtils.escapeEcmaScript(newsTitle);
			//newsTitle = StringUtils.escapeQuote(newsTitle, "'");
		}

		this.newsTitle = newsTitle;
	}

	public String getNewsBody() {
		return newsBody;
	}

	public void setNewsBody(String newsBody) {
		if (newsBody.contains("\"")) {
			//newsBody = StringEscapeUtils.escapeJava(newsBody);
		} else if (newsBody.contains("'")) {
			//newsBody = StringEscapeUtils.escapeEcmaScript(newsBody);
			newsBody = newsBody.replaceAll("'", "");
		} 
		
		this.newsBody = newsBody;
	}

	public String getNewsURL() {
		return newsURL;
	}

	public void setNewsURL(String newsURL) {
		this.newsURL = newsURL;
	}

	public String getNewsCategory() {
		return newsCategory;
	}

	public void setNewsCategory(String newsCategory) {
		this.newsCategory = newsCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((newsBody == null) ? 0 : newsBody.hashCode());
		result = prime * result
				+ ((newsTitle == null) ? 0 : newsTitle.hashCode());
		result = prime * result + ((newsURL == null) ? 0 : newsURL.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsEntity other = (NewsEntity) obj;
		if (newsBody == null) {
			if (other.newsBody != null)
				return false;
		} else if (!newsBody.equals(other.newsBody))
			return false;
		if (newsTitle == null) {
			if (other.newsTitle != null)
				return false;
		} else if (!newsTitle.equals(other.newsTitle))
			return false;
		if (newsURL == null) {
			if (other.newsURL != null)
				return false;
		} else if (!newsURL.equals(other.newsURL))
			return false;
		return true;
	}
	
	public String propertiesAsSHA1() {
		
		if (this.hashedProperties.equals("")) {
			
			try {
				this.hashedProperties = NewsAggregatorUtility.StringToSHA1Hash(this.newsURL);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return hashedProperties;
	}
	
}
