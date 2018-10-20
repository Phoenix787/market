package ru.xenya.market.ui.crud;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.xenya.market.backend.data.entity.Customer;
import ru.xenya.market.backend.data.entity.Order;
import ru.xenya.market.backend.data.entity.User;
import ru.xenya.market.backend.service.CustomerService;
import ru.xenya.market.backend.service.OrderService;
import ru.xenya.market.backend.service.UserService;

@Configuration
public class PresenterFactory {

    @Bean
    public User getUser() {
        return new User();
    }

    @Bean
    public Customer getCustomer() {
        return new Customer();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CrudEntityPresenter<User> userPresenter(UserService crudService, User currentUser) {
        return new CrudEntityPresenter<>(crudService, currentUser);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CrudEntityPresenter<Customer> customerPresenter(CustomerService crudService, User currentUser) {
        return new CrudEntityPresenter<>(crudService, currentUser);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CrudEntityPresenter<Order> orderPresenter(OrderService crudService, User currentUser) {
        return new CrudEntityPresenter<>(crudService, currentUser);
    }
}
