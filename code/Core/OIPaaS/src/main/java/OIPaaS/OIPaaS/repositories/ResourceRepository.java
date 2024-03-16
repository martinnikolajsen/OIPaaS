package oipaas.oipaas.repositories;

import oipaas.oipaas.models.resources.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository<T extends Resource> extends JpaRepository<T, Integer> {
}
