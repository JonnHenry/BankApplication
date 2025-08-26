package com.devsu.hackerearth.backend.client.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClientDto {

	@EqualsAndHashCode.Include
	private Long id;

	private String dni;

	private String name;

	private String password;

	private String gender;

	private int age;

	private String address;

	private String phone;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@JsonProperty(value = "isActive")
	private boolean isActive;

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
}
