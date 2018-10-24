package ru.xenya.market;

import ru.xenya.market.backend.data.OrderState;
import ru.xenya.market.ui.utils.converters.OrderStateConverter;

public class Temp {
    public static void main(String[] args) {
        OrderState[] states = OrderState.values();
        for (OrderState state : states) {
            System.out.println(state);
        }

//        OrderState state = OrderState.NEW;
//
//        if ("Новый".equals(state.toString())){
//            System.out.println("одинаково");
//        } else
//            System.out.println(state.name());

        OrderStateConverter stateConverter = new OrderStateConverter();

        System.out.println(stateConverter.encode(OrderState.NEW));
    }
}
