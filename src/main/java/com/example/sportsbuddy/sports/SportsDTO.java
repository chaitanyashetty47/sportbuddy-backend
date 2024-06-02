package com.example.sportsbuddy.sports;

import com.example.sportsbuddy.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class SportsDTO {
	@NotEmpty
	private String Id;
	@NotEmpty
	@Size(max = 20, message = "Name can't be more than 20 characters")
	private String name;
	
	private User user;
}
