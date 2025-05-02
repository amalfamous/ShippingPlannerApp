package org.example.shippingplanner.ws.converter;

import org.example.shippingplanner.bean.RouteResponse;
import org.example.shippingplanner.ws.dto.RouteResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouteResponseConverter {
    public RouteResponse toBean(RouteResponseDto dto){
        RouteResponse bean=new RouteResponse();
        BeanUtils.copyProperties(dto,bean);
        return bean;
    }
    public List<RouteResponse> toBean(List<RouteResponseDto> dtos){
        return dtos.stream().map(this::toBean).toList();
    }
    public RouteResponseDto toDto(RouteResponse bean){
        RouteResponseDto dto=new RouteResponseDto();
        BeanUtils.copyProperties(bean,dto);
        return dto;
    }
    public List<RouteResponseDto> toDto(List<RouteResponse> beans){
        return beans.stream().map(this::toDto).toList();
    }
}
