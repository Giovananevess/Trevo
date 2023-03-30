package trevo.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import trevo.voll.api.image.ImageService;

import java.io.IOException;

@RestController
@RequestMapping("image")
public class ImageController {
    @Autowired
    ImageService imageService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestParam("foto")MultipartFile foto)throws IOException{
        return imageService.uploadImage(foto);
    }
    @GetMapping(value = "/list")
    public ResponseEntity<?> list() {
        return imageService.listImages();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return imageService.delete(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> searchimage(@PathVariable Long id) {
        byte[]imageData = imageService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }
}
