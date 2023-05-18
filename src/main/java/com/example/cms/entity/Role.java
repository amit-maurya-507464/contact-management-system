package com.example.cms.entity;

import com.example.cms.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

	public Role(Roles roleName) {
		this.roleName = roleName;
	}

	@Enumerated(EnumType.STRING)
	private Roles roleName;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	@JsonIgnore
	private Set<User> users;

}
