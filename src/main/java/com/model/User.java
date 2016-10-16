package com.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.model.enuns.Status;
import com.util.StaticDB;

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
@Table(name = "user", catalog = StaticDB.DB_NAME, uniqueConstraints = {
		@UniqueConstraint(columnNames = { "username" }) })
public class User implements EntityJpa, Serializable, UserDetails {

	private static final long serialVersionUID = 442738873666572571L;

	@Id
	@TableGenerator(name = "user_generator", table = "GENERATED_KEYS", pkColumnName = "PK_COLUMN", valueColumnName = "VALUE_COLUMN", pkColumnValue = "id_user", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "user_generator")
	private Long id;

	@Column
	@NotNull(message = "error.empty.name")
	private String name;

	@Column
	@NotNull(message = "error.empty.username")
	private String username;

	@JsonIgnore
	@Column
	private String password;

	@Column
	private Status status;

	@Column
	private String description;

	@ElementCollection(fetch = FetchType.EAGER)
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
		return authorities;
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