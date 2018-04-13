package org.thinking.logistics.core.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.core.entity.Parameter;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    Parameter findByNumber(String number);
}