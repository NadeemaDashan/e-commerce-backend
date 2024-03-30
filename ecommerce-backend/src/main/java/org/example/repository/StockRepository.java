package org.example.repository;

import org.example.entity.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface StockRepository extends CrudRepository<Stock,Long> {


}
