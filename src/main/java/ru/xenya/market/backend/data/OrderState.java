package ru.xenya.market.backend.data;

import com.vaadin.flow.shared.util.SharedUtil;

public enum OrderState {
    NEW, CONFIRMED, READY, PROBLEM, CANCELED;

    public String getDisplayName(){
        return SharedUtil.capitalize(name().toLowerCase());
    }
}
