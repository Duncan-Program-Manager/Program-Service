package com.dpm.program.service;

import com.dpm.program.cloudstorage.DropboxStorage;
import com.dpm.program.databaseModel.Program;
import com.dpm.program.dto.ProgramDTO;
import com.dpm.program.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.LifecycleProcessor;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ProgramService {

    private DropboxStorage storage = new DropboxStorage();

    @Autowired
    private ProgramRepository programRepository;

    public Program getProgramById(int id)
    {
        if(programRepository.findById(id).isPresent())
        return programRepository.findById(id).get();

        return null;
    }

    public Program getProgramByName(String name)
    {
        return programRepository.getByName(name);
    }

    public void UploadProgram(ProgramDTO dto)
    {
        String path = storage.uploadFile(dto.getFile());
        Program program = new Program();
        program.setUsername(dto.getUsername());
        program.setDescription(dto.getDescription());
        program.setUploadDate(dto.getUploadDate());
        program.setUserUpload(dto.isUserUpload());
        program.setName(dto.getName());
        program.setVersion(dto.getVersion());
        program.setLocation(path);
        programRepository.save(program);
    }

    public void uploadTest(File file)
    {
        System.out.println(storage.uploadFile(file));
    }


}
