package ru.xenya.market.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.xenya.market.backend.data.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByFullNameIgnoreCase(String fullName);

   // Page<Customer> findBy(Pageable pageable);

    List<Customer> findByFullNameContainingIgnoreCase(String fullNameLike);

    List<Customer> findByFullNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String fuulNameLike, String addressLike);
}
