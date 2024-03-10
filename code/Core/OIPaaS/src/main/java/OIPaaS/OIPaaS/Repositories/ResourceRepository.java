package OIPaaS.OIPaaS.Repositories;

import OIPaaS.OIPaaS.Models.Resources.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}
