package com.devsu.hackerearth.backend.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AccountDto {

	@EqualsAndHashCode.Include
	private Long id;
	@NotEmpty
	private String number;
	private String type;
	private double initialAmount;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@JsonProperty(value = "isActive")
	private boolean isActive;

	@NotNull
	private Long clientId;

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
}
