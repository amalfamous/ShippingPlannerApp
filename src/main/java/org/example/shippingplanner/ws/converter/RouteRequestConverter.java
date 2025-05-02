package org.example.shippingplanner.ws.converter;

import org.example.shippingplanner.bean.RouteRequest;
import org.example.shippingplanner.ws.dto.RouteRequestDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouteRequestConverter {
    public RouteRequest toBean(RouteRequestDto dto){
        RouteRequest bean=new RouteRequest();
        BeanUtils.copyProperties(dto,bean);
        return bean;
    }
    public List<RouteRequest> toBean(List<RouteRequestDto> dtos){
        return dtos.stream().map(this::toBean).toList();
    }
    public RouteRequestDto toDto(RouteRequest bean){
        RouteRequestDto dto=new RouteRequestDto();
        BeanUtils.copyProperties(bean,dto);
        return dto;
    }
    public List<RouteRequestDto> toDto(List<RouteRequest> beans){
        return beans.stream().map(this::toDto).toList();
    }
}
