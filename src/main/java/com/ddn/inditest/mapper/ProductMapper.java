package com.ddn.inditest.mapper;

import com.ddn.inditest.dto.ProductDTO;
import com.ddn.inditest.entity.Product;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface ProductMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "sequence", target = "sequence")
    @Mapping(source = "sizes", target = "sizes")
    ProductDTO entityToDTO(Product product);
}
