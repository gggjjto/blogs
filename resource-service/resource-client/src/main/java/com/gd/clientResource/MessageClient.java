package com.gd.clientResource;


import com.gd.clientResource.fuse.MessageClientFuse;
import com.gd.clientResource.pojo.MailDTO;
import com.gd.core.result.RestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 消息服务模块接口客户端
 *
 * @author 阿杆
 */
@Import(MessageClientFuse.class)
@FeignClient(value = "resource-server", fallback = MessageClientFuse.class, contextId = "MessageClient")
public interface MessageClient {

	/**
	 * 发送邮件
	 *
	 * @param mail 邮件信息
	 * @return 发送情况
	 */
	@PostMapping(value = "/send/mail", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	RestResult<Object> sendMail(MailDTO mail);

}
