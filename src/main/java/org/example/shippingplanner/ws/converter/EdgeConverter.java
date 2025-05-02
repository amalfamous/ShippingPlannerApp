package org.example.shippingplanner.ws.converter;

import org.example.shippingplanner.bean.Edge;
import org.example.shippingplanner.ws.dto.EdgeDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EdgeConverter {
    public Edge toBean(EdgeDto dto){
        Edge bean=new Edge();
        BeanUtils.copyProperties(dto,bean);
        return bean;
    }
    public List<Edge> toBean(List<EdgeDto> dtos){
        return dtos.stream().map(this::toBean).toList();
    }
    public EdgeDto toDto(Edge bean){
        EdgeDto dto=new EdgeDto();
        BeanUtils.copyProperties(bean,dto);
        return dto;
    }
    public List<EdgeDto> toDto(List<Edge> beans){
        return beans.stream().map(this::toDto).toList();
    }
}
