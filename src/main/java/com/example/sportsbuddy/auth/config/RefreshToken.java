package com.example.sportsbuddy.auth.config;

import com.example.sportsbuddy.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {
	
	@Id
	private String id;
	private String token;
	private Instant expiryDate;
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User userInfo;
}
