package dv16888.com.transaction.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.util.StringUtils;

@Configuration
public class MqttSenderConfig {

    public static final String CHANNEL_NAME_OUT = "mqttOutboundChannel";

    private static final byte[] WILL_DATA;
    static {
        WILL_DATA = "offline".getBytes();
    }

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.url}")
    private String url;

    @Value("${mqtt.sender.clientId}")
    private String clientId;

    @Value("${mqtt.sender.defaultTopic}")
    private String defaultTopic;


    @Bean
    public MqttConnectOptions getSenderMqttConnectOptions(){
        MqttConnectOptions options=new MqttConnectOptions();

        if(!username.trim().equals("")){
            options.setUserName(username);
        }
        options.setPassword(password.toCharArray());
        options.setServerURIs(StringUtils.split(url, ","));
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
        options.setWill("willTopic", WILL_DATA, 2, false);
        return options;
    }

    @Bean
    public MqttPahoClientFactory senderMqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getSenderMqttConnectOptions());
        return factory;
    }

    @Bean(name = CHANNEL_NAME_OUT)
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME_OUT)
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
                clientId,
                senderMqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(defaultTopic);
        return messageHandler;
    }

}
