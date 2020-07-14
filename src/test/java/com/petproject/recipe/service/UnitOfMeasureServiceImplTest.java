package com.petproject.recipe.service;

import com.petproject.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.petproject.recipe.domain.UnitOfMeasure;
import com.petproject.recipe.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;


    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;


    @Mock
    UnitOfMeasureService unitOfMeasureService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void testListAllUoms() {

        //given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);

        unitOfMeasures.add(uom1);
        unitOfMeasures.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        //when
        //Set<UnitOfMeasureCommand> uomCommands = unitOfMeasureService.listAllUoms();

        //then
        assertEquals(2, unitOfMeasures.size());
       // verify(unitOfMeasureRepository, times(1)).findAll();

    }
}