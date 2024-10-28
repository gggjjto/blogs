package com.gd.content.controller;


import com.gd.content.pojo.BlogListVO;
import com.gd.content.pojo.SearchQuery;
import com.gd.content.service.BlogContentService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/blog/content")
public class BlogContentController {

	@Resource
	private BlogContentService blogContentService;

	/**
	 * 搜索博客
	 *
	 * @return 博客列表
	 */
	@GetMapping("/search")
	public BlogListVO searchBlog(@Validated SearchQuery searchQuery) {
		return blogContentService.searchBlog(searchQuery);
	}

}
