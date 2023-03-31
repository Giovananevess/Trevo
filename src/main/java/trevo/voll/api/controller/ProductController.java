package trevo.voll.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.voll.api.product.*;


@RestController
@RequestMapping("product")

public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody @Valid DadosCadastroProductDTO dto) {
        return productService.register(dto);
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return productService.list();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return productService.delete(id);
    }


    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid DadosCadastroProductDTO dto) {
        return productService.update(id, dto);
    }
}




