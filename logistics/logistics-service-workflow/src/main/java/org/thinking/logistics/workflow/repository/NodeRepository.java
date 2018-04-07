package org.thinking.logistics.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.workflow.entity.Node;

@Repository
public interface NodeRepository extends JpaRepository<Node, String> {
}