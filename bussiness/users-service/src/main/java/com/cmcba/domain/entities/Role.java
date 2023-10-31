package com.cmcba.domain.entities;

import com.cmcba.utils.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author claudio.vilas
 * date 10/2023
 */

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
