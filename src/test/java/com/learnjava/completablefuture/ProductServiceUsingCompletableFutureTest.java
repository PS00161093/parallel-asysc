package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceUsingCompletableFutureTest {

    private final ProductInfoService productInfoService = new ProductInfoService();
    private final ReviewService reviewService = new ReviewService();

    ProductServiceUsingCompletableFuture pscf = new ProductServiceUsingCompletableFuture(productInfoService, reviewService);

    @Test
    void retrieveProductDetails() {
        Product product = pscf.retrieveProductDetails("ABC123");
        assertNotNull(product);
        assertFalse(product.getProductInfo().getProductOptions().isEmpty());
        assertNotNull(product.getReview());
    }
}