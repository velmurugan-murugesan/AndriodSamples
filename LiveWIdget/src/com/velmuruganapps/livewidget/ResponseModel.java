package com.velmuruganapps.livewidget;

import java.net.URL;

import android.net.Uri;

public class ResponseModel {

	private String status;
	private String challengeType;
	private String startTimestamp;
	private String description;
	private String endDate;
	private String title;
	private String url;
	private String coverImage;
	private Boolean isHackerearth;
	private String end_tz;
	private String end_utc_tz;
	private URL subscribe;
	private Boolean college;
	private String endTime;
	private String time;
	private String date;
	private String start_utc_tz;
	private String start_tz;
	private String end_timestamp;
	

	public String getChallengeType() {
		return challengeType;
	}

	public void setChallengeType(String challengeType) {
		this.challengeType = challengeType;
	}

	public String getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(String startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getIsHackerearth() {
		return isHackerearth;
	}

	public void setIsHackerearth(Boolean isHackerearth) {
		this.isHackerearth = isHackerearth;
	}

	public String getEnd_tz() {
		return end_tz;
	}

	public void setEnd_tz(String end_tz) {
		this.end_tz = end_tz;
	}

	public String getEnd_utc_tz() {
		return end_utc_tz;
	}

	public void setEnd_utc_tz(String end_utc_tz) {
		this.end_utc_tz = end_utc_tz;
	}

	public URL getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(URL subscribe) {
		this.subscribe = subscribe;
	}

	public Boolean getCollege() {
		return college;
	}

	public void setCollege(Boolean college) {
		this.college = college;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStart_utc_tz() {
		return start_utc_tz;
	}

	public void setStart_utc_tz(String start_utc_tz) {
		this.start_utc_tz = start_utc_tz;
	}

	public String getStart_tz() {
		return start_tz;
	}

	public void setStart_tz(String start_tz) {
		this.start_tz = start_tz;
	}

	public String getEnd_timestamp() {
		return end_timestamp;
	}

	public void setEnd_timestamp(String end_timestamp) {
		this.end_timestamp = end_timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Title = "+title+" Description = "+description;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	
}
