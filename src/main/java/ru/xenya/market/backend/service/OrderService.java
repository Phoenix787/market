package ru.xenya.market.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.xenya.market.backend.data.OrderState;
import ru.xenya.market.backend.data.entity.Customer;
import ru.xenya.market.backend.data.entity.Order;
import ru.xenya.market.backend.data.entity.User;
import ru.xenya.market.backend.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.function.BiConsumer;

@Service
public class OrderService implements FilterableCrudService<Order> {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        super();
        this.orderRepository = orderRepository;
    }

    private static final Set<OrderState> notAvailableState = Collections.unmodifiableSet(
            EnumSet.complementOf(EnumSet.of(OrderState.READY, OrderState.CONFIRMED, OrderState.CANCELED))
    );

    @Transactional (rollbackOn = Exception.class)
    public Order saveOrder(User currentUser, Long id, BiConsumer<User, Order> orderFiller) {
        Order order;
        if (id == null) {
            order = new Order(currentUser);
        } else {
            order = load(id);
        }
        orderFiller.accept(currentUser, order);
        return orderRepository.save(order);
    }

    @Transactional(rollbackOn = Exception.class)
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Page<Order> findAnyMatchingAfterDueDate(Optional<String> optionalFilter,
                                                   Optional<LocalDate> optionalFilterDate,
                                                   Pageable pageable) {
        if (optionalFilter.isPresent() && !optionalFilter.get().isEmpty()) {
            if (optionalFilterDate.isPresent()) {
                return orderRepository.findByCustomerFullNameContainingIgnoreCaseAndDueDateAfter(
                        optionalFilter.get(), optionalFilterDate.get(), pageable);

            }else{
                return orderRepository.findByCustomerFullNameContainingIgnoreCase(optionalFilter.get(), pageable);
            }
        } else {
            if (optionalFilterDate.isPresent()) {
                return orderRepository.findByDueDateAfter(optionalFilterDate.get(), pageable);
            } else {
                return orderRepository.findAll(pageable);
            }
        }
    }

    @Override
    public OrderRepository getRepository() {
        return orderRepository;
    }

    @Override
    @Transactional
    public Order createNew(User currentUser) {
        Order order = new Order(currentUser);
        order.setDueDate(LocalDate.now());
        return order;
    }

    @Override
    public Page<Order> findAnyMatching(Optional<String> filter, Pageable pageable) {
        if (filter.isPresent()){
            String repositoryFilter = "%" + filter.get() + "%";
            return getRepository().findByCustomerFullNameContainingIgnoreCase(
                            repositoryFilter, pageable
                    );
        } else {
            return find(pageable);
        }
    }

    private Page<Order> find(Pageable pageable) {
                   return orderRepository.findAll(pageable);
    }

    @Override
    public long countAnyMatching(Optional<String> filter) {
        return 0;
    }

    public List<Order> findByCustomer(Customer currentCustomer) {
        return orderRepository.findByCustomer(currentCustomer);
    }
}
