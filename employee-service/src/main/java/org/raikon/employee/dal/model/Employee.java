package org.raikon.employee.dal.model;

import org.hibernate.FetchMode;
import org.raikon.employee.enums.Role;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "employee")
@Access(AccessType.FIELD)
public class Employee implements Serializable {

    private static final long serialVersionUID = -2827505572033027588L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @CPF
    @NotNull
    @Column(name = "cpf")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('INTERN', 'JUNIOR', 'PLAN', 'SENIOR')")
    private Role role;

    @Column(name = "salary_base")
    private Float salaryBase = 1000.0F;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private List<Address> address;

    public void setRole(Role role) {
        this.role = role;
        this.setSalaryBase(this.getSalaryBase() * role.bonus());
    }

}
