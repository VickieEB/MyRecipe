package com.petproject.recipe.service;

import com.petproject.recipe.commands.UnitOfMeasureCommand;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
