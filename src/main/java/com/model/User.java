package com.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.model.enuns.Authorities;
import com.model.enuns.Status;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = { "id" })
@ToString(callSuper = false, of = { "id", "name" })
@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User implements EntityJpa, Serializable, UserDetails {

	private static final long serialVersionUID = 442738873666572571L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotNull(message = "error.empty.name")
	@NotEmpty(message = "error.empty.name")
	private String name;

	@Column
	@NotNull(message = "error.empty.username")
	@NotEmpty(message = "error.empty.name")
	private String username;

	@JsonIgnore
	@Column
	private String password;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Column
	private String description;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Collection<Authorities> roles;

	@Transient
	@JsonIgnore
	private Collection<UserAuthority> authorities;

	@Override
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	@JsonIgnore
	public Collection<UserAuthority> getAuthorities() {
		if (roles != null) {
			roles.stream().forEach(r -> {
				addAuth(r);
			});
		}

		return authorities;
	}

	private void addAuth(Authorities role) {
		if (authorities == null) {
			authorities = new HashSet<>();
		}
		authorities.add(new UserAuthority(role.getRole()));
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		if (Status.ACTIVE.equals(status)) {
			return true;
		}
		return false;
	}
}