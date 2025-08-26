package com.devsu.hackerearth.backend.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Account extends Base {
    private String number;
	private String type;
	private double initialAmount;
    @Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
    @Column(name = "is_active")
	private boolean isActive;

    @Column(name = "client_id")
    private Long clientId;


    public boolean getIsActive(){
		return isActive;
	}

	public void setIsActive(boolean isActive){
		this.isActive=isActive;
	}
}
