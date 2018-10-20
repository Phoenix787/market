package ru.xenya.market.ui.crud;

import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import ru.xenya.market.backend.data.entity.Customer;
import ru.xenya.market.backend.data.entity.Order;
import ru.xenya.market.backend.data.entity.User;
import ru.xenya.market.backend.service.OrderService;
import ru.xenya.market.ui.dataproviders.OrdersGridDataProvider;
import ru.xenya.market.ui.views.customers.CustomerViewTemplate;
import ru.xenya.market.ui.views.orderedit.OrdersViewOfCustomer;

import javax.validation.OverridesAttribute;
import java.util.List;

//todo разобраться с вызовом формы при нажатии добавить на searchbar


@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderPresenter extends CrudEntityPresenter<Order> {

    private OrdersViewOfCustomer view;

//    private final EntityPresenter<Order, CustomerViewTemplate> entityPresenter;
//    private final OrdersGridDataProvider dataProvider;
    private final User currentUser;
    private final OrderService orderService;
    private Customer currentCustomer;

    public OrderPresenter(OrderService orderService, User currentUser) {
        super(orderService, currentUser);
//        this.dataProvider = dataProvider;
        this.orderService = orderService;
        //this.currentCustomer = currentCustomer;
        this.currentUser = currentUser;

    }

    public void setView(OrdersViewOfCustomer view) {
        this.view = view;
        view.getSearchBar().addActionClickListener(e->createNew());
        view.getGrid().setItems(updateList());
    }


//todo подумать насчет фильтра через Optional<String>
    public List<Order> updateList() {
        return orderService.findByCustomer(currentCustomer);
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public Order createNew() {

        return orderService.createNew(currentUser);
    }

    //}
}
