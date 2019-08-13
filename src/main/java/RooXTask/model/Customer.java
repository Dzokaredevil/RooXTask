package RooXTask.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="CUSTOMER")
public class Customer {

    @Id
    @Column(columnDefinition="BINARY(16)")
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false)
    private String fullName;
    private Integer balance;
    private boolean active;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<PartnerMapping> partnerMappings;

    public Customer() {
        this.id = UUID.randomUUID();
    }

    public Customer(UUID id) {
        this.id = id;
    }

}