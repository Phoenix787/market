package ru.xenya.market.ui.crud;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import ru.xenya.market.backend.data.entity.Customer;
import ru.xenya.market.backend.data.entity.Order;
import ru.xenya.market.backend.data.entity.User;
import ru.xenya.market.backend.service.OrderService;
import ru.xenya.market.ui.views.orderedit.OrdersViewOfCustomer;

import java.util.List;

//todo разобраться с вызовом формы при нажатии добавить на searchbar


@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderPresenter extends CrudEntityPresenter<Order> {

    private OrdersViewOfCustomer view;

    private final EntityPresenter<Order, OrdersViewOfCustomer> entityPresenter;
//    private final OrdersGridDataProvider dataProvider;
    private final User currentUser;
    private final OrderService orderService;
    private Customer currentCustomer;
    private Order currentOrder;

    public OrderPresenter(EntityPresenter<Order, OrdersViewOfCustomer> entityPresenter,
                          OrderService orderService, User currentUser) {
        super(orderService, currentUser);
        this.entityPresenter = entityPresenter;
//        this.dataProvider = dataProvider;
        this.orderService = orderService;
        //this.currentCustomer = currentCustomer;
        this.currentUser = currentUser;

    }

    public void setView(OrdersViewOfCustomer view) {
        this.view = view;
        //view.getSearchBar().addActionClickListener(e-> createNew());
        view.getGrid().setItems(updateList());
    }


    public void init(OrdersViewOfCustomer view) {
        this.entityPresenter.setView(view);
        this.view = view;
        view.getGrid().setItems(updateList());
        view.getOpenedOrderEditor().setCurrentUser(currentUser);
        view.getOpenedOrderEditor().addCancelListener(e -> cancel());
        view.getOpenedOrderEditor().addSaveListener(e -> save());


//        view.getOpenedOrderDetails().addSaveListenter(e -> save());
//        view.getOpenedOrderDetails().addCancelListener(e -> cancel());
//        view.getOpenedOrderDetails().addBackListener(e -> back());
//        view.getOpenedOrderDetails().addEditListener(e -> edit());
//        view.getOpenedOrderDetails().addCommentListener(e -> addComment(e.getMessage()));
    }


    public List<Order> updateList() {
        return orderService.findByCustomer(currentCustomer);
    }

    public List<Order> updateList(Customer currentCustomer, String filter) {
        if (filter != null && !filter.isEmpty()) {
            return orderService.findByCustomerAndDueDateOrOrderState(currentCustomer, filter, filter);
        } else {
            return orderService.findByCustomer(currentCustomer);
        }

    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
        orderService.setCurrentCustomer(currentCustomer);
        view.getForm().setCurrentCustomer(currentCustomer);
    }

    @Override
    public Order createNew() {
        orderService.setCurrentCustomer(currentCustomer);
        open(entityPresenter.createNew(), true);
        return orderService.createNew(currentUser);
    }

    private void open(Order order, boolean edit) {
        if (edit) {
            view.getForm().read(order, entityPresenter.isNew());
            view.getDialog().add(view.getForm());
            view.getDialog().open();
        }
    }

    public void save() {
//todo customer-order

       // Order order = view.getForm().getBinder().getBean();
      //  if (view.getForm().getBinder().getBean() == null)
     //   Notification.show("getBinder.getBean == null");
    //    order.setCustomer(currentCustomer);
      //  orderService.saveOrder(order);
      //  if (writeEntity()){
            super.save(e->{
                if (isNew()) {
                    getView().showCreatedNotification();
                    updateList();
                } else {
                    getView().showUpdateNotification();
                    updateList();
                }
                closeSilently();
            });
       // }
    }

    public void filter(String filter) {
        updateList(currentCustomer, filter);
    }

    @Override
    public void cancel() {
        //todo проверку на несохраненные данные
        view.getDialog().close();
    }

    public void delete(){
        super.delete(e->{
            getView().showDeleteNotification();
            updateList();
            closeSilently();
        });
    }
    //    public  void cancel(){
    //        cancel(this::closeSilently, getView()::openDialog);
//    }
//
    public void closeSilently() {
        close();
        getView().closeDialog();
    }
    //}


}

