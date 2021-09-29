package com.jss.rabbitListenerProject

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.MessageListenerContainer
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    @Autowired
    lateinit var firstQueue: Queue

    @Autowired
    lateinit var directExchange: DirectExchange

    @Bean
    fun bindFirstQueue(): Binding = BindingBuilder.bind(firstQueue).to(directExchange).with("firstTopic")

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val cachingConnectionFactory = CachingConnectionFactory("localhost")
        cachingConnectionFactory.username = "guest"
        cachingConnectionFactory.setPassword("guest")
        return cachingConnectionFactory
    }

    @Bean
    fun messageListenerContainer(): MessageListenerContainer {
        val simpleMessageListenerContainer = SimpleMessageListenerContainer()
        simpleMessageListenerContainer.connectionFactory = connectionFactory()
        simpleMessageListenerContainer.setQueues(firstQueue)
        simpleMessageListenerContainer.setMessageListener(RabbitMQMessageListener())
        return simpleMessageListenerContainer
    }
}