package ru.xenya.market.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.xenya.market.backend.data.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByDateAfter(LocalDate filterDate);
}
