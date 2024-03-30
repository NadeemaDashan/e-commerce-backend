package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ProductDto;
import org.example.dto.StockDto;
import org.example.entity.Product;
import org.example.entity.Stock;
import org.example.repository.StockRepository;
import org.example.service.ProductService;
import org.example.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class  StockServiceImpl implements StockService {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductService productService;

    @Override
    public Boolean addStock(StockDto stockDto) {
        ProductDto product =productService.getProductByName(stockDto.getProduct().getName());
        Stock stock=mapper.convertValue(stockDto,Stock.class);
        stock.setProduct(Product.builder().id(product.getId()).name(product.getName()).build());
        Stock saved = stockRepository.save(stock);
        return saved.getId() != null;
    }

    @Override
    public StockDto updateStock(Long id, StockDto stockDto) {
            Optional<Stock> stockRepositoryById=stockRepository.findById(id);
            if (stockRepositoryById.isPresent()) {
                Stock stock=stockRepositoryById.get();
                stockRepository.deleteById(stock.getId());
                addStock(stockDto);
            }
            return null;
    }

    @Override
    public Boolean deleteStock(Long id) {
        if (stockRepository.existsById(id)){
            stockRepository.deleteById(id);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public List<StockDto> listStock(Long id) {
        Optional<Stock> stockOptional = stockRepository.findById(id);

        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();
            Long productId=stock.getProduct().getId();
            ProductDto productDto=productService.getProductById(productId);
            StockDto convertedStock=convertStockToDTO(stock);
            convertedStock.setProduct(Product.builder().id(productDto.getId()).name(productDto.getName()).build());
            List<StockDto> stockDTOList = Collections.singletonList(convertedStock);
            return stockDTOList;
        } else {

            return Collections.emptyList();
        }
    }

    @Override
    public StockDto getStockById(long id){
        Optional<Stock> stockOptional = stockRepository.findById(id);

        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();
            Long productId = stock.getProduct().getId();
            ProductDto productDto = productService.getProductById(productId);
            StockDto stockDto=mapper.convertValue(stock,StockDto.class);
            stockDto.setProduct(Product.builder().id(productDto.getId()).name(productDto.getName()).build());
            return stockDto;
        }
        return null;
    }


    private StockDto convertStockToDTO(Stock stock) {
        StockDto stockDTO = new StockDto();
        stockDTO.setId(stock.getId());
        stockDTO.setColor(stock.getColor());
        stockDTO.setSize(stock.getSize());
        stockDTO.setPrice(Double.valueOf(stock.getPrice()));
        stockDTO.setQty(Integer.parseInt(stock.getQty()));
        return stockDTO;
    }

}