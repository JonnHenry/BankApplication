package com.devsu.hackerearth.backend.account.model.dto;

import javax.validation.constraints.NotNull;

import com.devsu.hackerearth.backend.account.constants.AccountConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartialAccountDto {

	@NotNull(message=AccountConstants.PARTIAL_ACCOUNT_NULL)
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@JsonProperty(value = "isActive")
	private boolean isActive;
 
	public boolean getIsActive(){
		return isActive;
	}

	public void setIsActive(boolean isActive){
		this.isActive=isActive;
	}
}
