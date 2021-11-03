package dv16888.com.transaction.config;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;


@Component
@MessagingGateway(defaultRequestChannel = MqttSenderConfig.CHANNEL_NAME_OUT)
public interface IMqttSender {

    void sendToMqtt(String data);
 
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic,
                    String payload);
 
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic,
                    @Header(MqttHeaders.QOS) int qos,
                    String payload);
}
