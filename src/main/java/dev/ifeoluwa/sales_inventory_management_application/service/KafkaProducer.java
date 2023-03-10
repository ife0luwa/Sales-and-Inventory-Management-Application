package dev.ifeoluwa.sales_inventory_management_application.service;

import dev.ifeoluwa.sales_inventory_management_application.model.Orders;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author on 09/03/2023
 * @project
 */
@Service
@Slf4j
@ComponentScan
public class KafkaProducer {

    private KafkaTemplate<String, Orders> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, Orders> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Orders order) {
        ProducerRecord<String, Orders> record = new ProducerRecord<>("create-Order", order);
        kafkaTemplate.send("create-Order", record.value());
    }
}
