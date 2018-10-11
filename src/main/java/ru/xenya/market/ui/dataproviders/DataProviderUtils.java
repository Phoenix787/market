package ru.xenya.market.ui.dataproviders;

import com.vaadin.flow.component.ItemLabelGenerator;

import java.util.function.Function;
import java.util.function.Supplier;

public class DataProviderUtils {

    public static <S, T> T convertIfNotNull(S source, Function<S, T> converter, Supplier<T> nullValueSupplier) {
        return source != null ? converter.apply(source) : nullValueSupplier.get();
    }

    public static <S, T> T convertIfNotNull(S source, Function<S, T> converter) {
        return convertIfNotNull(source, converter, () -> null);
    }

    public static <T> ItemLabelGenerator<T> createItemLabelGenerator(Function<T, String> converter) {
        return item -> convertIfNotNull(item, converter, () -> "");
    }
}
