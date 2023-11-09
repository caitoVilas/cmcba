package com.cmcba.api.models.requests;

import com.cmcba.utils.enums.IvaType;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "modelo que representa un provedor para las requests")
public class SupplierRequest implements Serializable {
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
