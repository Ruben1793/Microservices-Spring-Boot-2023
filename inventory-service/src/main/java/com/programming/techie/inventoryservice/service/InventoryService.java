package com.programming.techie.inventoryservice.service;

import com.programming.techie.inventoryservice.dto.InventoryResponse;
import com.programming.techie.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows // just for demo thread sleep
    public List<InventoryResponse> isInStock(List<String> skuCode){
        //log.info("Wait Started");
        //Thread.sleep(10000);
        //log.info("Wait Ended");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(i -> InventoryResponse.builder()
                        .skuCode(i.getSkuCode())
                        .isInStock(i.getQuantity()>0)
                        .build())
                .toList();
    }
}
