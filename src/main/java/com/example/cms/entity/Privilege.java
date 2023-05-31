package com.example.cms.entity;

import com.example.cms.enums.Privileges;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "privileges")
public class Privilege extends BaseEntity {

	@Enumerated(EnumType.STRING)
	private Privileges name;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "privileges")
	@JsonIgnore
	private Set<Role> roles;

}