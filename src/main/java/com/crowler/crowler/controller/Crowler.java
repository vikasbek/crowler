package com.crowler.crowler.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class Crowler {
	private static HashMap<String, String> requestStatusMap = new HashMap<>();
	private static HashMap<String, List<PageDetail>> requestMap = new HashMap<>();
	private static HashMap<String, PageDetail> linkDetails = new HashMap<>();
	private static HashMap<String, List<String>> innerLinkListMap = new HashMap<String, List<String>>();
	private static ExecutorService executorService = Executors.newFixedThreadPool(30);
	@Async
	public void crowlRequest(String token, String uri, int depth) {
		requestStatusMap.put(token, "In-Process");
		try {
			List<PageDetail> pageDetailList = getPageLinks(uri, depth, new ArrayList<>(0));
			requestStatusMap.put(token, "Processed");
			requestMap.put(token, pageDetailList);
		} catch (Exception ex) {
			requestStatusMap.put(token, "Failed");
		}
	}

	public List<PageDetail> getPageLinks(String uri, int depth, List<PageDetail> resultPageDetailList) {
		if (depth <= 0) {
			return resultPageDetailList;
		}
		if (!linkDetails.containsKey(uri)) {
			try {

				Document document = Jsoup.connect(uri).get();
				String title = document.title();
				Elements linksOnPage = document.select("a[href]");
				Elements imageOnPage = document.select("img[src]");
				PageDetail newPage = new PageDetail();
				newPage.setImage_count(String.valueOf(imageOnPage.size()));
				newPage.setPage_title(title);
				newPage.setPage_link(uri);
				linkDetails.put(uri, newPage);
				resultPageDetailList.add(newPage);
				List<String> innerLinkList = new ArrayList<String>();
				for (Element page : linksOnPage) {
					String innerUrl = page.attr("abs:href");
					innerLinkList.add(innerUrl);
					
					getPageLinks(innerUrl, depth-1, resultPageDetailList);
					
					
				}
				innerLinkListMap.put(uri, innerLinkList);
			} catch (IOException e) {
				System.err.println("For '" + uri + "': " + e.getMessage());
				throw new RuntimeException("Exception occured while processing");
			}
		} else {
			resultPageDetailList.add(linkDetails.get(uri));
			while (innerLinkListMap.containsKey(uri)) {
				for (String link : innerLinkListMap.get(uri)) {
					getPageLinks(link, depth-1, resultPageDetailList);
				}
			}
		}
		return resultPageDetailList;
	}

	public String getRequestToken() {
		String token = UUID.randomUUID().toString();
		requestStatusMap.put(token, "Submitted");
		return token;
	}

	public String getRequestStatus(String reqId) {
		if(requestStatusMap.containsKey(reqId))
			return requestStatusMap.get(reqId);
		return "Invalid token";
	}

	public List<PageDetail> getResult(String reqId) {
		return requestMap.get(reqId);
	}

}
