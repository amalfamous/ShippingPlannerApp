package org.example.shippingplanner.dao;

import org.example.shippingplanner.bean.Edge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdgeDao extends JpaRepository<Edge, Long> {
    Edge findByCode(String code);
    int deleteByCode(String code);
    // Pour récupérer toutes les liaisons sortantes depuis un noeud donné
    List<Edge> findBySourceCode(String code);

    // Pour récupérer toutes les liaisons entrantes vers un noeud donné
    List<Edge> findByTargetCode(String code);

}
