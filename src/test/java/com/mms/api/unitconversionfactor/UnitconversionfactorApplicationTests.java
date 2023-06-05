package com.mms.api.unitconversionfactor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.mms.api.unitconversionfactor.controller.UnitConversionFactorController;
import com.mms.api.unitconversionfactor.model.UnitConversionFactor;
import com.mms.api.unitconversionfactor.repository.UnitConversionRepository;
import com.mms.api.unitconversionfactor.util.UnitType;

class UnitconversionfactorApplicationTests {

    @Mock
    private UnitConversionRepository conversionRepository;

    @InjectMocks
    private UnitConversionFactorController conversionFactorService;

    public UnitconversionfactorApplicationTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllConversions() {
        List<UnitConversionFactor> mockConversions = new ArrayList<>();
        mockConversions.add(new UnitConversionFactor(UnitType.TEMPERATURE,"celsius","fahrenheit",BigDecimal.valueOf(1.80)));
       mockConversions.add(new UnitConversionFactor(UnitType.LENGTH,"meters","feet",BigDecimal.valueOf(3.28)));

        when(conversionRepository.findAll()).thenReturn(mockConversions);

        ResponseEntity<List<UnitConversionFactor>> result = conversionFactorService.getAllConversions();

        assertEquals(HttpStatusCode.valueOf(200), result.getStatusCode());
        assertEquals(mockConversions, result.getBody());
    }

    @Test
    void testGetConversionById() {
    	UnitConversionFactor mockConversion = new UnitConversionFactor(UnitType.TEMPERATURE,"celsius","fahrenheit",BigDecimal.valueOf(1.80));
        when(conversionRepository.findById(1L)).thenReturn(Optional.of(mockConversion));

        ResponseEntity<UnitConversionFactor> result = conversionFactorService.getConversionById(1L);

        assertEquals(HttpStatusCode.valueOf(200), result.getStatusCode());
        assertEquals(mockConversion, result.getBody());

    }

    @Test
    void testGetConversionById_NotFound() {
        when(conversionRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<UnitConversionFactor> result = conversionFactorService.getConversionById(1L);

        assertEquals(HttpStatusCode.valueOf(404), result.getStatusCode());
    }

    @Test
    void testSaveConversion() {
    	UnitConversionFactor mockedConversion =new UnitConversionFactor(null,UnitType.TEMPERATURE,"celsius","fahrenheit",BigDecimal.valueOf(1.80));
    	UnitConversionFactor savedConversion = new UnitConversionFactor(1L,UnitType.TEMPERATURE,"celsius","fahrenheit",BigDecimal.valueOf(1.80));


        when(conversionRepository.save(mockedConversion)).thenReturn(savedConversion);

        ResponseEntity<UnitConversionFactor> result = conversionFactorService.saveConversion(mockedConversion);

        assertEquals(HttpStatusCode.valueOf(201), result.getStatusCode());

    }
    
}
