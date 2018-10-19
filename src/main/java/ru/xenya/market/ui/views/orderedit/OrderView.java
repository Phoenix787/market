package ru.xenya.market.ui.views.orderedit;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import ru.xenya.market.backend.data.entity.Customer;
import ru.xenya.market.backend.data.entity.Order;
import ru.xenya.market.backend.repositories.CustomerRepository;
import ru.xenya.market.backend.repositories.OrderRepository;
import ru.xenya.market.ui.components.SearchBar;
import ru.xenya.market.ui.components.common.AbstractEditorDialog;
import ru.xenya.market.ui.components.common.ConfirmationDialog;
import ru.xenya.market.ui.views.EntityView;

import java.util.List;

@SpringComponent
@UIScope
public class OrderView extends Dialog implements EntityView<Order> {

    private  H2 title;
    private SearchBar search;

    private Grid<Order> orderGrid;

    private CustomerRepository repository;
    private OrderRepository orderRepository;

    private Customer currentItem;

    private OrderEditor form;

   // private final OrderDetails orderDetails = new OrderDetails();

    private Dialog dialog;

    private ConfirmationDialog<Order> confirmationDialog;



    public OrderView(CustomerRepository repository, OrderRepository orderRepository, OrderEditor orderEditor) {
        this.repository = repository;
        this.orderRepository = orderRepository;
        this.form = orderEditor;
        form.addReviewListener(e->save());
        form.addCancelListener(e->cancel());

        search = new SearchBar();
        dialog = new Dialog();
        dialog.getElement().addEventListener("opened-changed", e->{
            if (dialog.isOpened()) {
                close();
            }
        });

        dialog.add(form);

        setupTitle();
        setupSearchBar();
        setupGrid();

    }

    private void setupTitle() {
        title = new H2();
       // title.setText(currentItem.getFullName());
        //todo установить кнопку Редактировать, через которую вызывается форма редактирования контрагента
        //todo сделать стиль для заголовка
        add(title);
    }

    private void setupGrid() {
        this.orderGrid = new Grid<>();
      //  orderGrid.setHeight("100vh");
        orderGrid.addColumn(Order::getId).setWidth("50px").setFlexGrow(0);
        orderGrid.addColumn(Order::getDueDate).setWidth("100px").setHeader("Дата заказа").setFlexGrow(5);
        orderGrid.addColumn(Order::getPayment).setHeader("Форма оплаты").setWidth("100px").setFlexGrow(5);
        orderGrid.addColumn(Order::getOrderState).setHeader("Статус").setWidth("100px").setFlexGrow(5);
        orderGrid.addColumn(new ComponentRenderer<>(this::createEditOrderButton)).setFlexGrow(2);

        updateList();
        add(orderGrid);

    }

    private void setupSearchBar() {
        search.getFilterTextField().addValueChangeListener(e -> updateList());
        search.setActionText("Новый заказ");
        search.addActionClickListener(e->openForm(new Order(currentItem), AbstractEditorDialog.Operation.ADD));
        add(search);
    }

    private void openForm(Order order, AbstractEditorDialog.Operation operation) {

        form.getBinder().setBean(order);
        form.setCurrentOrder(order);
        dialog.open();
    }


    public final void open(Customer item/*, AbstractEditorDialog.Operation operation*/) {
        currentItem = item;
        title.setText(currentItem.getFullName());
        updateList();
//        if (registrationForSave != null) {
//            registrationForSave.remove();
//        }
//        registrationForSave = saveButton.addClickListener(e -> saveClicked(operation));
       // binder.readBean(currentItem);

      //  deleteButton.setEnabled(operation.isDeleteEnabled());
        open();
    }

    private void updateList() {
        List<Order> orders = orderRepository.findByCustomer(currentItem);
        orderGrid.setItems(orders);
    }

    private Button createEditOrderButton(Order order) {
        Button edit = new Button("Редактировать",
                event -> dialog.open());
        edit.setIcon(new Icon("lumo", "edit"));
        edit.addClassName("order__edit");
        edit.getElement().setAttribute("theme", "tertiary");
        return edit;
    }

    private void saveUpdate(Order order, AbstractEditorDialog.Operation operation) {
        orderRepository.save(order);
        updateList();
        Notification.show("Заказ успешно " + operation.getNameInText(),
                3000, Notification.Position.BOTTOM_START);
    }

    private void save() {



    }

    private void cancel() {
        form.clear();
        form.close();
        setOpened(true);
    }

    private void deleteUpdate(Order order) {
        orderRepository.delete(order);
        updateList();
        Notification.show("Заказ успешно удалён.",
                3000, Notification.Position.BOTTOM_START);
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void write(Order entity) throws ValidationException {

    }

    @Override
    public String getEntityName() {
        return null;
    }

    @Override
    public void setConfirmDialog(ConfirmationDialog confirmDialog) {


    }

    @Override
    public ConfirmationDialog getConfirmDialog() {
        return null;
    }


//todo сделать форму для создания и редактирования заказов

    //todo как связать заказы и контрагента, чтобы отображались только заказы выбранного контрагента

}
