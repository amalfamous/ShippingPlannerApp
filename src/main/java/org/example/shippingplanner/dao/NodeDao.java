package org.example.shippingplanner.dao;

import org.example.shippingplanner.bean.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeDao extends JpaRepository<Node, Long> {
    List<Node> findByCode(String code);
    int deleteByCode(String code);
    List<Node> findByNodeType(String type);
}
