package mg.imwa.tenant.model.tenantEntityBeans;

import jakarta.persistence.Entity;
import lombok.Data;

import jakarta.persistence.Column;

@Entity
@Data
public class PersoneMorale extends Person {
    @Column(columnDefinition = "TEXT")
    private String nif;
    @Column(columnDefinition = "TEXT")
    private String stat;
    @Column(columnDefinition = "TEXT")
    private String cif;
    @Column(columnDefinition = "TEXT")
    private String rcs;
}
