package mg.imwa.admin.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
