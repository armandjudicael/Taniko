package mg.imwa.admin.model;

import lombok.Data;
import javax.persistence.*;
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
   private List<TenantUser> tenantUsers;
}
