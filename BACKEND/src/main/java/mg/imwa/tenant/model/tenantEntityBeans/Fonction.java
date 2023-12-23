package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;
import lombok.NoArgsConstructor;
import mg.imwa.tenant.model.entityEnum.DefaultPage;

import jakarta.persistence.*;

import java.util.Map;

@Entity(name = "fonction")
@Table(name = "fonction")
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "fonction.all", query = "from fonction")
})
public class Fonction{
    
    @Override
    public String toString() {
        return "Fonction{" +
                "id=" + id +
                ", nomFonction='" + nomFonction + '\'' +
                ", filiale=" + filiale +
                ", defaultPage=" + defaultPage +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String nomFonction;

    @ElementCollection
    @CollectionTable(name="fonction_autorisation",
            joinColumns = {@JoinColumn(name = "fonction_id",referencedColumnName="id")})
    @MapKeyColumn(name = "fonctionnalite_id")
    @Column(name = "status")
    private Map<Fonctionnalite, Long> autorisationMap;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "fonction_filiale")
    private Filiale filiale;

    @Enumerated(EnumType.ORDINAL)
    private DefaultPage defaultPage;
}
