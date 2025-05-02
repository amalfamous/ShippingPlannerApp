package org.example.shippingplanner.ws.converter;


import org.example.shippingplanner.bean.RouteRequest;
import org.example.shippingplanner.bean.RouteResponse;

import org.example.shippingplanner.ws.dto.RouteRequestDto;
import org.example.shippingplanner.ws.dto.RouteResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class RoutePlanningConverter {

    public RouteRequest toBean(RouteRequestDto dto) {
        RouteRequest bean = new RouteRequest();
        BeanUtils.copyProperties(dto, bean);
        return bean;
    }

    public RouteResponseDto toDto(RouteResponse bean) {
        RouteResponseDto dto = new RouteResponseDto();
        BeanUtils.copyProperties(bean, dto);
        return dto;
    }
}

