package trevo.voll.api.area;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Table(name = "tb_area")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Area {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true, nullable = false)
    @Length(max = 90)
    private String name;

    public Area(DadosCadastroAreaDTO dto){

        this.name = dto.name();
    }

    public void update(DadosCadastroAreaDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
    }
}
