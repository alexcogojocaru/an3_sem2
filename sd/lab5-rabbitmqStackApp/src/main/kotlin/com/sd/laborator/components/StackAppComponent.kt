package com.sd.laborator.components

import com.sd.laborator.abstractapi.AbstractChaining
import com.sd.laborator.abstractapi.PayloadReceiver
import com.sd.laborator.interfaces.PrimeNumberGenerator
import com.sd.laborator.interfaces.UnionOperation
import com.sd.laborator.model.Stack
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class StackAppComponent {
    private var A: Stack? = null
    private var B: Stack? = null

    @Autowired
    private lateinit var primeGenerator: PrimeNumberGenerator

    @Qualifier("cartesianProductService")
    @Autowired
    private lateinit var cartesianProductOperationOne: AbstractChaining

    @Qualifier("cartesianProductService")
    @Autowired
    private lateinit var cartesianProductOperationTwo: AbstractChaining

    @Qualifier("unionService")
    @Autowired
    private lateinit var unionOperation: AbstractChaining

    @Autowired
    private lateinit var connectionFactory: RabbitMqConnectionFactoryComponent

    private lateinit var amqpTemplate: AmqpTemplate

    @Autowired
    fun initTemplate() {
        this.amqpTemplate = connectionFactory.rabbitTemplate()
    }

    @RabbitListener(queues = ["\${stackapp.rabbitmq.queue}"])
    fun receiveMessage(msg: String) {
        val processedMsg = (msg.split(",").map {
            it.toInt().toChar()
        }).joinToString(separator = "")

        var result: String? = when (processedMsg) {
            "compute" -> computeExpression()
            "regenerate_A" -> regenerateA()
            "regenerate_B" -> regenerateB()
            else -> null
        }

        println("result: ")
        println(result)

        if (result != null) sendMessage(result)
    }

    fun sendMessage(msg: String) {
        println("message: ")
        println(msg)

        this.amqpTemplate.convertAndSend(
            connectionFactory.getExchange(),
            connectionFactory.getRoutingKey(),
            msg
        )
    }

    private fun generateStack(count: Int): Stack? {
        if (count < 1)
            return null

        val X: MutableSet<Pair<Int, Int>> = mutableSetOf()
        while (X.count() < count) {
            X.add(Pair(primeGenerator.generatePrimeNumber(), 0))
        }

        return Stack(X)
    }

    private fun computeExpression(): String {
        if (A == null) {
            A = generateStack(20)
        }

        if (B == null) {
            B = generateStack(20)
        }

        if (A!!.data.count() == B!!.data.count()) {
            cartesianProductOperationOne.loadSet(A!!.data)
            cartesianProductOperationOne.loadSet(B!!.data)

            cartesianProductOperationTwo.loadSet(B!!.data)
            cartesianProductOperationTwo.loadSet(B!!.data)

            cartesianProductOperationOne.setLink(cartesianProductOperationTwo)
            cartesianProductOperationTwo.setLink(unionOperation)

            cartesianProductOperationOne.executeOperation()

            return "${PayloadReceiver.list.size} compute~" + "{\"A\": \"" + A?.data.toString() + "\", \n\"B\": \"" + B?.data.toString() + "\", \n\"result\": \"" +
                    PayloadReceiver.list[0].toString() + "\"}"
        }

        return "compute~" + "Error: A.count() != B.count()"
    }

    private fun regenerateA(): String {
        A = generateStack(20)
        return "A~" + A?.data.toString()
    }

    private fun regenerateB(): String {
        B = generateStack(20)
        return "B~" + B?.data.toString()
    }
}