package mg.imwa.admin.model.Entity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Data
public class Filiale implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String nom;
   private String addresse;
   private String numTel;
   private String email;
   @OneToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "filiale_tenant_user")
   private List<TenantUser> tenantUsers;
}
