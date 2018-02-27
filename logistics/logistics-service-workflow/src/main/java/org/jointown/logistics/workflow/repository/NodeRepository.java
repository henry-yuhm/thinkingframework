package org.jointown.logistics.workflow.repository;

import org.jointown.logistics.workflow.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends JpaRepository<Node, String> {
}