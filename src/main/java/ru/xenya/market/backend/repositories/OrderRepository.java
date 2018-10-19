package ru.xenya.market.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.xenya.market.backend.data.entity.Customer;
import ru.xenya.market.backend.data.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByDueDateAfter(LocalDate filterDate);
    Page<Order> findByDueDateAfter(LocalDate filterDate, Pageable pageable);

    List<Order> findByCustomer(Customer customer);

    Page<Order> findByCustomerFullNameContainingIgnoreCaseAndDueDateAfter(String searchQuery, LocalDate dueDate, Pageable pageable);


    Page<Order> findByCustomerFullNameContainingIgnoreCase(String s, Pageable pageable);
}
