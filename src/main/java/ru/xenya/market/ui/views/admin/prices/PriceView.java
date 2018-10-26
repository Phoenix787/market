package ru.xenya.market.ui.views.admin.prices;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.templatemodel.TemplateModel;
import ru.xenya.market.backend.data.entity.Price;
import ru.xenya.market.ui.components.SearchBar;
import ru.xenya.market.ui.crud.CrudEntityPresenter;
import ru.xenya.market.ui.crud.CrudView;

//todo типа списка заказов одного клиента сделать
public class PriceView extends CrudView<Price, TemplateModel> {

    public PriceView(String entityName, CrudForm<Price> form) {
        super(entityName, form);
    }

    @Override
    protected CrudEntityPresenter<Price> getPresenter() {
        return null;
    }

    @Override
    protected String getBasePage() {
        return null;
    }

    @Override
    protected BeanValidationBinder<Price> getBinder() {
        return null;
    }

    @Override
    protected SearchBar getSearchBar() {
        return null;
    }

    @Override
    protected Grid<Price> getGrid() {
        return null;
    }
}
