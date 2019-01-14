package se.anderssonbilly.labb_1_tmdb.models;

import com.fasterxml.jackson.annotation.JsonSetter;

public class TmdbSession {
	private boolean success;
	private String sessionId;
	private String expires_at;

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getSessionId() {
		return sessionId;
	}
	@JsonSetter("guest_session_id")
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getExpires_at() {
		return expires_at;
	}
	public void setExpires_at(String expires_at) {
		this.expires_at = expires_at;
	}
}
