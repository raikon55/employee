package org.raikon.employee.dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.raikon.employee.enums.Role;

import lombok.Data;

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
    @Column(name = "name", nullable = false)
    private String name;

    @CPF
    @NotNull
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('INTERN', 'JUNIOR', 'PLAN', 'SENIOR')", nullable = false)
    private Role role;

    @Column(name = "salary_base")
    private Float salaryBase = 1000.0F;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private List<Address> address;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public void setRole(Role role) {
        this.role = role;
        this.setSalaryBase(this.getSalaryBase() * role.bonus());
    }

}
