package br.com.mundiale.employee.dal.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Access(AccessType.FIELD)
@Table(catalog = "employee", name = "addresses")
public class Address implements Serializable {

    private static final long serialVersionUID = 5949582591051590075L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "street")
    private String street;

    @Column(name = "zipcode")
    private String zipCode;

    @Column(name = "number")
    private Short number;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;
}
