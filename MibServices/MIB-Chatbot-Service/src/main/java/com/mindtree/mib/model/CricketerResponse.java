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
	
	private Integer pid;
	
	@JsonInclude(value=Include.NON_NULL)
	private String result;
	
	@JsonInclude(value=Include.NON_NULL)
	private String url;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
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

}
