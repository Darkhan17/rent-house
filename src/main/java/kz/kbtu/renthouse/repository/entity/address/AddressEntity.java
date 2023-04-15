package kz.kbtu.renthouse.repository.entity.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "addresses")
@Entity
public class AddressEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

}
