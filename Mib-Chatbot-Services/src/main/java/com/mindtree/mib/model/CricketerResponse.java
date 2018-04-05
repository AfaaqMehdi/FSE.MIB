/**
 * 
 */
package com.mindtree.mib.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author M1038389
 *
 */
public class CricketerResponse {
	
	private String pid;
	
	@JsonInclude(value=Include.NON_NULL)
	private String result;
	
	@JsonInclude(value=Include.NON_NULL)
	private String url;
	
	@JsonInclude(value=Include.NON_NULL)
	private String httpStatus;
	
	@JsonInclude(value=Include.NON_NULL)
	private String exceptionType;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

}
