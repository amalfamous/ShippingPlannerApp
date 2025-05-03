package org.example.shippingplanner.ws.facade;

import org.example.shippingplanner.bean.Edge;
import org.example.shippingplanner.service.facade.EdgeService;
import org.example.shippingplanner.ws.converter.EdgeConverter;
import org.example.shippingplanner.ws.dto.EdgeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/edges/")
@CrossOrigin("*")
public class EdgeProvided {

    @Autowired
    private EdgeService edgeService;
    @Autowired
    EdgeConverter converter;
    @PostMapping("")
    public int save(@RequestBody EdgeDto dto) {
        Edge bean=converter.toBean(dto);
        return edgeService.save(bean);
    }

    @PutMapping("code/{code}")
    public EdgeDto update(@PathVariable String code, @RequestBody EdgeDto dto) {
        Edge bean = converter.toBean(dto);
        Edge bean1=edgeService.update(code,bean);
        return converter.toDto(bean1);
    }
    @GetMapping("")
    public List<EdgeDto> findAll() {
        List<Edge> beans = edgeService.findAll();
        return converter.toDto(beans);
    }

    @GetMapping("/code/{code}")
    public EdgeDto findByCode(@PathVariable String code) {
        Edge bean = edgeService.findByCode(code);
        return converter.toDto(bean);
    }

    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return edgeService.deleteByCode(code);
    }

    @GetMapping("/Source/code/{code}")
    public List<EdgeDto> findBySourceCode(@PathVariable String code) {
        List<Edge> beans = edgeService.findBySourceCode(code);
        return converter.toDto(beans);
    }

    @GetMapping("/Target/{code}")
    public List<EdgeDto> findByTargetCode(@PathVariable String code) {
        List<Edge> beans = edgeService.findByTargetCode(code);
        return converter.toDto(beans);
    }
}
