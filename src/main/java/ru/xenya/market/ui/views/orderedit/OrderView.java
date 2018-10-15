package ru.xenya.market.ui.views.orderedit;


import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import ru.xenya.market.backend.data.entity.Customer;
import ru.xenya.market.backend.data.entity.Order;
import ru.xenya.market.backend.repositories.CustomerRepository;
import ru.xenya.market.ui.components.SearchBar;

@SpringComponent
@UIScope
public class OrderView extends Dialog {
    private SearchBar search;

    private Grid<Order> orderGrid;

    private CustomerRepository repository;

    private Customer currentItem;
    //todo как связать заказы и контрагента, чтобы отображались только заказы выбранного контрагента
    //binder.readBean(currentItem)см. AbstractDialog
    /*
    public final void open(T item, AbstractDialog.Operation operation) {
        currentItem = item;
        titleField.setText(operation.getNameInTitle() + " " + itemType);
        if (registrationForSave != null) {
            registrationForSave.remove();
        }
        registrationForSave = saveButton.addClickListener(e -> saveClicked(operation));
        binder.readBean(currentItem);

        deleteButton.setEnabled(operation.isDeleteEnabled());
        open();
    }
     */
}
