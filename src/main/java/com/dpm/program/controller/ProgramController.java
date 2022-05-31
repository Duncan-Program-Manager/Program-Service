package com.dpm.program.controller;

import com.dpm.program.endpoint.ProgramEndpoints;
import com.dpm.program.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ProgramEndpoints.BASE)
public class ProgramController {

    private final ProgramRepository programRepository;

    @Autowired
    public ProgramController(ProgramRepository programRepository)
    {
        this.programRepository = programRepository;
    }

    @GetMapping(value = ProgramEndpoints.TEST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> testCall()
    {
        return new ResponseEntity<>("works!", HttpStatus.OK);
    }

    @GetMapping(value = ProgramEndpoints.GETPROGRAM, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProgram(@RequestParam int id)
    {
        return new ResponseEntity<>(programRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = ProgramEndpoints.GETPAGINATION)
    public ResponseEntity<?> getPagination(@RequestParam int page)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
