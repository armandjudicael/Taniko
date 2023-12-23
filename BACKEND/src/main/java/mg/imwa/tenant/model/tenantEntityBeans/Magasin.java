package mg.imwa.tenant.model.tenantEntityBeans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;

@Entity(name = "magasin")
@Table(name = "magasin")
@Getter
@Setter
@NoArgsConstructor
@NamedQueries(value = {
        @NamedQuery(name = "magasin.all", query = "from magasin")
})
public class Magasin {
    @Id
    @Column(name = "id_magasin")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String adresse;

    @Column(columnDefinition = "TEXT")
    private String nomMagasin;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "filialeId", foreignKey = @ForeignKey(name = "magasin_filiale_key_constraint"))
    private Filiale filiale;

    @JsonIgnore
    @ManyToMany(mappedBy = "magasin", cascade = CascadeType.PERSIST)
    private Set<User> users;
}
