
package trevo.voll.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.voll.api.culture.Culture;
import trevo.voll.api.culture.CultureRepository;
import trevo.voll.api.culture.DadosCadastroCultureDTO;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class CultureRepositoryTest {
    @Autowired
    CultureRepository cultureRepository;

    @Test
    public void whenCreate_thenPersistenseData() {
        Culture culture = new Culture(new DadosCadastroCultureDTO("Culture"));
        cultureRepository.save(culture);
        assertThat(culture.getId()).isNotNull();
        assertThat(culture.getName()).isEqualTo("Culture1");
    }

    @Test
    public void whenUpdate_thenPersistenseData() {
        Culture culture = new Culture(new DadosCadastroCultureDTO("Culture2"));
        cultureRepository.save(culture);
        culture.setName("Culture10");
        cultureRepository.save(culture);
        assertThat(culture.getName()).isEqualTo("Culture10");
    }

    @Test
    public void whenList_theSearchProduct() {
        Culture culture = new Culture(new DadosCadastroCultureDTO("Teste2"));
        cultureRepository.save(culture);
        Optional<Culture> all = cultureRepository.findById(culture.getId());
        Assertions.assertTrue(all.isPresent());
    }

    @Test
    @DisplayName(value = "deve deletar uma area")
    public void whenDelete_theDeleteArea() {
        Culture culture = new Culture(new DadosCadastroCultureDTO("Culture3"));
        cultureRepository.save(culture);
        cultureRepository.deleteById(culture.getId());
        assertThat(cultureRepository.findById(culture.getId())).isEmpty();
    }
}
