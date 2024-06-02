package com.example.sportsbuddy.exceptions;

public class TimeAlreadyBookedException extends RuntimeException{
	String message;
	public TimeAlreadyBookedException(String message){
		super(String.format("Ooops %s",message));
		this.message=message;
	}
	
}
