package com.dpm.program.repository;

import com.dpm.program.databaseModel.Program;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends CrudRepository<Program, Integer> {
    Program getByName(String Name);
    Program getByUsername(String Username);
    List<Program> getAllByUsername(String Username);
}
