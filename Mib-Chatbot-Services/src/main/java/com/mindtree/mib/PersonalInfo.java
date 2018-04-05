/**
 * 
 */
package com.mindtree.mib;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author M1017168
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "pid", "wifename", "girlfriend", "father", "mother",
		"kids", "school", "dob", "college", "hobbies", "studies", "siblings",
		"placeOfbirth", })
public class PersonalInfo {
	@Id
	public String id;

	@JsonProperty("pid")
	private Integer pid;
	@JsonProperty("name")
	private String name;
	@JsonProperty("wifename")
	private String wifename;
	@JsonProperty("girlfriend")
	private String girlfriend;
	@JsonProperty("father")
	private String father;
	@JsonProperty("mother")
	private String mother;
	@JsonProperty("kids")
	private String kids;
	@JsonProperty("school")
	private String school;
	@JsonProperty("dob")
	private String dob;
	@JsonProperty("college")
	private String college;
	@JsonProperty("hobbies")
	private String hobbies;
	@JsonProperty("studies")
	private String studies;
	@JsonProperty("placeOfbirth")
	private String placeOfbirth;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getWifename() {
		return wifename;
	}
	public void setWifename(String wifename) {
		this.wifename = wifename;
	}
	public String getGirlfriend() {
		return girlfriend;
	}
	public void setGirlfriend(String girlfriend) {
		this.girlfriend = girlfriend;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
	public String getKids() {
		return kids;
	}
	public void setKids(String kids) {
		this.kids = kids;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getStudies() {
		return studies;
	}
	public void setStudies(String studies) {
		this.studies = studies;
	}
	public String getPlaceOfbirth() {
		return placeOfbirth;
	}
	public void setPlaceOfbirth(String placeOfbirth) {
		this.placeOfbirth = placeOfbirth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
