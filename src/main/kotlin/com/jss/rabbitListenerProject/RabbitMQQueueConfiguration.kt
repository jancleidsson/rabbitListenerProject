package com.jss.rabbitListenerProject

import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.QueueBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQQueueConfiguration {

    @Bean
    fun firstQueue(): Queue = Queue("FirstQueue", false)

    @Bean
    fun secondQueue(): Queue = QueueBuilder.durable("SecondQueue").autoDelete().exclusive().build()
}