package com.aelion.suivi.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aelion.suivi.entities.POEEntity;

public final class InternInputDto {
	public Long id = null;
	
	public String name = "";
	public String firstName = "";
	public Date birthDate = new Date();
	public String phoneNumber = "";
	public String email = "";
	public String address = "";
	
	public List<POEEntity> poes = new ArrayList<>();
}
