package com.ecom.inventoryservice.mapper;

import com.ecom.inventoryservice.dto.AddInventoryRequest;
import com.ecom.inventoryservice.models.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryMapper {

    Inventory toEntity(AddInventoryRequest addInventoryRequest);

    AddInventoryRequest toAddInventoryDTO(Inventory inventory);
}
