package com.gd.blog.client;


import com.gd.blog.dto.BlogDTO;
import com.gd.blog.dto.BlogGeneralDTO;
import com.gd.core.result.RestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 阿杆
 */
@Import(BlogClientResolver.class)
@FeignClient(value = "blog-server", fallback = BlogClientResolver.class)
public interface BlogClient {

	/**
	 * 获取博客基本信息
	 *
	 * @param id 博客id
	 * @return 博客信息
	 */
	@GetMapping("/blog/blog")
	RestResult<BlogDTO> getBlogInfo(@RequestParam Integer id);

	/**
	 * 获取用户所发表的博客各项数据
	 *
	 * @param userIds 用户id
	 * @return 用户发表博客数据统计
	 */
	@GetMapping("/blog/general")
	RestResult<List<BlogGeneralDTO>> getBlogUserGeneral(@RequestParam Integer[] userIds);

}
