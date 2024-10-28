package com.electronics.store.electronocs_Store.services.impl;

import com.electronics.store.electronocs_Store.exception.BadRequestException;
import com.electronics.store.electronocs_Store.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadImage(MultipartFile file, String path) throws IOException {
        String fileName = file.getOriginalFilename();
        logger.info("Original Fle Name {}",fileName);
        String uniqueFilename = UUID.randomUUID().toString();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String fileNameWithExtension = uniqueFilename+extension;
        String fullPathWithFileName = path+ File.separator+fileNameWithExtension;
        if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg")|| extension.equalsIgnoreCase(".jpeg")){


            //file save
            File folder = new File(path);
            if(folder.exists()){
                //create folder
                folder.mkdirs();
            }

            //upload
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return fileNameWithExtension;
        }else{
            throw new BadRequestException("File with this "+extension+" not allowed !!");
        }

    }

    @Override
    public InputStream getResources(String path, String name) throws FileNotFoundException {
        String fullPath = path+File.separator+name;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}
