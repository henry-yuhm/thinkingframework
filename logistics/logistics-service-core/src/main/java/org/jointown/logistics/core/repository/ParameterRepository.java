package org.jointown.logistics.core.repository;
import org.jointown.logistics.core.entity.parameter.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository<S extends Parameter> extends JpaRepository<S, Long> {
    S findByNumber(String number);
}