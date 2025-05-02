package org.example.shippingplanner.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.shippingplanner.bean.Edge;
import org.example.shippingplanner.bean.Node;
import org.example.shippingplanner.dao.EdgeDao;
import org.example.shippingplanner.dao.NodeDao;
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
        if (edge.getCode() == null || edge.getCode().isEmpty()) {
            edge.setCode(UUID.randomUUID().toString());
        }
        if (dao.findByCode(edge.getCode()) != null) {
            return -1;
        }
        if (edge.getSource() != null && edge.getSource().getId() != null) {
            Node src = nodeDao.findById(edge.getSource().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Node not found: " + edge.getSource().getId()));
            edge.setSource(src);
        }
        if (edge.getTarget() != null && edge.getTarget().getId() != null) {
            Node tgt = nodeDao.findById(edge.getTarget().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Node not found: " + edge.getTarget().getId()));
            edge.setTarget(tgt);
        }
        dao.save(edge);
        return 1;
    }

    @Override
    @Transactional
    public Edge update(String code, Edge edge) {
        Edge existing = dao.findByCode(code);
        if (existing == null) {
            throw new EntityNotFoundException("Edge not found with code: " + code);
        }
        if (edge.getDistanceKm() > 0) existing.setDistanceKm(edge.getDistanceKm());
        if (edge.getDurationHours() > 0) existing.setDurationHours(edge.getDurationHours());
        if (edge.getCost() > 0) existing.setCost(edge.getCost());
        if (edge.getTransportMode() != null) existing.setTransportMode(edge.getTransportMode());
        if (edge.getSource() != null && edge.getSource().getId() != null) {
            Node src = nodeDao.findById(edge.getSource().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Node not found: " + edge.getSource().getId()));
            existing.setSource(src);
        }
        if (edge.getTarget() != null && edge.getTarget().getId() != null) {
            Node tgt = nodeDao.findById(edge.getTarget().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Node not found: " + edge.getTarget().getId()));
            existing.setTarget(tgt);
        }
        return dao.save(existing);
    }

    @Override
    public List<Edge> findAll() {
        return dao.findAll();
    }

    @Override
    public Edge findByCode(String code) {
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
