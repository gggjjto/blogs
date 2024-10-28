package com.gd.content.service;


import com.gd.content.pojo.BlogListVO;
import com.gd.content.pojo.SearchQuery;

public interface BlogContentService {

	/**
	 * 搜索博客
	 *
	 * @param searchQuery 搜索条件
	 * @return 搜索到的结果
	 */
	BlogListVO searchBlog(SearchQuery searchQuery);

}
