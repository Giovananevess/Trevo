package trevo.voll.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.voll.api.order.DadosCadastroOrderDTO;
import trevo.voll.api.order.Order;
import trevo.voll.api.order.OrderRepository;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)


public class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void whenCreateOrder_thenPersistenseData() {
        Order order = new Order(new DadosCadastroOrderDTO("Teste2", "giovana@gmail.com",
                null, null));
        orderRepository.save(order);
        assertThat(order.getId()).isNotNull();
        assertThat(order.getName()).isEqualTo("Teste2");
        assertThat(order.getEmail()).isEqualTo("giovana@gmail.com");
    }
    @Test
    public void whenUpdateName_thenPersistenseData() {
        Order order = new Order(new DadosCadastroOrderDTO("Teste2", "giovana@gmail.com",
                null, null));
        orderRepository.save(order);
        order.setName("Teste5");
        orderRepository.save(order);
        assertThat(order.getName()).isEqualTo("Teste5");
    }
    @Test
    public void whenUpdateEmail_thenPersistenseData() {
        Order order = new Order(new DadosCadastroOrderDTO("Teste2", "giovanaaa@gmail.com",
                null, null));
        orderRepository.save(order);
        order.setEmail("giovana@gmail.com");
        orderRepository.save(order);
        assertThat(order.getEmail()).isEqualTo("giovana@gmail.com");
    }

    @Test
    public void whenList_theSearchOrder() {
        Order order = new Order(new DadosCadastroOrderDTO("Teste2", "giovanaaa@gmail.com",
                null, null));
        orderRepository.save(order);
        Optional<Order> all = orderRepository.findById(order.getId());
        Assertions.assertTrue(all.isPresent());
    }

    @Test
    @DisplayName(value = "deve deletar um produto")
    public void whenDelete_theDeleteOrder() {
        Order order = new Order(new DadosCadastroOrderDTO("Teste2", "giovanaaa@gmail.com",
                null, null));
        orderRepository.save(order);
        orderRepository.deleteById(order.getId());
        assertThat(orderRepository.findById(order.getId())).isEmpty();
    }
}
