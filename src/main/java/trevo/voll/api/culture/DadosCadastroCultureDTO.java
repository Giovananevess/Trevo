package trevo.voll.api.culture;


import jakarta.validation.constraints.NotEmpty;

public record DadosCadastroCultureDTO(
    @NotEmpty(message = "Digite o nome da cultura")
        String name) {

}
