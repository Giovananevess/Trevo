package trevo.voll.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.voll.api.product.DadosCadastroProductDTO;
import trevo.voll.api.product.Product;
import trevo.voll.api.product.ProductRepository;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void whenCreate_thenPersistenseData() {
        Product product = new Product(new DadosCadastroProductDTO("Teste2", "Esse produto é um pulverizador",
                null, null, null));
        productRepository.save(product);
        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Teste2");
        assertThat(product.getDescricao()).isEqualTo("Esse produto é um pulverizador");
    }
    @Test
    public void whenUpdate_thenPersistenseData() {
        Product product = new Product(new DadosCadastroProductDTO("Frutas", "Esse produto é um pulverizador",
                null, null, null));
        productRepository.save(product);
        product.setName("Teste5");
        productRepository.save(product);
        assertThat(product.getName()).isEqualTo("Teste5");
    }
    @Test
    public void whenUpdateDescription_thenPersistenseData() {
        Product product = new Product(new DadosCadastroProductDTO("Teste2", "Esse produto é um pulverizador",
                null, null, null));
        productRepository.save(product);
        product.setDescricao("Esse produto");
        productRepository.save(product);
        assertThat(product.getDescricao()).isEqualTo("Esse produto");
    }
    @Test
    public void whenList_theSearchProduct() {
        Product product = new Product(new DadosCadastroProductDTO("Teste3", "Esse produto é um pulverizador",
                null, null, null));
        productRepository.save(product);
        Optional<Product> all = productRepository.findById(product.getId());
        Assertions.assertTrue(all.isPresent());

    }
    @Test
    @DisplayName(value = "deve deletar um produto de deletar")
    public void whenDelete_theDeleteProduct() {
        Product product = new Product(new DadosCadastroProductDTO("Teste3", "Esse produto é um pulverizador",
                null, null, null));
        productRepository.save(product);
        productRepository.deleteById(product.getId());
        assertThat(productRepository.findById(product.getId())).isEmpty();
    }
}
