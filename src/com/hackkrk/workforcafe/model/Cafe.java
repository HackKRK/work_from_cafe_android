package com.hackkrk.workforcafe.model;

import java.util.Map;

public class Cafe {
	
	private String city;
	private String name;
	private String address;
	private Map<String, String> description;
	private Map<String, String> link;
	private String country;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Map<String, String> getDescription() {
		return description;
	}

	public void setDescription(Map<String, String> description) {
		this.description = description;
	}

	public Map<String,String> getLink() {
		return link;
	}

	public void setLink(Map<String, String> link) {
		this.link = link;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
