package com.example.sportsbuddy.turf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.sportsbuddy.playground.Playground;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("turf")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Turf {
	
	@Id
	@GeneratedValue
	String id;
	String name;
	Integer count;
	String address;
	String city;
	@OneToMany(mappedBy="turf")
	private Set<Playground> playgrounds;
}
