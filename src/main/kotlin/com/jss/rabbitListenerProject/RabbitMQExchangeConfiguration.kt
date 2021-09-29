package com.jss.rabbitListenerProject

import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Exchange
import org.springframework.amqp.core.ExchangeBuilder
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQExchangeConfiguration {

    @Bean
    fun firstExchange(): Exchange = TopicExchange("FirstExchange")

    @Bean
    fun directExchange(): DirectExchange = ExchangeBuilder.directExchange("DirectExchange").autoDelete().build()

    @Bean
    fun topicExchange(): Exchange =
        ExchangeBuilder.topicExchange("TopicExchange").autoDelete().durable(true).internal().build()

    @Bean
    fun fanOutExchange(): Exchange =
        ExchangeBuilder.fanoutExchange("FanOutExchange").autoDelete().durable(false).internal().build()

    @Bean
    fun headerExchange(): Exchange =
        ExchangeBuilder.headersExchange("HeaderExchange").internal().durable(false).ignoreDeclarationExceptions()
            .build()
}
