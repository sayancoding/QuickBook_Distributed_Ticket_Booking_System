package com.example.event_service.service.impl;

import com.example.event_service.dao.InstanceDefaultPriceDao;
import com.example.event_service.dto.InstanceDefaultPriceDto;
import com.example.event_service.model.InstanceDefaultPrice;
import com.example.event_service.service.InstanceDefaultPriceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstanceDefaultPriceServiceImpl implements InstanceDefaultPriceService {
    @Autowired
    private InstanceDefaultPriceDao instanceDefaultPriceDao;
    @Override
    public String create(InstanceDefaultPriceDto instanceDefaultPriceDto) {
        InstanceDefaultPrice instanceDefaultPrice = new InstanceDefaultPrice();
        BeanUtils.copyProperties(instanceDefaultPriceDto, instanceDefaultPrice);
        return "new instance default price item is created";
    }

    @Override
    public List<InstanceDefaultPriceDto> findByInstanceId(Long instanceId) {
        List<InstanceDefaultPrice> instanceDefaultPrices =
                instanceDefaultPriceDao.findByInstanceId(instanceId).get();

        if (!instanceDefaultPrices.isEmpty()) {
            return instanceDefaultPrices.stream().map(e->{
                InstanceDefaultPriceDto instanceDefaultPriceDto = new InstanceDefaultPriceDto();
                BeanUtils.copyProperties(e, instanceDefaultPriceDto);
                return instanceDefaultPriceDto;
            }).toList();
        }
        return null;
    }
}
