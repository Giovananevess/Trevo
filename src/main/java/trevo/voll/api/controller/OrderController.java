package trevo.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.voll.api.order.DadosCadastroOrderDTO;
import trevo.voll.api.order.OrderService;

@RestController
@RequestMapping("order")

public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroOrderDTO dto) {
        return orderService.register(dto);
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return orderService.list();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return orderService.delete(id);
    }

    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<?> atulizar(@PathVariable Long id, @RequestBody @Valid DadosCadastroOrderDTO dto) {
        return orderService.update(id, dto);
    }
}
