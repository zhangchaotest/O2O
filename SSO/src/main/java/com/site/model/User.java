package com.site.model;

public class User {
	private String userId;
	private String dingUserId;
	private String nickName;
	private int role;
	private String companyId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDingUserId() {
		return dingUserId;
	}
	public void setDingUserId(String dingUserId) {
		this.dingUserId = dingUserId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", dingUserId=" + dingUserId + ", nickName=" + nickName + ", role=" + role
				+ ", companyId=" + companyId + "]";
	}
	
	
	
}
