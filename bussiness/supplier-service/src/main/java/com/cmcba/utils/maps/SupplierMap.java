package com.cmcba.utils.maps;

import com.cmcba.api.models.responses.SupplierResponse;
import com.cmcba.domain.entities.Supplier;

/**
 * @author claudio.vilas
 * date 11/2023
 */


public class SupplierMap {
    public static SupplierResponse mapToDto(Supplier entity){
        return SupplierResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .location(entity.getLocation())
                .province(entity.getProvince())
                .telephone(entity.getTelephone())
                .email(entity.getEmail())
                .cuit(entity.getCuit())
                .ivaType(entity.getIvaType())
                .contact(entity.getContact())
                .build();
    }
}
