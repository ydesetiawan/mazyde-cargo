package com.mazyde.cargo.model;

import com.mazyde.cargo.persistence.AuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", indexes = {@Index(name = "IDX_User_1", columnList = "username,email,name")})
@EqualsAndHashCode(of = "id", callSuper = false)
public class User extends AuditingEntity implements BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, name = "is_deleted")
    private boolean deleted;

}
