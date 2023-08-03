package com.programming.techie.inventoryservice.service;

import com.programming.techie.inventoryservice.dto.InventoryResponse;
import com.programming.techie.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(i -> InventoryResponse.builder()
                        .skuCode(i.getSkuCode())
                        .isInStock(i.getQuantity()>0)
                        .build())
                .toList();
    }
}
