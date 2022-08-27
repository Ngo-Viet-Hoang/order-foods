package com.example.orderfood.service;

import com.example.orderfood.entity.ViewOrder;
import com.example.orderfood.repository.ViewOrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ViewOrderService {
    final ViewOrderRepository viewOrderRepository;

    public ViewOrderService(ViewOrderRepository viewOrderRepository) {

        this.viewOrderRepository = viewOrderRepository;
    }

    public List<ViewOrder> findAll() {
        return viewOrderRepository.findAll();
    }
    public Optional<ViewOrder> findById(Long id) {
        return viewOrderRepository.findById(id);
    }
    public ViewOrder save(ViewOrder viewOrder) {
        return viewOrderRepository.save(viewOrder);
    }


}
