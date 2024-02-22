package com.gs.erp.repository.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gs.erp.dao.UploadImageService;
import com.gs.erp.models.UploadImage;

@RestController
public class UploadImageController {

	private final UploadImageService uploadImageService;
	
	public UploadImageController(UploadImageService uploadImageService) {
        this.uploadImageService = uploadImageService;
    }
	
	@GetMapping("/getphoto/{id}")
    public ResponseEntity<byte[]> displayPhoto(@PathVariable("id") long id) throws IOException, SQLException {
    	UploadImage image = uploadImageService.viewById(id);
        if (image != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(image.getPhotoImageData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/getsign/{id}")
    public ResponseEntity<byte[]> displaySign(@PathVariable("id") long id) throws IOException, SQLException {
    	UploadImage image = uploadImageService.viewById(id);
        if (image != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(image.getPhotoSignData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/getimages")
    public List<UploadImage> getAllImages() {
        return uploadImageService.viewAll();
    }
    
    @GetMapping("/getimages/{id}")
    public ResponseEntity<String> displayPhotoAndSign(@PathVariable("id") long id) throws IOException, SQLException {
        UploadImage image = uploadImageService.viewById(id);
        if (image != null) {
            StringBuilder htmlBuilder = new StringBuilder();
            htmlBuilder.append("<html><body>");
            htmlBuilder.append("<img src=\"data:image/jpeg;base64," + Base64.getEncoder().encodeToString(image.getPhotoImageData()) + "\">");
            htmlBuilder.append("<img src=\"data:image/jpeg;base64," + Base64.getEncoder().encodeToString(image.getPhotoSignData()) + "\">");
            htmlBuilder.append("</body></html>");
            return ResponseEntity.ok().body(htmlBuilder.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/uploadphotosign")
    public ResponseEntity<String> uploadImages(@RequestParam("photo") MultipartFile photoFile, 
                                               @RequestParam("sign") MultipartFile signFile) {
        try {
            if (photoFile == null || photoFile.isEmpty() || signFile == null || signFile.isEmpty()) {
                return ResponseEntity.badRequest().body("Both photo and signature files are required.");
            }

            UploadImage image = new UploadImage();
            image.setPhotoImageData(photoFile.getBytes());
            image.setPhotoSignData(signFile.getBytes());
            uploadImageService.create(image);
            return ResponseEntity.ok().body("Images added successfully.");
        } catch (IOException e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading images.");
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred.");
        }
    }

}
