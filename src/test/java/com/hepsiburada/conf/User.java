package com.hepsiburada.conf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;



public class User {
	
	private Properties properties;
	private final String propertyFilePath= "properties//User.properties";

	
	public User(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("User.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getMail(){
		String mail = properties.getProperty("mail");
		if(mail!= null) return mail;
		else throw new RuntimeException("mail not specified in the Configuration.properties file.");		
	}
	
	public String getPassword() {		
		String password = properties.getProperty("password");
		if(password != null) return password;
		else throw new RuntimeException("password not specified in the Configuration.properties file.");		
	}
	
	public String getName() {		
		String name = properties.getProperty("nameSurname");
		if(name != null) return name;
		else throw new RuntimeException("password not specified in the Configuration.properties file.");		
	}
	
	
}
