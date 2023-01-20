package mg.imwa.tenant.model.entityEmbededId;

import lombok.Data;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import java.io.Serializable;

@Data
@Embeddable
public class InfoArticleMagasinId implements Serializable {
    private Long magasinId;
    private Long uniteId;
    private Long articleId;
}
