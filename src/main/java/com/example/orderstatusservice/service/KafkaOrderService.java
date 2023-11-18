package com.example.orderstatusservice.service;

import com.example.dtomodel.OrderEvent;
import com.example.dtomodel.StatusEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaOrderService {

  private final List<StatusEvent> messages = new ArrayList<>();

  public void add( StatusEvent statusEvent ) {
    messages.add( statusEvent );
  }
}
