package com.goldcard.shiro.handlers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shiro")
public class ShiroHandler {
	@RequestMapping("/login")
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		Subject currentUser = SecurityUtils.getSubject();

		if (!currentUser.isAuthenticated()) {
			// ���û����������װΪ UsernamePasswordToken ����
			UsernamePasswordToken token = new UsernamePasswordToken(username,
					password);
			// rememberme
			token.setRememberMe(true);
			try {
				System.out.println("1. " + token.hashCode());
				// ִ�е�¼.
				currentUser.login(token);
			}
			// ... catch more exceptions here (maybe custom ones specific to
			// your application?
			// ������֤ʱ�쳣�ĸ���.
			catch (AuthenticationException ae) {
				// unexpected condition? error?
				System.out.println("��¼ʧ��: " + ae.getMessage());
			}
		}

		return "redirect:/list.jsp";
	}
}
