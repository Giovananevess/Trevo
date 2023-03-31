package trevo.voll.api.culture;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.voll.api.response.ResponseModelMessage;
import trevo.voll.api.response.ResponseModelObject;

import java.util.List;


@Service
public class CultureService {
    @Autowired
    CultureRepository cultureRepository;

    public ResponseEntity<?> register(@RequestBody DadosCadastroCultureDTO dto) {
        if (cultureRepository.existsByName(dto.name())) {
            return new ResponseEntity<>(new ResponseModelMessage("Cultura já existe"), HttpStatus.BAD_REQUEST);
        }
        Culture culture = new Culture(dto);
        cultureRepository.save(culture);
        return new ResponseEntity<>(new ResponseModelObject("Cultura cadastrada", culture), HttpStatus.CREATED);
    }
    public ResponseEntity<?> list() {
        List<Culture> cultures = cultureRepository.findAll();
        if (cultures.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelMessage("Lista de Cultura está vazia"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseModelObject("Lista de cultura", cultures), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (cultureRepository.existsById(id)) {
            cultureRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelMessage("Cultura deletada"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseModelMessage("Cultura não encontrada"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid DadosCadastroCultureDTO dto) {
        Culture culture = cultureRepository.findById(id).orElse(null);
        if (cultureRepository.existsByName(dto.name())) {
            return new ResponseEntity<>(new ResponseModelMessage("Cultura com o nome " + dto.name() + " já existe"), HttpStatus.BAD_REQUEST);
        }
        if (!cultureRepository.existsById(id) || culture == null) {
            return new ResponseEntity<>(new ResponseModelMessage("Cultura não encontrada"), HttpStatus.BAD_REQUEST);
        }
        culture.update(dto);
        cultureRepository.save(culture);
        return new ResponseEntity<>(new ResponseModelObject("Cultura atualizado", culture), HttpStatus.OK);
    }
}
