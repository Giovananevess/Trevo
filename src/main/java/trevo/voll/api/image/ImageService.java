package trevo.voll.api.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import trevo.voll.api.response.ResponseModelMessage;
import trevo.voll.api.response.ResponseModelObject;
import java.io.IOException;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile photo) throws IOException {
        if (imageRepository.existsByName(photo.getOriginalFilename())) {
            return new ResponseEntity<> (new ResponseModelMessage("Imagem " + photo.getOriginalFilename() + " já existe"), HttpStatus.BAD_REQUEST);
        }
        Image image = imageRepository.save(Image.builder()
                .name(photo.getOriginalFilename())
                .type(photo.getContentType())
                .imageData(ImageUtils.compressImage(photo.getBytes())).build());
        return new ResponseEntity<>(new ResponseModelObject("Imagem salva foi salva nome : " + image.getName(), image), HttpStatus.OK);
    }

    public ResponseEntity<?> listImages() {
        List<Image> images = imageRepository.findAll();
        if (images.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelMessage("Não existem imagens cadastradas"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseModelObject("Lista de imagens", images), HttpStatus.OK);
    }

    public byte[] downloadImage(@PathVariable Long id) {
        Image dbImageData = imageRepository.findById(id).orElse(null);
        assert dbImageData != null;
        return ImageUtils.decompressImage(dbImageData.getImageData());
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
//        Image image = imageRepository.findById(id).orElse(null);
        if (!imageRepository.existsById(id)) {
            return new ResponseEntity<>(new ResponseModelMessage("Não existe imagem com esse ID"), HttpStatus.BAD_REQUEST);
        }
        imageRepository.deleteById(id);
//        List<Product> productList = productRepository.findByImages(image);
//        if (productList.isEmpty()) {
//            imageRepository.deleteById(id);
//        }
            return new ResponseEntity<>(new ResponseModelMessage("Imagem não excluida"), HttpStatus.OK);
    }

}
