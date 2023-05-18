package com.ddn.inditest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO implements Comparable<ProductDTO> {
    private Integer id;
    private Integer sequence;
    private List<SizeDTO> sizes;

    @Override
    public int compareTo(ProductDTO o) {
        return getSequence().compareTo(o.getSequence());
    }
}
