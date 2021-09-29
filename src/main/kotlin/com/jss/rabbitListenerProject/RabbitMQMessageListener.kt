package com.jss.rabbitListenerProject

import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageListener

class RabbitMQMessageListener(): MessageListener {
    override fun onMessage(message: Message?) {
        println("Message received: ${String(message!!.body)}.")
    }
}