package mg.imwa.tenant.model.entityEmbededId;

import lombok.*;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
@Data
public class VoyageId implements Serializable {
    private Long materielTransportId;
    private Long articleId;
    private Long fournisseurId;
}
