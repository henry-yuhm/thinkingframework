package org.jointown.logistics.workflow.repository;

import org.jointown.logistics.workflow.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends JpaRepository<Line, Line.LinePk> {
}