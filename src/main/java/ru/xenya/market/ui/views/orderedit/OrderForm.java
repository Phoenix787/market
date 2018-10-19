package ru.xenya.market.ui.views.orderedit;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import ru.xenya.market.backend.data.OrderState;
import ru.xenya.market.backend.data.entity.Order;
import ru.xenya.market.backend.data.entity.Payment;
import ru.xenya.market.backend.data.entity.User;
import ru.xenya.market.ui.components.common.AbstractEditorDialog;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@SpringComponent
@UIScope
public class OrderForm extends AbstractEditorDialog<Order> {


    private H2 title;

    private Div metaContainer;
    private Span orderNumber;
    private ComboBox<OrderState> status;
    @Id("dueDate")
    private DatePicker dueDate;
    @Id("payment")
    private ComboBox<Payment> payment;
    @Id("customerName")
    private TextField customerName;
    @Id("customerPhone")
    private TextField customerPhone;
    @Id("cancel")
    private Button cancel;
    @Id("review")
    private Button review;
    @Id("itemsContainer")
    private Div itemsContainer;

//    private OrderItemsEditor itemsEditor;
    private User currentUser;

    private Order currentOrder;

    public OrderForm(BiConsumer<Order, Operation> itemSaver, Consumer<Order> itemDeleter) {
        super("Заказ", itemSaver, itemDeleter);
    }




    @Override
    protected void confirmDelete() {

    }
}
