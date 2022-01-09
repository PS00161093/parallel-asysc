package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    @Test
    void testCheckOutSuccess() {
        PriceValidatorService priceValidatorService = new PriceValidatorService();
        CheckoutService checkoutService = new CheckoutService(priceValidatorService);
        Cart cart = DataSet.createCart(6);
        CheckoutResponse checkoutResponse = checkoutService.checkOut(cart);

        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void testCheckOutFailure() {
        PriceValidatorService priceValidatorService = new PriceValidatorService();
        CheckoutService checkoutService = new CheckoutService(priceValidatorService);
        Cart cart = DataSet.createCart(25);
        CheckoutResponse checkoutResponse = checkoutService.checkOut(cart);

        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void numberOfCores() {
        System.out.println("No of cores: " + Runtime.getRuntime().availableProcessors());
    }
}