package com.cmcba.api.models.responses;

import com.cmcba.utils.enums.IvaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author claudio.vilas
 * date 11/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SupplierResponse implements Serializable {
    private long id;
    private String name;
    private String address;
    private String location;
    private String province;
    private String telephone;
    private String email;
    private String cuit;
    private IvaType ivaType;
    private String contact;
}
