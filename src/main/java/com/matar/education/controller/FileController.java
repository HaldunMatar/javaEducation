package com.matar.education.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.matar.education.service.impl.FileStorageService;
import payload.UploadFileResponse;
import javax.servlet.http.HttpServletRequest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.matar.education.FileStorageProperties;

    


    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.core.io.Resource;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;
    import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
    import com.matar.education.service.impl.FileStorageService;
    import payload.UploadFileResponse;
    import javax.servlet.http.HttpServletRequest;
    import java.io.IOException;
    import java.util.Arrays;
    import java.util.List;
    import java.util.stream.Collectors;
    @CrossOrigin(origins = "*")
    @RestController
    public class FileController {

        private static final Logger logger = LoggerFactory.getLogger(FileController.class);

        @Autowired
        private FileStorageService fileStorageService;
       //// @PostMapping("/uploadFile")
        @PostMapping("/students/uploadFile")
        public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("fileid") String fileid) 
        {
        	System.out.println("I am in server start file upload ");
            String fileName = fileStorageService.storeFile(file,fileid);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileid)
                    .toUriString();
            return 
            		 new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());
        }
        
        @PostMapping("/students/uploadFileFromWeb")
     //   public void  uploadFileFromWeb(@RequestBody  byte[] file1 ) throws IOException 
        public UploadFileResponse uploadFileFromWeb(@RequestParam("file") MultipartFile file)
        {
                                
        	System.out.println("I am in server start file upload ");
        	
        	System.out.println("Converted Successfully!");
        	String fileName = fileStorageService.storeFile(file,file.getName());
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(file.getName())
                    .toUriString();
            return 
            		 new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());
        		        
            
        }
        
        
        @GetMapping("/students/uploadFile1")
        public UploadFileResponse uploadFile1() 
        {
        	System.out.println("I am in server start fiile uplpoad ");
    		return null;
           
        }
        /*
        @PostMapping("/uploadMultipleFiles")
        public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@RequestParam("fileid") String fileid )
        {
            return Arrays.asList(files)
                    .stream()
                    .map(file -> uploadFile(file))
                    .collect(Collectors.toList());
        }*/

        @GetMapping("/downloadFile/{fileName:.+}")
        
        public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
            // Load file as Resource
            Resource resource = fileStorageService.loadFileAsResource(fileName);
           System.out.print(resource);

            // Try to determine file's content type
            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException ex) {
                logger.info("Could not determine file type.");
            }

            // Fallback to the default content type if type could not be determined
            if(contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        }

    }