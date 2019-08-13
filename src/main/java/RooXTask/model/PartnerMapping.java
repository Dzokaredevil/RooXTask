package RooXTask.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="PARTNERMAPPING")
public class PartnerMapping {

    @Id
    @Column(columnDefinition="BINARY(16)")
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String partnerId;
    private String accountId;
    private String fullName;
    @Column(columnDefinition="VARBINARY(5000)")
    private byte[] avatar;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public PartnerMapping() {
        this.id = UUID.randomUUID();
    }

    public PartnerMapping(UUID id) {
        this.id = id;
    }
}