package trevo.voll.api.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record DadosCadastroProductDTO(
        @NotEmpty(message = "o campo não pode estar vazio")
        String name,

        @NotEmpty(message = "o campo não pode estar vazio")
        String description,

        @JsonProperty("areas")
        List<Long> areaIds,

        @JsonProperty("cultures")
        List<Long> cultureIds,

        @JsonProperty("images")
        List<Long> imageIds

) {

}
