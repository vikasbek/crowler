package com.crowler.crowler.vo;

import java.util.List;

import com.crowler.crowler.controller.PageDetail;

public class RequestResult {
	private Integer total_links;
	private Integer total_images;
	private List<PageDetail> details;

	public Integer getTotal_links() {
		return total_links;
	}

	public void setTotal_links(Integer total_links) {
		this.total_links = total_links;
	}

	public Integer getTotal_images() {
		return total_images;
	}

	public void setTotal_images(Integer total_images) {
		this.total_images = total_images;
	}

	public List<PageDetail> getDetails() {
		return details;
	}

	public void setDetails(List<PageDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "RequestResult [total_links=" + total_links + ", total_images=" + total_images + ", details=" + details
				+ "]";
	}

}
