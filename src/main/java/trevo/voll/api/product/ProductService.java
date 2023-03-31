package trevo.voll.api.product;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.voll.api.area.Area;
import trevo.voll.api.area.AreaRepository;
import trevo.voll.api.culture.Culture;
import trevo.voll.api.culture.CultureRepository;
import trevo.voll.api.image.Image;
import trevo.voll.api.image.ImageRepository;
import trevo.voll.api.response.ResponseModelMessage;
import trevo.voll.api.response.ResponseModelObject;
import java.util.List;

@Service
public class ProductService {
    @Autowired //injeção de dependencia
    ProductRepository productRepository;

    @Autowired
    CultureRepository cultureRepository;

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    ImageRepository imageRepository;

    public ResponseEntity<?> register(@RequestBody @Valid DadosCadastroProductDTO dto) {
        List<Culture> cultures = cultureRepository.findByIdIn(dto.cultureIds());
        List<Area> areas = areaRepository.findByIdIn(dto.areaIds());
        List<Image> images = imageRepository.findByIdIn(dto.imageIds());
        if (cultures.isEmpty()){
            return new ResponseEntity<>(new ResponseModelMessage("Produto tem que ser cadastrado com uma cultura"), HttpStatus.BAD_REQUEST);
        }
        if (areas.isEmpty()){
            return new ResponseEntity<>(new ResponseModelMessage("Produto tem que ser cadastrado com uma area"), HttpStatus.BAD_REQUEST);
        }
        if (images.isEmpty()){
            return new ResponseEntity<>(new ResponseModelMessage("Produto tem que ser cadastrado com uma imagem"), HttpStatus.BAD_REQUEST);
        }

        if (productRepository.existsByName(dto.name())){
            return new ResponseEntity<>(new ResponseModelMessage("Esse produto já existe"), HttpStatus.BAD_REQUEST);
        }
        Product product = new Product(dto, cultures, areas,images);
        productRepository.save(product);
        return new ResponseEntity<>(new ResponseModelObject("Produto cadastrado", product), HttpStatus.OK);
    }

    public ResponseEntity<?> list() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelMessage("Lista de produtos está vazia"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseModelObject("Lista dos produtos", products), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelMessage("Produto foi deletado"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseModelMessage("Produto não encontrado"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid DadosCadastroProductDTO dto) {
        Product product = productRepository.findById(id).orElse(null);
        if (productRepository.existsByName(dto.name())){
            return new ResponseEntity<>(new ResponseModelMessage("Produto com nome " + dto.name() + " já existe"), HttpStatus.BAD_REQUEST);
        }
        if (!productRepository.existsById(id) || product == null) {
            return new ResponseEntity<>(new ResponseModelMessage("Produto não encontrado"), HttpStatus.BAD_REQUEST);
        }
        product.update(dto);
        productRepository.save(product);
        return new ResponseEntity<>(new ResponseModelObject("Produto atualizado", product), HttpStatus.OK);
    }
}
