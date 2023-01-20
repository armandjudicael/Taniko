package mg.imwa.tenant.model.entityEmbededId;

import lombok.Data;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class InfoVenteId implements Serializable {
    private Long venteId;
    private Long articleId;
}
