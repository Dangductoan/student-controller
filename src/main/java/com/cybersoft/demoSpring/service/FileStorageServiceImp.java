package com.cybersoft.demoSpring.service;

import com.cybersoft.demoSpring.exception.FileStorageException;
import com.cybersoft.demoSpring.exception.MyFileNotFoundException;
import com.cybersoft.demoSpring.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImp implements FileStorageService{
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageServiceImp(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch(Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",ex);

        }
    }
    public String storeFile(MultipartFile file) {
        //normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            //check if file 's name contains invalid char
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry filName contain invalid path sequence" + fileName);
            }
            //copy file to the target location(Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch(IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }

    }




    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }



}
