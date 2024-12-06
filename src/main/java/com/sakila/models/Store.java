package com.sakila.models;

import java.time.LocalDateTime; // Para manejar fechas
import javax.persistence.*;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "manager_staff_id")
    private Staff managerStaff;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate; // Mejor usar LocalDateTime para fechas

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Staff getManagerStaff() {
        return managerStaff;
    }

    public void setManagerStaff(Staff managerStaff) {
        this.managerStaff = managerStaff;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
