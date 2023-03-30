package trevo.voll.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.voll.api.area.Area;
import trevo.voll.api.area.AreaRepository;
import trevo.voll.api.area.DadosCadastroAreaDTO;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)






public class AreaRepositoryTest {
    @Autowired
    AreaRepository areaRepository;

    @Test
    public void whenCreate_thenPersistenseData() {
        Area area = new Area(new DadosCadastroAreaDTO("Area1"));
        areaRepository.save(area);
        assertThat(area.getId()).isNotNull();
        assertThat(area.getName()).isEqualTo("Area1");
    }
    @Test
    public void whenUpdate_thenPersistenseData() {
        Area area = new Area(new DadosCadastroAreaDTO("Area2"));
        areaRepository.save(area);
        area.setName("Area10");
        areaRepository.save(area);
        assertThat(area.getName()).isEqualTo("Area10");
    }


    @Test
    public void whenList_theSearchProduct() {
        Area area = new Area(new DadosCadastroAreaDTO("Teste3"));
        areaRepository.save(area);
        Optional<Area> all = areaRepository.findById(area.getId());
        Assertions.assertTrue(all.isPresent());
    }

    @Test
    @DisplayName(value = "deve deletar uma area")
    public void whenDelete_theDeleteArea() {
        Area area = new Area(new DadosCadastroAreaDTO("Teste3"));
        areaRepository.save(area);
        areaRepository.deleteById(area.getId());
        assertThat(areaRepository.findById(area.getId())).isEmpty();
    }
}
