package mg.imwa.tenant.model.tenantEntityBeans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "filiale")
@Getter
@Setter
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "filiale.all", query = "from filiale")
})
public class Filiale extends Person {

    @JsonIgnore
    @OneToMany(mappedBy = "filiale", cascade = CascadeType.MERGE)
    private List<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "filiale", cascade = CascadeType.MERGE)
    private List<Magasin> magasins;

    @Transient
    private Double chiffreAffaire = 0D;

    @PostLoad
    private void setChiffreAffaire() {

    }

    private boolean active = true;
}
