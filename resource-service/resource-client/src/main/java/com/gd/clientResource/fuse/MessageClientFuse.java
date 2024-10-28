package com.gd.clientResource.fuse;


import com.gd.clientResource.MessageClient;
import com.gd.clientResource.pojo.MailDTO;
import com.gd.core.result.RestResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息服务模块接口客户端熔断器
 *
 */
@Slf4j
public class MessageClientFuse implements MessageClient {

	/**
	 * 发送邮件
	 *
	 * @param mail 邮件信息
	 * @return 发送情况
	 */
	@Override
	public RestResult<Object> sendMail(MailDTO mail) {
		log.error("Message 服务异常：sendMail(MailDTO mail) 请求失败");
		return RestResult.fail("request fail");
	}

}
