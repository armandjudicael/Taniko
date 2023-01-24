package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String nom;

    @Column(columnDefinition = "TEXT")
    private String adresse;

    @Column(columnDefinition = "TEXT")
    private String numTel;

    @Column(columnDefinition = "TEXT")
    private String email;

    @Lob
    private byte[] photo;

    public Person() {

    }
}
