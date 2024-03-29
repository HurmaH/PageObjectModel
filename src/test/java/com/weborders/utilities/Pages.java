package com.weborders.utilities;

import com.weborders.pages.*;

public class Pages {
    //private variables for each pages
    private LoginPage loginPage;
    private ViewAllOrdersPage viewAllOrdersPage;
    private ViewAllProductsPage viewAllProductsPage;
    private OrderPage orderPage;

    //getter methods of private variables of pages
    public LoginPage login() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public ViewAllOrdersPage viewAllOrders() {
        if (viewAllOrdersPage == null) {
            viewAllOrdersPage = new ViewAllOrdersPage();
        }
        return viewAllOrdersPage;
    }

    public ViewAllProductsPage viewAllProducts() {
        if (viewAllProductsPage == null) {
            viewAllProductsPage = new ViewAllProductsPage();
        }
        return viewAllProductsPage;
    }

    public OrderPage order() {
        if (orderPage == null) {
            orderPage = new OrderPage();
        }
        return orderPage;
    }
}
