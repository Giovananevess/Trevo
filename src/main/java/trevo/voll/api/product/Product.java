package trevo.voll.api.product;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import trevo.voll.api.area.Area;
import trevo.voll.api.culture.Culture;
import trevo.voll.api.image.Image;
import java.time.LocalDate;
import java.util.List;

@Table(name = "tb_product")
@Entity
@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "Text")
    private String description;
    private LocalDate data;
    private Boolean status;

    @ManyToMany
    @JoinTable(
            name = "tb_product_culture",
            joinColumns = {@JoinColumn(name = "id_product", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_culture", referencedColumnName = "id")}
    )
    private List<Culture> cultures;

    @ManyToMany
    @JoinTable(
            name = "tb_product_area",
            joinColumns = {@JoinColumn(name = "id_product", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "id_area", referencedColumnName = "id")}
    )
    private List<Area> areas;

    @OneToMany
    @JoinTable(
            name = "tb_product_image",
            joinColumns = {@JoinColumn(name = "id_product", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_image", referencedColumnName = "id")}
    )
    private List<Image> images;

    public Product(DadosCadastroProductDTO dto, List<Culture> cultures, List<Area> areas, List<Image> images) {
        this.name = dto.name();
        this.data = LocalDate.now();
        this.description = dto.description();
        this.cultures = cultures;
        this.areas = areas;
        this.images = images;
        this.status = true;
    }

    public Product(DadosCadastroProductDTO dto) {
        this.name = dto.name();
        this.description = dto.description();
    }

    public void update(DadosCadastroProductDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.description() != null) {
            this.description = dto.description();
        }
    }
}