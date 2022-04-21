package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.dto.CatalogWS;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/catalog")
public class CatalogController {
	private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);
	private final CatalogService catalogService;

	@Autowired
	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@GetMapping("/user")
	public String user(@AuthenticationPrincipal OAuth2User principal) {
		//return Collections.singletonMap("name", principal.getAttribute("name"));
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		logger.info("Name", authentication.getName());
		return "Hello user!";
	}

	@GetMapping("/{genre}")
	public CatalogWS getCatalogByGenre(@PathVariable String genre) {
		logger.info("Request to get movies and series by genre {}", genre);
		return catalogService.getCatalogByGenre(genre);
	}

}
