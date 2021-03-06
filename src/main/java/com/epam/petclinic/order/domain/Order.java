package com.epam.petclinic.order.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pc_order", schema = "app")
public class Order implements Serializable {
    @Id
    @Column(name = "pc_order_id")
    private UUID id;
    @Column(name = "pc_name")
    @NotNull
    @Size(max = 50)
    private String name;
    @Column(name = "pc_email")
    @NotNull
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    @Column(name = "pc_address")
    private String address;
    @NotNull
    @Column(name = "pc_animal_id")
    private UUID animalId;
    @NotNull
    @ElementCollection
    @CollectionTable(
            name = "service_to_order_map", schema = "app",
            joinColumns = @JoinColumn(name = "pc_order_id")
    )
    @Column(name = "pc_service_id")
    private List<UUID> serviceIds;

    public Order() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UUID getAnimalId() {
        return animalId;
    }

    public void setAnimalId(UUID animalId) {
        this.animalId = animalId;
    }

    public List<UUID> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(List<UUID> serviceIds) {
        this.serviceIds = serviceIds;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj || this.getClass() != obj.getClass()) {
            return false;
        }
        final Order that = (Order) obj;
        return new EqualsBuilder()
                .append(this.id, that.id)
                .append(this.name, that.name)
                .append(this.email, that.email)
                .append(this.address, that.address)
                .append(this.animalId, that.animalId)
                .append(this.serviceIds, that.serviceIds)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.id)
                .append(this.name)
                .append(this.email)
                .append(this.address)
                .append(this.animalId)
                .append(this.serviceIds)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("name", this.name)
                .append("email", this.email)
                .append("address", this.address)
                .append("animalId", this.animalId)
                .append("serviceIds", this.serviceIds)
                .toString();
    }
}
