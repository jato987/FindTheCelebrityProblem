package com.problem.dto;

import java.util.HashSet;
import java.util.Set;

public class Person {

	private String name;
	private Set<String> knowit = new HashSet<String>();
	private Set<String> knowme = new HashSet<String>();
	
	/**
	 * @param name
	 */
	public Person(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the knowit
	 */
	public Set<String> getKnowit() {
		return knowit;
	}

	/**
	 * @param knowit the knowit to set
	 */
	public void setKnowit(Set<String> knowit) {
		this.knowit = knowit;
	}

	/**
	 * @return the knowme
	 */
	public Set<String> getKnowme() {
		return knowme;
	}

	/**
	 * @param knowme the knowme to set
	 */
	public void setKnowme(Set<String> knowme) {
		this.knowme = knowme;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [name=" + name + ", knowit=" + knowit + ", knowme=" + knowme + "]";
	}
	
	
}
