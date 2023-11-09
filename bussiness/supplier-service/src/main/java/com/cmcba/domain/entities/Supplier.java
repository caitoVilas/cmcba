package com.cmcba.domain.entities;

import com.cmcba.utils.enums.IvaType;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author claudio.vilas
 * date 11/2023
 */

@Entity
@Table(name = "suppliers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String location;
    private String province;
    private String telephone;
    private String email;
    private String cuit;
    @Enumerated(EnumType.STRING)
    private IvaType ivaType;
    private String contact;
}
