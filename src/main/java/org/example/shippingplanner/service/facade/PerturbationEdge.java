package org.example.shippingplanner.service.facade;

import org.example.shippingplanner.bean.Perturbation;

import java.util.List;

public interface PerturbationEdge {
    List<Perturbation> findByCode(String code);
    int deleteByCode(String code);
    // Trouver les perturbations d'une liaison spécifique
    List<Perturbation> findByEdgeCode(String code);
    int save(Perturbation perturbation);
    List<Perturbation> findAll();
    Perturbation update(String code,Perturbation perturbation);
}
