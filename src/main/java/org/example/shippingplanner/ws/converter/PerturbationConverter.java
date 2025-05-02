package org.example.shippingplanner.ws.converter;

import org.example.shippingplanner.bean.Perturbation;
import org.example.shippingplanner.ws.dto.PerturbationDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PerturbationConverter {
    public Perturbation toBean(PerturbationDto dto){
        Perturbation bean=new Perturbation();
        BeanUtils.copyProperties(dto,bean);
        return bean;
    }
    public List<Perturbation> toBean(List<PerturbationDto> dtos){
        return dtos.stream().map(this::toBean).toList();
    }
    public PerturbationDto toDto(Perturbation bean){
        PerturbationDto dto=new PerturbationDto();
        BeanUtils.copyProperties(bean,dto);
        return dto;
    }
    public List<PerturbationDto> toDto(List<Perturbation> beans){
        return beans.stream().map(this::toDto).toList();
    }
}
