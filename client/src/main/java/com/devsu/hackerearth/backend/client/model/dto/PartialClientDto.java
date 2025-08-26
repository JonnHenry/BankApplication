package com.devsu.hackerearth.backend.client.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartialClientDto {

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
