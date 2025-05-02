package org.example.shippingplanner.dao;

import org.example.shippingplanner.bean.Perturbation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerturbationDao extends JpaRepository<Perturbation, Long> {
    Perturbation findByCode(String code);
    int deleteByCode(String code);
    // Trouver les perturbations d'une liaison spécifique
    List<Perturbation> findByEdgeCode(String code);

}
