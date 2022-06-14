package com.dpm.program.service;

import com.dpm.program.cloudstorage.DropboxStorage;
import com.dpm.program.databaseModel.Program;
import com.dpm.program.dto.ProgramDTO;
import com.dpm.program.dto.ProgramRecieveDTO;
import com.dpm.program.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.LifecycleProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

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

    public void UploadProgram(ProgramRecieveDTO dto, byte[] file) throws IOException {
        File filefile = new File(dto.getName());
        try (OutputStream os = new FileOutputStream(filefile)) {
            os.write(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String path = storage.uploadFile(filefile);
        if(!filefile.delete())
        {
            throw new IOException("failed to delete file");
        }
        Program program = new Program();
        program.setUsername(dto.getUsername());
        program.setDescription(dto.getDescription());
        program.setUploadDate(new Date());
        program.setUserUpload(dto.isUserUpload());
        program.setName(dto.getName());
        program.setVersion(dto.getVersion());
        program.setLocation(path);
        programRepository.save(program);
    }

    public void deleteUserDataFromPrograms(String username)
    {
        List<Program> programsFromUser = programRepository.getAllByUsername(username);
        for (Program program: programsFromUser) {
            program.setUsername("");
            programRepository.save(program);
        }
    }

    public List<Program> getAllPrograms()
    {
        return (List<Program>) programRepository.findAll();
    }

    public void uploadTest(File file)
    {
        System.out.println(storage.uploadFile(file));
    }


}
