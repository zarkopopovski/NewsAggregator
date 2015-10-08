package com.ctx.newsaggregator.entities;

public class CategoryEntity {
	private String categoryName;
	private String categoryURL;
	
	public CategoryEntity(){}

	public CategoryEntity(String categoryName, String categoryURL) {
		super();
		this.categoryName = categoryName;
		this.categoryURL = categoryURL;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryURL() {
		return categoryURL;
	}

	public void setCategoryURL(String categoryURL) {
		this.categoryURL = categoryURL;
	}
	
}
