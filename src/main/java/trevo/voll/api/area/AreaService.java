package trevo.voll.api.area;

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
public class AreaService {
    @Autowired
    AreaRepository areaRepository;

    public ResponseEntity<?> register(@RequestBody DadosCadastroAreaDTO dto) {
        if (areaRepository.existsByName(dto.name())) {
            return new ResponseEntity<>(new ResponseModelMessage("Area já existe"), HttpStatus.BAD_REQUEST);
        }
        Area area = new Area(dto);
        areaRepository.save(area);
        return new ResponseEntity<>(new ResponseModelObject("Area cadastrada", area), HttpStatus.CREATED);
    }

    public ResponseEntity<?> list() {
        List<Area> areas = areaRepository.findAll();
        if (areas.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelMessage("Lista de Area está vazia"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseModelObject("Lista de area", areas), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (areaRepository.existsById(id)) {
            areaRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelMessage("Area deletada"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseModelMessage("Area não encontrada"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid DadosCadastroAreaDTO dto) {
        Area area = areaRepository.findById(id).orElse(null);
        if (areaRepository.existsByName(dto.name())) {
            return new ResponseEntity<>(new ResponseModelMessage("Area com o nome " + dto.name() + " já existe"), HttpStatus.BAD_REQUEST);
        }
        if (!areaRepository.existsById(id) || area == null) {
            return new ResponseEntity<>(new ResponseModelMessage("Area não encontrada"), HttpStatus.BAD_REQUEST);
        }
        area.update(dto);
        areaRepository.save(area);
        return new ResponseEntity<>(new ResponseModelObject("Area atualizado", area), HttpStatus.OK);
    }
}