package com.ddn.inditest.service.impl;

import com.ddn.inditest.dto.ProductDTO;
import com.ddn.inditest.dto.SizeDTO;
import com.ddn.inditest.entity.Stock;
import com.ddn.inditest.mapper.ProductMapper;
import com.ddn.inditest.repository.ProductRepository;
import com.ddn.inditest.repository.StockRepository;
import com.ddn.inditest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final StockRepository stockRepository;

  private final ProductMapper productMapper;

  @Override
  public List<Integer> getAll() {
    List<ProductDTO> products =
        productRepository.findAll().stream()
            .map(productMapper::entityToDTO)
            .collect(Collectors.toList());
    Map<Integer, Integer> stock = convertStockListToMap(stockRepository.findAll());
    products.forEach(
        productDTO ->
            productDTO.getSizes().forEach(sizeDTO -> sizeDTO.setStock(stock.get(sizeDTO.getId()))));

    products.forEach(
        productDTO ->
            productDTO.setSizes(
                productDTO.getSizes().stream()
                    .filter(this::hasAvailableStock)
                    .collect(Collectors.toList())));

    products =
        products.stream()
            .filter(
                productDTO ->
                    productDTO.getSizes().size() > 1
                        && (hasSpecialSizesWithStock(productDTO)
                            || hasNotSpecialSizesWithStock(productDTO)))
            .collect(Collectors.toList());

    Collections.sort(products);

    return products.stream().map(ProductDTO::getId).collect(Collectors.toList());
  }

  private boolean hasAvailableStock(SizeDTO size) {
    return size.getBackSoon() || (size.getStock() != null && size.getStock() > 0);
  }

  private Boolean hasSpecialSizesWithStock(ProductDTO p) {
    for (SizeDTO s : p.getSizes()) {
      if (Boolean.TRUE.equals(s.getSpecial()) && hasAvailableStock(s)) {
        return true;
      }
    }
    return false;
  }

  private Boolean hasNotSpecialSizesWithStock(ProductDTO p) {
    for (SizeDTO s : p.getSizes()) {
      if (Boolean.TRUE.equals(!s.getSpecial()) && hasAvailableStock(s)) {
        return true;
      }
    }
    return false;
  }

  private Map<Integer, Integer> convertStockListToMap(List<Stock> stock) {
    Map<Integer, Integer> stockMap = new HashMap<>();
    for (Stock s : stock) {
      stockMap.put(s.getSizeId(), s.getQuantity());
    }
    return stockMap;
  }
}
