package net.kdigital.web_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.web_project.entity.CustomerEntity;
import net.kdigital.web_project.entity.CustomerItemEntity;

public interface CustomerItemRepository extends JpaRepository<CustomerItemEntity, Long> {

	CustomerItemEntity findByCustomerEntity(CustomerEntity customerEntity);
}
