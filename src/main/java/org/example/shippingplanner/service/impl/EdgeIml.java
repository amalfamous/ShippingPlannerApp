package org.example.shippingplanner.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.shippingplanner.bean.Edge;
import org.example.shippingplanner.bean.Node;
import org.example.shippingplanner.bean.Perturbation;
import org.example.shippingplanner.dao.EdgeDao;
import org.example.shippingplanner.dao.NodeDao;
import org.example.shippingplanner.dao.PerturbationDao;
import org.example.shippingplanner.service.facade.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EdgeIml implements EdgeService {
    @Autowired
    private EdgeDao dao;
    @Autowired
    private NodeDao nodeDao;

    @Override
    public int save(Edge edge) {
        // Générer un code si absent
        if (edge.getCode() == null || edge.getCode().isEmpty()) {
            edge.setCode(UUID.randomUUID().toString());
        }
        // Vérifier unicité du code
        if (!dao.findByCode(edge.getCode()).isEmpty()) {
            return -1; // Code déjà existant
        }

        // Charger la source et la cible depuis la BDD
        if (edge.getSource() != null && edge.getSource().getId() != null) {
            Node source = nodeDao.findById(edge.getSource().getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Node non trouvé pour l'id: " + edge.getSource().getId()));
            edge.setSource(source);
        }

        if (edge.getTarget() != null && edge.getTarget().getId() != null) {
            Node target = nodeDao.findById(edge.getTarget().getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Node non trouvé pour l'id: " + edge.getTarget().getId()));
            edge.setTarget(target);
        }
        dao.save(edge);
        return 1;
    }

    @Override
    @Transactional
    public Edge update(String code, Edge edge) {
        // Récupérer l'entité existante
        List<Edge> list = dao.findByCode(code);
        if (list.isEmpty()) {
            throw new EntityNotFoundException("Edge non trouvé avec le code " + code);
        }
        Edge existing = list.get(0);

        // Mettre à jour les champs s'ils sont fournis
        if (edge.getDistanceKm() > 0) {
            existing.setDistanceKm(edge.getDistanceKm());
        }
        if (edge.getDurationHours() > 0) {
            existing.setDurationHours(edge.getDurationHours());
        }
        if (edge.getCost() > 0) {
            existing.setCost(edge.getCost());
        }
        if (edge.getTransportMode() != null) {
            existing.setTransportMode(edge.getTransportMode());
        }
        if (edge.getSource() != null && edge.getSource().getId() != null) {
            Node source = nodeDao.findById(edge.getSource().getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Node non trouvé pour l'id: " + edge.getSource().getId()));
            existing.setSource(source);
        }
        if (edge.getTarget() != null && edge.getTarget().getId() != null) {
            Node target = nodeDao.findById(edge.getTarget().getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Node non trouvé pour l'id: " + edge.getTarget().getId()));
            existing.setTarget(target);
        }
        return dao.save(existing);
    }

    @Override
    public List<Edge> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Edge> findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public int deleteByCode(String code) {
        return dao.deleteByCode(code);
    }

    @Override
    public List<Edge> findBySourceCode(String code) {
        return dao.findBySourceCode(code);
    }

    @Override
    public List<Edge> findByTargetCode(String code) {
        return dao.findByTargetCode(code);
    }

}
