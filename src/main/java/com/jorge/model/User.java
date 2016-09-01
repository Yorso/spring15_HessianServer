package com.jorge.model;

import java.io.Serializable;

// CLIENT AND SERVER SIDE
public class User implements Serializable { // Serializable is necessary because the User objects are serialized before they are transmitted over the network
								            // This sentence is FALSE: Note that the User class doesn't need to implement Serializable . Hessian uses its own object	serialization mechanism.
										    // It crashes and warns about serialization of User.java class
	
	private static final long serialVersionUID = 4295592366442619242L;
	
	private String name;
	private int age;
	
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
