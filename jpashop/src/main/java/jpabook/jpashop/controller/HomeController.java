package jpabook.jpashop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
// 로그를 볼 수 있는 어노테이션
@Slf4j
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		log.info("home controller");
		return "home";
	}
	
}
