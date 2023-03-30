package trevo.voll.api.culture;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Table(name = "tb_culture")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Culture {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true, nullable = false)
    @Length(max = 90)
    private String name;

    public Culture(DadosCadastroCultureDTO dto) {
        this.name = dto.name();
    }

    public void update(DadosCadastroCultureDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
    }
}
