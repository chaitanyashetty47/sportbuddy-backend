package com.example.sportsbuddy.sports;

import com.example.sportsbuddy.user.User;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Random;


@Document("sports")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Sports {
	
	@Id
	@GeneratedValue
	private String id;
	public String name;
	@ManyToOne
	User user;

}
