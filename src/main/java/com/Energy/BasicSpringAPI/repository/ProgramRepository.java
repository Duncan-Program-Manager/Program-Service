package com.Energy.BasicSpringAPI.repository;

import com.Energy.BasicSpringAPI.databaseModel.Program;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends CrudRepository<Program, Integer> {
    Program getByName(String name);
}
