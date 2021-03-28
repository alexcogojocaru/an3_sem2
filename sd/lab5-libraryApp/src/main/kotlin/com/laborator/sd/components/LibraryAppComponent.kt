package com.laborator.sd.components

import com.laborator.sd.interfaces.LibraryDAO
import com.laborator.sd.interfaces.LibraryPrinter
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LibraryAppComponent {
    @Autowired
    private lateinit var libraryDAO: LibraryDAO

    @Autowired
    private lateinit var libraryPrinter: LibraryPrinter

    @Autowired
    private lateinit var connectionFactory: RabbitMqConnectionFactoryComponent

    private lateinit var amqpTemplate: AmqpTemplate

    @Autowired
    fun initTemplate() {
        this.amqpTemplate = connectionFactory.rabbitTemplate()
    }

    fun sendMessage(msg: String) {
        this.amqpTemplate.convertAndSend(
            connectionFactory.getExchange(),
            connectionFactory.getRoutingKey(),
            msg
        )
    }

    @RabbitListener(queues = ["\${libraryapp.rabbitmq.queue}"])
    fun receiveMessage(msg: String) {

    }
}