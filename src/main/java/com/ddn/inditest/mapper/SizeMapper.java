package com.ddn.inditest.mapper;

import com.ddn.inditest.dto.SizeCsvDTO;
import com.ddn.inditest.entity.Size;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    builder = @Builder(disableBuilder = true))
public interface SizeMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "backSoon", target = "backSoon")
    @Mapping(source = "special", target = "special")
    Size csvToEntity(SizeCsvDTO sizeCsvDTO);
}
