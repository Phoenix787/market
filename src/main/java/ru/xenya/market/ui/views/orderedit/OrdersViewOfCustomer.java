package ru.xenya.market.ui.views.orderedit;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.templatemodel.TemplateModel;
import ru.xenya.market.backend.data.entity.Order;
import ru.xenya.market.backend.data.entity.util.EntityUtil;
import ru.xenya.market.ui.components.SearchBar;
import ru.xenya.market.ui.components.common.ConfirmationDialog;
import ru.xenya.market.ui.crud.CrudEntityPresenter;
import ru.xenya.market.ui.crud.CrudView;

public class OrdersViewOfCustomer extends CrudView<Order, TemplateModel> {
    private SearchBar searchBar;
    private Grid<Order> grid;
    private Dialog dialog;

    private final CrudEntityPresenter<Order> presenter;

    public OrdersViewOfCustomer(CrudEntityPresenter<Order> presenter, OrderEditor form) {
        super(EntityUtil.getName(Order.class), form);
        this.presenter = presenter;
    }

    @Override
    protected CrudEntityPresenter<Order> getPresenter() {
        return null;
    }

    @Override
    protected String getBasePage() {
        return null;
    }

    @Override
    protected BeanValidationBinder<Order> getBinder() {
        return null;
    }

    @Override
    protected SearchBar getSearchBar() {
        return null;
    }

    @Override
    protected Grid<Order> getGrid() {
        return null;
    }


}
