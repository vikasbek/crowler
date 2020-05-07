package com.crowler.crowler.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crowler.crowler.vo.RequestResult;
import com.crowler.crowler.vo.TokenResponse;

@RestController
@RequestMapping("api/v1/crowl")
public class CrowlerApiController {

	@Autowired
	private Crowler crowler;

	@GetMapping("/home")
	private String index() {
		return "index";
	}
	@GetMapping("/")
	private TokenResponse crowl(@RequestParam("uri") String uri, @RequestParam("depth") int depth) {
		String token = crowler.getRequestToken();
		crowler.crowlRequest(token, uri, depth);
		TokenResponse tokenResponse = new TokenResponse();
		tokenResponse.setToken(token);
		return tokenResponse;
	}

	@GetMapping("/result/{reqId}")
	private RequestResult crowl(@PathVariable String reqId) {
		RequestResult result = new RequestResult();
		List<PageDetail> pageResult = crowler.getResult(reqId);
		if (pageResult != null && pageResult.size() > 0) {
			result.setDetails(pageResult);
			Integer imageCount = pageResult.stream().map(p -> Integer.valueOf(p.getImage_count()))
					.collect(Collectors.summingInt(Integer::intValue));
			result.setTotal_images(imageCount);
		}
		result.setTotal_links(pageResult.size());
		return result;
	}

	@GetMapping("/status/{reqId}")
	private String crowlStatus(@PathVariable String reqId) {
		String status = crowler.getRequestStatus(reqId);
		return status;

	}

}
