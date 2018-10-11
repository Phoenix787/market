package ru.xenya.market.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.xenya.market.backend.data.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFullNameStartsWithIgnoreCase(String fullName);
}
