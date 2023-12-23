package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Sortie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    private InfoArticleMagasin infoArticleMagasin;
}
