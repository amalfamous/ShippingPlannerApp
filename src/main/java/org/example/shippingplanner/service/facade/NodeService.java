package org.example.shippingplanner.service.facade;

import org.example.shippingplanner.bean.Node;

import java.util.List;

public interface NodeService {
    Node findByCode(String code);
    int deleteByCode(String code);
    List<Node> findByNodeType(String type);
    int save(Node node);
    List<Node> findAll();
    Node update(String code,Node node);
}
