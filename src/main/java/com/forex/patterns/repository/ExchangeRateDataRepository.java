package com.forex.patterns.repository;

import com.forex.patterns.model.ExchangeRateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateDataRepository extends JpaRepository<ExchangeRateData, Long>{

    ExchangeRateData findByTimestamp(String timestamp);

}
