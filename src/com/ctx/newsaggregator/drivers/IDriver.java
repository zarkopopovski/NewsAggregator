package com.ctx.newsaggregator.drivers;

import java.util.List;

import com.ctx.newsaggregator.entities.CategoryEntity;
import com.ctx.newsaggregator.entities.NewsEntity;

public interface IDriver {
	public List<CategoryEntity> parseHomeForCategoryLinks(String baseURL);
	public List<String> parseCategory(String categoryName, String categoryURL);
	public NewsEntity parseNewsPerCategory(String newsURL);
}
