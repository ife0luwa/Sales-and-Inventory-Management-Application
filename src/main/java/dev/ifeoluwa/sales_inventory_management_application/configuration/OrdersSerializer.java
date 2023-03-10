package dev.ifeoluwa.sales_inventory_management_application.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.ifeoluwa.sales_inventory_management_application.model.Orders;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author on 10/03/2023
 * @project
 */
public class OrdersSerializer implements Serializer<Orders> {

    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    public byte[] serialize(String topic, Orders data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing orders object", e);
        }
    }
}
