package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Objects;

@Entity(name = "fonctionnalite")
@Data
@NoArgsConstructor
@Table(name = "fonctionnalite")
@NamedQueries({
        @NamedQuery(name = "fonctionnalite.all", query = "from fonctionnalite")
})
@AllArgsConstructor
public class Fonctionnalite {
    public Fonctionnalite(String nom) {
        this.nom = nom;
    }

    public Fonctionnalite(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String nom;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fonctionnalite that = (Fonctionnalite) o;
        return nom.equals(that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
