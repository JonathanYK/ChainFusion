package com.jonathanyk.InventoryService.service;

import com.jonathanyk.InventoryService.model.Inventory;
import com.jonathanyk.InventoryService.repository.InventoryRepository;
import com.jonathanyk.chainCommons.inventoryCommons.InventoryStockResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

    @Transactional(readOnly = true)
    public List<InventoryStockResponse> isProductListInStock(List<String> skuCodes) {

        List<InventoryStockResponse> inventoryStockResponses = new ArrayList<>();
        HashMap<String, Integer> foundInventoryMap = new HashMap<>();
        for (Inventory inventory : inventoryRepository.findBySkuCodeIn(skuCodes)) {
            foundInventoryMap.put(inventory.getSkuCode(), inventory.getQuantity());
        }

        // iterate over retrieved skuCodes, and for ones that exists, check if they in stock
        for (String orderSkuCode : skuCodes) {
            if (foundInventoryMap.containsKey(orderSkuCode)) {
                inventoryStockResponses.add(InventoryStockResponse.builder()
                        .skuCode(orderSkuCode)
                        .isInStock(foundInventoryMap.get(orderSkuCode) > 0)
                        .build());
            } else {
                log.error("sku: " + orderSkuCode + " not exists!");
            }
        }
        return inventoryStockResponses;
    }

}
