package pl.sda.carrental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sda.carrental.model.entity.Address;

import java.util.Collection;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Override
    List<Address> findAll();
    @Query("Select a from Address a where a.address_id not in (select address.address_id from Division)")
    List<Address> findAllUnusedAddresses();
}
