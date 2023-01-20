package mg.imwa.admin.repository;

import mg.imwa.admin.model.Entity.Filiale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AdminSubsidiaryRepository extends JpaRepository<Filiale,Long>{
}
