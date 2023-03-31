package trevo.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.voll.api.area.AreaService;
import trevo.voll.api.area.DadosCadastroAreaDTO;

@RestController
@RequestMapping("area")

public class AreaController {

    @Autowired
    AreaService areaService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid DadosCadastroAreaDTO dto) {
        return areaService.register(dto);
    }

    @GetMapping
    public ResponseEntity<?> list() { return areaService.list(); }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) { return areaService.delete(id); }

    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid DadosCadastroAreaDTO dto) {
        return areaService.update(id, dto);
    }
}
