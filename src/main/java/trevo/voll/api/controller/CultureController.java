package trevo.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.voll.api.culture.CultureService;
import trevo.voll.api.culture.DadosCadastroCultureDTO;

@RestController
@RequestMapping("culture")

public class CultureController {

    @Autowired
    CultureService cultureService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid DadosCadastroCultureDTO dto) {
        return cultureService.register(dto);
    }
    @GetMapping
    public ResponseEntity<?> list() {
        return cultureService.list();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return cultureService.delete(id);
    }
    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid DadosCadastroCultureDTO dto) {
        return cultureService.update(id, dto);
    }

}
