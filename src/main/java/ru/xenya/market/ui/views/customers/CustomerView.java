package ru.xenya.market.ui.views.customers;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ru.xenya.market.backend.data.Customer;
import ru.xenya.market.backend.repositories.CustomerRepository;
import ru.xenya.market.backend.service.CrudService;
import ru.xenya.market.ui.MainView;
import ru.xenya.market.ui.components.common.AbstractEditorDialog;
import ru.xenya.market.ui.utils.MarketConst;
import ru.xenya.market.ui.utils.TemplateUtils;


import java.util.List;

import static ru.xenya.market.ui.utils.MarketConst.PAGE_CUSTOMERS;

@Route(value = PAGE_CUSTOMERS, layout = MainView.class)
@PageTitle(MarketConst.TITLE_CUSTOMERS)
public class CustomerView extends VerticalLayout {

//    @Id("search")
//    private SearchBar search;

    private CustomerRepository repository;

    @Id("grid")
    private Grid<Customer> grid;

    @Id("newCustomer")
    private Button addCustomer = new Button();


    private final CustomerForm form;


    // private final BeanValidationBinder<Customer> binder = new BeanValidationBinder<>(Customer.class);

    public CustomerView(CustomerRepository repository) {
        this.repository = repository;
        addCustomer.setText("Новый контрагент");
        addCustomer.addClickListener(e -> openForm(new Customer(), AbstractEditorDialog.Operation.ADD));
        this.form = new CustomerForm(this::saveUpdate, this::deleteUpdate);
      //  this.form.setBinder(binder);
        setupButtonBar();
        setupGrid();
        setupEventListeners();

    }

    private void setupButtonBar() {
        HorizontalLayout buttonBar = new HorizontalLayout(addCustomer);
        add(buttonBar);
    }

    private void openForm(Customer customer, AbstractEditorDialog.Operation operation) {
        if (form.getElement().getParent() == null) {
            getUI().ifPresent(ui->ui.add(form));
        }
        form.open(customer, operation);
    }

    public CustomerRepository getRepository() {
        return repository;
    }

    private void saveUpdate(Customer customer, AbstractEditorDialog.Operation operation) {
        repository.save(customer);
        updateList();
        Notification.show("Контрагент успешно " + operation.getNameInText(), 3000, Notification.Position.BOTTOM_START);
    }

    private void deleteUpdate(Customer customer){
        repository.delete(customer);
        updateList();
        Notification.show("Контрагент успешно удален. ", 3000, Notification.Position.BOTTOM_START);
    }

    private void updateList() {
        List<Customer> customers = repository.findAll();
        grid.setItems(customers);
    }

    private void setupEventListeners() {
        grid.addSelectionListener(e->{
            e.getFirstSelectedItem().ifPresent(entity ->{
              //  navigateToEntity(entity.getId().toString());
                navigateToEntity(entity);
                grid.deselectAll();
            });
        });

    }

    private void navigateToEntity(String id) {
       // getUI().ifPresent(ui -> ui.navigate(TemplateUtils.generateLocation(getBasePage(), id)));

    }
    private void navigateToEntity(Customer customer) {
        openForm(customer, AbstractEditorDialog.Operation.EDIT);
    }

    private void setupGrid() {
        this.grid = new Grid<>(Customer.class);
        grid.setHeight("100vh");
        grid.setColumns("id", "fullName", "address", "phoneNumbers");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        updateList();
        add(grid);

    }

    public Grid<Customer> getGrid(){
        return grid;
    }

    public String getBasePage(){
        return PAGE_CUSTOMERS;
    }
}
