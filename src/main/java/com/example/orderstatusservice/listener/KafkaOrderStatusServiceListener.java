package com.example.orderstatusservice.listener;

import com.example.dtomodel.OrderEvent;
import com.example.dtomodel.StatusEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaOrderStatusServiceListener {

  @Value( "${app.kafka.kafkaOrderStatusTopic}" )
  private String topicName;

  private final KafkaTemplate<String, StatusEvent> kafkaTemplate;

  @KafkaListener( topics = "${app.kafka.kafkaOrderTopic}",
                  groupId = "${app.kafka.kafkaOrderGroupId}",
                  containerFactory = "kafkaOrderConcurrentKafkaListenerContainerFactory"
  )
  public void listen( @Payload OrderEvent message,
                      @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                      @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                      @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp
  ) {
    log.info( "ORDER_STATUS_SERVICE 22222 CONSUMER" );
    log.info( "Received message: {}", message );
    log.info( "Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, topic, partition, timestamp );

    StatusEvent event = new StatusEvent( "CREATED", Instant.now() );
    kafkaTemplate.send( topicName, event );
  }

}
