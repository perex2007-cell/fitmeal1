/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fitmeal.view;


import com.fitmeal.service.AuthService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

/**
 *
 * @author Yesid Ocampo
 */

public abstract class ProtectedView extends VerticalLayout
        implements BeforeEnterObserver {

    protected AuthService authService = AuthService.getInstance();

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

        if (!authService.isUserLoggedIn()) {
            event.forwardTo("login");
        }
    }
}

