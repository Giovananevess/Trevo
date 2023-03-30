package trevo.voll.api.order;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;


public record DadosCadastroOrderDTO(
        @NotEmpty(message = "Digite um nome")
        String name,

        @Email
        String email,

        @NotEmpty(message = "")
        String telefone,

        @JsonProperty("products")
        List<Long> productIds
) {
}
