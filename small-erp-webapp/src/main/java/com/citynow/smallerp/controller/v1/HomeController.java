package com.citynow.smallerp.controller.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Home redirection to swagger api documentation
 */
@Controller
public class HomeController {
	@RequestMapping(value = "/")
	public RedirectView index() {
		return new RedirectView("/swagger-ui.html");
	}
}
