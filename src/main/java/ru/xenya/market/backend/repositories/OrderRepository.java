package ru.xenya.market.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.xenya.market.backend.data.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
