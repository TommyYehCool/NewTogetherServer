package com.exfantasy.server.service.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.exfantasy.server.cnst.ResultCode;
import com.exfantasy.server.exception.OperationException;
import com.exfantasy.server.service.user.UserService;

@Service
public class MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * <pre>
	 * 發送 Email
	 * </pre>
	 * 
	 * @param mailTo 收件者信箱
	 * @param subject 主旨
	 * @param mailContent 內容
	 */
	public void send(String mailTo, String subject, String mailContent) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			mimeMessage.setSubject(subject);
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			helper.setTo(mailTo);
			helper.setText(mailContent, true);
			
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("MessagingException while trying to send mail", e);
			throw new OperationException(ResultCode.SEND_MAIL_FAILED);
		}
	}
	
}
