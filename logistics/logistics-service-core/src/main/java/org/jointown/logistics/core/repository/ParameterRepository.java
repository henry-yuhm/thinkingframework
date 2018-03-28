package org.jointown.logistics.core.repository;
import org.jointown.logistics.core.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    Parameter findByNo(String no);
}