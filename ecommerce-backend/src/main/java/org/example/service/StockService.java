package org.example.service;



import org.example.dto.StockDto;
import org.example.entity.Stock;

import java.util.List;

public interface StockService {

    Boolean addStock(StockDto stockDto);

    public StockDto updateStock(Long id, StockDto stockDto);
    Boolean deleteStock(Long id);

    List<StockDto> listStock(Long id);

}