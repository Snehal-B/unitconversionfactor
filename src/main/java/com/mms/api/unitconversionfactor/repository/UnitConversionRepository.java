package com.mms.api.unitconversionfactor.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mms.api.unitconversionfactor.model.UnitConversionFactor;
import com.mms.api.unitconversionfactor.util.UnitType;

/**
 * @author snehal.bachchhav
 *
 */
public interface UnitConversionRepository extends JpaRepository<UnitConversionFactor, Long> {
	UnitConversionFactor findByFromAndTo(String from, String to);
	Optional<UnitConversionFactor> findById(Long id);
	List<UnitConversionFactor>  findByType(UnitType type);
	BigDecimal findUnitConversionByFromAndTo(String from, String to);
	List<UnitConversionFactor> findAll();
	UnitConversionFactor saveAndFlush(UnitConversionFactor entity);
	void delete(UnitConversionFactor entity);
	
	
	
}
