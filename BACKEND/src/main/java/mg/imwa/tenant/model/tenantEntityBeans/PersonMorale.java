package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@Data
public class PersonMorale extends Person {
    @Column(columnDefinition = "TEXT")
    private String nif;
    @Column(columnDefinition = "TEXT")
    private String stat;
    @Column(columnDefinition = "TEXT")
    private String cif;
    @Column(columnDefinition = "TEXT")
    private String rcs;
}
