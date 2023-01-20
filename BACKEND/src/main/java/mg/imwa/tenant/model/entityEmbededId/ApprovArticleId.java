package mg.imwa.tenant.model.entityEmbededId;

import lombok.Data;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import java.io.Serializable;

@Embeddable
@Data
public class ApprovArticleId implements Serializable {
    private Long approvId;
    private Long articleId;
}
