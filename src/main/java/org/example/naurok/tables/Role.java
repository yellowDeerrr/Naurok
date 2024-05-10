package org.example.naurok.tables;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "roles")
public class Role {
    // insert into roles (id, name) values (1, 'ROLE_USER');
    // insert into roles (id, name) values (2, 'ROLE_ADMIN');

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
