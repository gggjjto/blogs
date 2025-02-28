package com.gd.server.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author 阿杆
 */
@Data
public class Mail {

	/**
	 * 邮件id
	 */
	private Integer id;

	/**
	 * 邮件发送人
	 */
	private String from;

	/**
	 * 邮件接收人
	 */
	private String to;

	/**
	 * 邮件主题
	 */
	private String subject;

	/**
	 * 邮件内容
	 */
	private String text;

	/**
	 * 发送时间
	 */
	private Date sentDate;

	/**
	 * 抄送
	 */
	private String cc;

	/**
	 * 密送
	 */
	private String bcc;

	/**
	 * 邮件附件
	 */
	@JsonIgnore
	private MultipartFile[] multipartFiles;

}
