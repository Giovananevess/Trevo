package trevo.voll.api.order;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.autoconfigure.web.WebProperties;
import trevo.voll.api.area.DadosCadastroAreaDTO;
import trevo.voll.api.product.Product;

import java.util.List;

@Table(name = "tb_order")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Length(max = 90)
    private String name;

    @Email
    private String email;

    private String telefone;

    @ManyToMany
    @JoinTable(
            name = "tb_order_product",
            joinColumns = {@JoinColumn(name = "id_order", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_product", referencedColumnName = "id")}
    )
    private List<Product> products;

    public Order(DadosCadastroOrderDTO dto, List<Product> products) {
        this.products = products;
        this.name = dto.name();
        this.email = dto.email();
        this.telefone = dto.telefone();
    }

    public Order(DadosCadastroOrderDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.telefone = dto.telefone();
    }


    public void atualizar(DadosCadastroOrderDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.email() != null) {
            this.email = dto.email();
        }
        if (dto.telefone() != null) {
            this.telefone = dto.telefone();
        }
    }
}