package mg.imwa.tenant.model.entityEmbededId;

import lombok.Data;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ArticleUniteId implements Serializable {
    private Long articleId;
    private Long uniteId;
}
