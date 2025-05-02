package org.example.shippingplanner.ws.converter;

import org.example.shippingplanner.bean.Node;
import org.example.shippingplanner.ws.dto.NodeDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NodeConverter {
    public Node toBean(NodeDto dto){
        Node bean=new Node();
        BeanUtils.copyProperties(dto,bean);
        return bean;
    }
    public List<Node> toBean(List<NodeDto> dtos){
        return dtos.stream().map(this::toBean).toList();
    }
    public NodeDto toDto(Node bean){
        NodeDto dto=new NodeDto();
        BeanUtils.copyProperties(bean,dto);
        return dto;
    }
    public List<NodeDto> toDto(List<Node> beans){
        return beans.stream().map(this::toDto).toList();
    }
}
