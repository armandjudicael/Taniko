package mg.imwa.tenant.model.entityEmbededId;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import java.io.Serializable;

@Data
@Embeddable
public class PrixVenteUniteArticleFilialeId implements Serializable {
    private Long filialeId;
    private Long articleId;
    private Long uniteId;
}
