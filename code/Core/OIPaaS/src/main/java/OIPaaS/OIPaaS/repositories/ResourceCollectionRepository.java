package oipaas.oipaas.repositories;

import oipaas.oipaas.models.resources.ResourceCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceCollectionRepository extends JpaRepository<ResourceCollection, Integer> {
}
