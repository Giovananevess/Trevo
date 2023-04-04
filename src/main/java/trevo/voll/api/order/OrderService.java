package trevo.voll.api.order;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.voll.api.product.Product;
import trevo.voll.api.product.ProductRepository;
import trevo.voll.api.response.ResponseModelMessage;
import trevo.voll.api.response.ResponseModelObject;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<?> register(@RequestBody DadosCadastroOrderDTO dto) {
        List<Product> products = productRepository.findByIdIn(dto.productIds());
        if (products.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelMessage("É necessário informar ao menos um produto"), HttpStatus.BAD_REQUEST);
        }
        Order order = new Order(dto, products);
        orderRepository.save(order);
        return new ResponseEntity<>(new ResponseModelObject("Requisição feita", order), HttpStatus.OK);
    }

    public ResponseEntity<?> list() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelMessage("Lista de requisição está vazia"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseModelObject("Lista de requisição", orders), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelMessage("Requisição deletada"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseModelMessage("Requisição não encontrado"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid DadosCadastroOrderDTO dto) {
    Order order = orderRepository.findById(id).orElse(null);
    if (orderRepository.existsByName(dto.name())){
        return new ResponseEntity<>(new ResponseModelMessage("Requição com o nome " + dto.name() + " já existe"), HttpStatus.BAD_REQUEST);
    }
    if (!orderRepository.existsById(id) || order == null) {
        return new ResponseEntity<>(new ResponseModelMessage("Requisição não encontrado"), HttpStatus.BAD_REQUEST);
    }
    order.atualizar(dto);
    orderRepository.save(order);
    return new ResponseEntity<>(new ResponseModelObject("Requisição atualizada", order), HttpStatus.OK);
    }
}
