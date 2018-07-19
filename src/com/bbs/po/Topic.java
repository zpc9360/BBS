package com.bbs.po;

import java.io.Serializable;

public class Topic implements Serializable {

	private static final long serialVersionUID = 3879156931820052873L;

	private long id;
	private long topicId;
	private String topicName;
	private String content;
	private long userId;
	private long sectionId;
	private long totalResponse;
	private long totalView;
	private long totalFavour;
	private String createDate;
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public long getTotalResponse() {
		return totalResponse;
	}
	public void setTotalResponse(long totalResponse) {
		this.totalResponse = totalResponse;
	}
	public long getTotalView() {
		return totalView;
	}
	public void setTotalView(long totalView) {
		this.totalView = totalView;
	}
	public long getTotalFavour() {
		return totalFavour;
	}
	public void setTotalFavour(long totalFavour) {
		this.totalFavour = totalFavour;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTopicId() {
		return topicId;
	}
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getSectionId() {
		return sectionId;
	}
	public void setSectionId(long sectionId) {
		this.sectionId = sectionId;
	}

	
	
}
