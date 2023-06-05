package com.mms.api.unitconversionfactor.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mms.api.unitconversionfactor.exception.UnitConversionNotFoundException;
import com.mms.api.unitconversionfactor.model.UnitConversionFactor;
import com.mms.api.unitconversionfactor.repository.UnitConversionRepository;
import com.mms.api.unitconversionfactor.util.UnitType;

/**
 * @author snehal.bachchhav
 * Get Conversion factor for Input Values from and to 
 *
 */
@RestController
@RequestMapping(path = "/conversionfactor", produces = { "application/json", "text/xml" })
@EnableAutoConfiguration
public class UnitConversionFactorController {

	private Logger logger = LoggerFactory.getLogger(UnitConversionFactorController.class);

	@Autowired
	private UnitConversionRepository repository;

	@GetMapping("/{from}/{to}")
	public UnitConversionFactor getUnitConversionByFromToUnit(@PathVariable String from,
			@PathVariable String to) throws UnitConversionNotFoundException {
		
		UnitConversionFactor unitConversionFactor = repository.findByFromAndTo(from, to);
		if(null==unitConversionFactor) {
			logger.error("Unit Conversion not found - from:"+from +" to:"+to);
			throw new UnitConversionNotFoundException(from,to) ;
		}
		logger.info("Return Unit Conversion from:"+from +" to:"+to);
		return unitConversionFactor;
	}
	
	@GetMapping("/all")
    public ResponseEntity<List<UnitConversionFactor>> getAllConversions()  {
		 logger.info("Returning All Unit Conversions");
        List<UnitConversionFactor> conversions = repository.findAll();
        return ResponseEntity.ok(conversions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitConversionFactor> getConversionById(@PathVariable Long id) {
    	logger.info("Returning  Unit Conversion  by Id "+ id);
        Optional<UnitConversionFactor> conversion = repository.findById(id);
        return conversion.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("type/{type}")
    public ResponseEntity<List<UnitConversionFactor>>  getConversionByType(@PathVariable UnitType type) {
    	logger.info("Returning Unit Conversion  by Type "+ type);
    	List<UnitConversionFactor> conversions = repository.findByType(type);
    	return ResponseEntity.ok(conversions);
    }
    
    
    @PostMapping
    public ResponseEntity<UnitConversionFactor> saveConversion(@RequestBody UnitConversionFactor conversion) {
    	UnitConversionFactor savedConversion = repository.saveAndFlush(conversion);
    	 logger.info("Creating a new Unit Conversion");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConversion);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteConversion(@PathVariable Long id) {
    	   Optional<UnitConversionFactor> conversion = repository.findById(id);
    	   if(conversion.isPresent()) {
    			repository.delete(conversion.get());
    	   }
    	   logger.info("Deleting Unit Conversion by id"+id);
        return ResponseEntity.noContent().build();
    }
	

}
