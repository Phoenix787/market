package ru.xenya.market.ui.views.orderedit;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;
import ru.xenya.market.backend.data.entity.Order;
import ru.xenya.market.ui.utils.converters.LocalDateToStringEncoder;
import ru.xenya.market.ui.utils.converters.LongToStringEncoder;
import ru.xenya.market.ui.events.CancelEvent;
import ru.xenya.market.ui.events.EditEvent;
import ru.xenya.market.ui.events.SaveEvent;
@Tag("order-details")
public class OrderDetails extends PolymerTemplate<OrderDetails.Model> {
    private Order order;

    private Button back;
    private Button cancel;
    private Button save;
    private Button edit;

    public OrderDetails() {
        save.addClickListener(e -> fireEvent(new SaveEvent(this, false)));
        cancel.addClickListener(e -> fireEvent(new CancelEvent(this, false)));
        edit.addClickListener(e -> fireEvent(new EditEvent(this)));
    }

    public void display(Order order, boolean review) {
        getModel().setReview(review);
        this.order = order;
        getModel().setItem(order);
    }

    public interface Model extends TemplateModel{
        @Include({"id", "dueDate.date", "orderState.name", "payment.name"})
        @Encode(value = LongToStringEncoder.class, path = "id")
        @Encode(value = LocalDateToStringEncoder.class, path = "dueDate")
        void setItem(Order order);

        void setReview(boolean review);
    }
}
