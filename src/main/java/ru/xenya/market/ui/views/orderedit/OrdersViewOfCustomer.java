package ru.xenya.market.ui.views.orderedit;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import ru.xenya.market.backend.data.entity.Customer;
import ru.xenya.market.backend.data.entity.Order;
import ru.xenya.market.backend.data.entity.util.EntityUtil;
import ru.xenya.market.ui.MainView;
import ru.xenya.market.ui.components.SearchBar;
import ru.xenya.market.ui.crud.CrudEntityPresenter;
import ru.xenya.market.ui.crud.CrudView;
import ru.xenya.market.ui.crud.OrderPresenter;
import ru.xenya.market.ui.utils.MarketConst;
import ru.xenya.market.ui.utils.TemplateUtils;

@Tag("orders-view-of-customer")
@Route(value = MarketConst.PAGE_STOREFRONT, layout = MainView.class)
@HtmlImport("src/views/orderedit/orders-view-of-customer.html")
@SpringComponent
@UIScope
public class OrdersViewOfCustomer extends CrudView<Order, TemplateModel> {

    @Id("text")
    private TextField text;

    @Id("search")
    private SearchBar searchBar;

    @Id("grid")
    private Grid<Order> grid;

    //private Dialog dialog;
    private Customer currentCustomer;


//    private final CrudEntityPresenter<Order> presenter;

    private final OrderPresenter presenter;

//    private OrderEditor form;

    private final BeanValidationBinder<Order> binder = new BeanValidationBinder<>(Order.class);

    @Autowired
    public OrdersViewOfCustomer(OrderPresenter presenter, OrderEditor form) {
        super(EntityUtil.getName(Order.class), form);
        this.presenter = presenter;
      //  this.form = form;
        presenter.setView(this);
        presenter.init(form);



        setupGrid();
        setupEventListeners();
       // getSearchBar().addActionClickListener(e->presenter.createNewOrder());
        form.setBinder(binder);
    }


    public void open(Customer customer) {
        text.setValue(customer.getFullName());
        presenter.setCurrentCustomer(customer);
        grid.setItems(presenter.updateList());

    }

    private void setupGrid() {
      //  grid.setItems(presenter.updateList());
        grid.setHeight("100vh");
        grid.addColumn(Order::getId).setWidth("50px").setFlexGrow(0);
        grid.addColumn(Order::getDueDate).setWidth("250px").setHeader("Дата заказа").setFlexGrow(5);
        grid.addColumn(Order::getOrderState).setWidth("200px").setHeader("Статус").setFlexGrow(5);
        grid.addColumn(Order::getPayment).setWidth("250px").setFlexGrow(5);
//        grid.addColumn(new ComponentRenderer<>(this::createEditButton)).setFlexGrow(2);
//        grid.addColumn(new ComponentRenderer<>(this::openOrdersButton)).setFlexGrow(2);
    }



    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

//    @Override
//    protected CrudEntityPresenter<Order> getPresenter() {
//        return presenter;
//    }

    @Override
    protected OrderPresenter getPresenter() {
        return presenter;
    }


    @Override
    protected String getBasePage() {
        return MarketConst.PAGE_STOREFRONT;
    }

    @Override
    public BeanValidationBinder<Order> getBinder() {
        return binder;
    }

    @Override
    public SearchBar getSearchBar() {
        return searchBar;
    }

    @Override
    public Grid<Order> getGrid() {
        return grid;
    }

    @Override
    public OrderEditor getForm() {
        return (OrderEditor) super.getForm();
    }

    //    void setDialogElementsVisibility(boolean editing) {
//        dialog.add(editing ? orderEditor : orderDetails);
//        orderEditor.setVisible(editing);
//        orderDetails.setVisible(!editing);
//    }

    public void setupEventListeners() {
        getGrid().addSelectionListener(e->{
            e.getFirstSelectedItem().ifPresent(entity->{
                navigateToEntity(entity.getId().toString());
                getGrid().deselectAll();
            });
        });

     //   getForm().getButtons().addSaveListener(e -> getPresenter().save());
     //   getForm().getButtons().addCancelListener(e -> getPresenter().cancel());

        getDialog().getElement().addEventListener("opened-changed", e->{
            if (!getDialog().isOpened()) {
                getPresenter().cancel();
            }
        });



      //  getForm().getButtons().addDeleteListener(e -> getPresenter().delete());

        getSearchBar().addActionClickListener(e -> getPresenter().createNewOrder());
        getSearchBar().addFilterChangeListener(e->getPresenter().filter(getSearchBar().getFilter()));
        getSearchBar().setActionText("New " + EntityUtil.getName(Order.class));
     //   getBinder().addValueChangeListener(e -> getPresenter().onValueChange(isDirty()));
    }

    public void navigateToEntity(String id) {
        getUI().ifPresent(ui-> ui.navigate(TemplateUtils.generateLocation(getBasePage(), id)));
    }

    public OrderEditor getOpenedOrderEditor() {
        return getForm();
    }

    public void setOpened(boolean isOpened) {
        getDialog().setOpened(isOpened);
    }
    public void navigateToMainView() {
        getUI().ifPresent(ui -> ui.navigate(MarketConst.PAGE_CUSTOMERS));
    }
}
