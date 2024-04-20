package oipaas.oipaas.repositories;

import oipaas.oipaas.models.resources.ResourceAbstract;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ResourceRepositoryAbstract extends JpaRepository<ResourceAbstract, Integer> {
    List<ResourceAbstract> findByParentIsNull();
}
