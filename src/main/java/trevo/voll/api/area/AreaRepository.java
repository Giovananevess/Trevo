package trevo.voll.api.area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findByIdIn(List<Long> areaIds);

    Boolean existsByName(String name);

}
