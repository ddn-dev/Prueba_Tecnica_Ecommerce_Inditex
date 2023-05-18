package com.ddn.inditest.service;

import com.ddn.inditest.dto.SizeCsvDTO;
import com.ddn.inditest.entity.Product;
import com.ddn.inditest.entity.Size;
import com.ddn.inditest.entity.Stock;
import com.ddn.inditest.mapper.SizeMapper;
import com.ddn.inditest.repository.ProductRepository;
import com.ddn.inditest.repository.SizeRepository;
import com.ddn.inditest.repository.StockRepository;
import com.ddn.inditest.util.CSVUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataSetUpService {

  private final SizeMapper sizeMapper;

  private final ProductRepository productRepository;
  private final SizeRepository sizeRepository;
  private final StockRepository stockRepository;

  private static final String PRODUCT_CSV_PATH = "src/main/resources/data/product.csv";
  private static final String SIZE_CSV_PATH = "src/main/resources/data/size.csv";
  private static final String STOCK_CSV_PATH = "src/main/resources/data/stock.csv";

  @PostConstruct
  public void loadData() throws IOException {
    loadProducts();
    loadSizes();
    loadStock();
  }

  private void loadProducts() throws IOException {
    List<Product> products = CSVUtils.read(Product.class, new FileInputStream(PRODUCT_CSV_PATH));
    productRepository.saveAll(products);
  }

  private void loadSizes() throws IOException {
    List<SizeCsvDTO> csvSizes = CSVUtils.read(SizeCsvDTO.class, new FileInputStream(SIZE_CSV_PATH));
    List<Size> sizes = csvSizes.stream().map(sizeMapper::csvToEntity).collect(Collectors.toList());
    sizeRepository.saveAll(sizes);
  }

  private void loadStock() throws IOException {
    List<Stock> stock = CSVUtils.read(Stock.class, new FileInputStream(STOCK_CSV_PATH));
    stockRepository.saveAll(stock);
  }
}
