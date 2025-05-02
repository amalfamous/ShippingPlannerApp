package org.example.shippingplanner.service.facade;

import org.example.shippingplanner.bean.Edge;

import java.util.List;

public interface EdgeService {
    Edge findByCode(String code);
    int deleteByCode(String code);
    List<Edge> findBySourceCode(String code);
    List<Edge> findByTargetCode(String code);
    int save(Edge edge);
    Edge update(String code,Edge edge);
    List<Edge> findAll();


}
