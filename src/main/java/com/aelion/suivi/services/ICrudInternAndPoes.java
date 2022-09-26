package com.aelion.suivi.services;

import com.aelion.suivi.dto.InternInputDto;
import com.aelion.suivi.entities.InternEntity;

public interface ICrudInternAndPoes {
	public InternEntity addInternAndPoes(InternInputDto input);
}
