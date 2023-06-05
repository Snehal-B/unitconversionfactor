package com.mms.api.unitconversionfactor.model;

import java.math.BigDecimal;

import com.mms.api.unitconversionfactor.util.UnitType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * @author snehal.bachchhav
 *
 */
@Entity
public class UnitConversionFactor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "unit_type")
	private UnitType type;

	@Column(name = "from_unit")
	private String from;

	@Column(name = "to_unit")
	private String to;

	@Column
	private BigDecimal conversion_factor;

	public UnitConversionFactor() {
	}

	public UnitConversionFactor(UnitType unit_type, String from_unit, String to_unit, BigDecimal conversion_factor) {
		super();
		this.type = unit_type;
		this.from = from_unit;
		this.to = to_unit;
		this.conversion_factor = conversion_factor;
	}

	
	public UnitConversionFactor(Long id, UnitType type, String from, String to, BigDecimal conversion_factor) {
		super();
		this.id = id;
		this.type = type;
		this.from = from;
		this.to = to;
		this.conversion_factor = conversion_factor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom_unit() {
		return from;
	}

	public void setFrom_unit(String from_unit) {
		this.from = from_unit;
	}

	public String getTo_unit() {
		return to;
	}

	public void setTo_unit(String to_unit) {
		this.to = to_unit;
	}

	public BigDecimal getConversion_factor() {
		return conversion_factor;
	}

	public void setConversion_factor(BigDecimal conversion_factor) {
		this.conversion_factor = conversion_factor;
	}

	public UnitType getType() {
		return type;
	}

	public void setType(UnitType type) {
		this.type = type;
	}

}
