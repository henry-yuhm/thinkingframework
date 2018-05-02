package org.thinking.logistics.statemachine.dispatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.statemachine.dispatcher.domain.Node;

@Repository
public interface NodeRepository extends JpaRepository<Node, String> {
}