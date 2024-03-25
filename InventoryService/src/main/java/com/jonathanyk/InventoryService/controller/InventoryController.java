package com.jonathanyk.InventoryService.controller;

import com.jonathanyk.InventoryService.service.InventoryService;
import com.jonathanyk.chainCommons.inventoryCommons.InventoryStockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable String skuCode) {
        return inventoryService.isInStock(skuCode);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryStockResponse> isProductsListInStock(@RequestParam List<String> skuCodes) {
        return inventoryService.isProductListInStock(skuCodes);
    }
}
