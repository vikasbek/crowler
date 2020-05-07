package com.crowler.crowler.controller;

public class PageDetail {
	private String page_title;
	private String page_link;
	private String image_count;

	public String getPage_title() {
		return page_title;
	}

	public void setPage_title(String page_title) {
		this.page_title = page_title;
	}

	public String getPage_link() {
		return page_link;
	}

	public void setPage_link(String page_link) {
		this.page_link = page_link;
	}

	public String getImage_count() {
		return image_count;
	}

	public void setImage_count(String image_count) {
		this.image_count = image_count;
	}

	@Override
	public String toString() {
		return "PageDetail [page_title=" + page_title + ", page_link=" + page_link + ", image_count=" + image_count
				+ "]";
	}
	
}
