package com.dpm.program.controller;

import com.dpm.program.endpoint.ProgramEndpoints;
import com.dpm.program.repository.ProgramRepository;
import com.dpm.program.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping(value = ProgramEndpoints.BASE)
public class ProgramController {

    private final ProgramService programService;

    @Autowired
    public ProgramController(ProgramService programService)
    {
        this.programService = programService;
    }

    @GetMapping(value = ProgramEndpoints.TEST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> testCall()
    {
        return new ResponseEntity<>("works!", HttpStatus.OK);
    }

    @GetMapping(value = ProgramEndpoints.GETPROGRAMWITHID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProgram(@RequestParam int id)
    {
        return new ResponseEntity<>(programService.getProgramById(id), HttpStatus.OK);
    }

    @GetMapping(value = ProgramEndpoints.GETPROGRAMBYNAME, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProgram(@RequestParam String name)
    {
        return new ResponseEntity<>(programService.getProgramByName(name), HttpStatus.OK);
    }

    @GetMapping(value = ProgramEndpoints.GETPAGINATION)
    public ResponseEntity<?> getPagination(@RequestParam int page)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/testUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadTest(@RequestBody MultipartFile file)
    {
        File filefile = new File("src/main/resources/" + file.getOriginalFilename());
        try (OutputStream os = new FileOutputStream(filefile)) {
            os.write(file.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        programService.uploadTest(filefile);
        filefile.delete();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
