package ru.xenya.market.ui.views.admin.prices;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.internal.AbstractFieldSupport;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.shared.Registration;
import ru.xenya.market.backend.data.entity.ItemPrice;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PriceItemsEditor extends Div
        implements HasValueAndElement<AbstractField.ComponentValueChangeEvent<PriceItemsEditor,List<ItemPrice>>, List<ItemPrice>> {

    //todo PriceItemEditor
 //   private PriceItemEditor empty;

    private boolean hasChanges = false;

    private final AbstractFieldSupport<PriceItemsEditor, List<ItemPrice>> fieldSupport;

    public PriceItemsEditor() {

        this.fieldSupport = new AbstractFieldSupport<>(this, Collections.emptyList(),
                Objects::equals, c -> {
        });
    }

    //todo uncomment
//    public Stream<HasValue<?,?>> validate() {
//        return getChildren()
//                .filter((component -> fieldSupport.getValue().size() == 0 || !component.equals(empty)))
//                .map(editor -> ((PriceItemEditor) editor).validate()).flatMap(stream -> stream);
//
//    }

    @Override
    public Element getElement() {
        return null;
    }

    @Override
    public void setValue(List<ItemPrice> value) {

    }

    @Override
    public List<ItemPrice> getValue() {
        return null;
    }

    @Override
    public Registration addValueChangeListener(ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<PriceItemsEditor, List<ItemPrice>>> listener) {
        return null;
    }
}
