package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureExceptionallyTest {

    @InjectMocks
    ProductServiceUsingCompletableFuture pscmf;

    @Mock
    private ProductInfoService pisMock;

    @Mock
    private ReviewService rsMock;

    @Mock
    private InventoryService isMock;

    @Test
    void retrieveProductDetailsWithInventory_1_SuccessFlow() {

        String productId = "productId";

        when(pisMock.retrieveProductInfo(productId)).thenCallRealMethod();
        when(rsMock.retrieveReviews(productId)).thenThrow(new RuntimeException("Exception Occurred"));
        when(isMock.retrieveInventory(any())).thenCallRealMethod();

        Product product = pscmf.retrieveProductDetailsWithInventory_1(productId);

        assertNotNull(product);
        assertFalse(product.getProductInfo().getProductOptions().isEmpty());
        assertNotNull(product.getReview());
        product.getProductInfo().getProductOptions().forEach(productOption -> assertNotNull(productOption.getInventory()));
        assertEquals(0, product.getReview().getNoOfReviews());
    }

}