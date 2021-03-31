package com.laborator.sd.components

import com.laborator.sd.interfaces.LibraryDAO
import com.laborator.sd.interfaces.LibraryPrinter
import com.laborator.sd.model.Book
import com.laborator.sd.model.Content
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.Exception

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

    private fun sendMessage(msg: String) {
        this.amqpTemplate.convertAndSend(
            connectionFactory.getExchange(),
            connectionFactory.getRoutingKey(),
            msg
        )
    }

    @RabbitListener(queues = ["\${libraryapp.rabbitmq.queue}"])
    fun receiveMessage(msg: String) {
        println(msg)
        val processedMsg = (msg.split(",").map { it.toInt().toChar() }).joinToString(separator = "")

        try {
            val (function, parameter) = processedMsg.split(":")
            val result: String? = when (function) {
                "print" -> customPrint(parameter)
                "find" -> customFind(parameter)
                else -> null
            }

            if (function == "add") {
                val params: MutableList<String> = processedMsg.split(":").toMutableList()
                params.removeAt(0)
                addBook(Book(
                    Content(
                        author = params[0],
                        text = params[1],
                        name = params[2],
                        publisher = params[3]
                    )
                ))
            }

            if (result != null) sendMessage(result)
        } catch (e: Exception) {
            println(e)
        }
    }

    private fun customPrint(format: String): String {
        return when (format) {
            "html" -> libraryPrinter.printHTML(libraryDAO.getBooks())
            "json" -> libraryPrinter.printJSON(libraryDAO.getBooks())
            "raw" -> libraryPrinter.printRaw(libraryDAO.getBooks())
            else -> "Not implemented"
        }
    }

    private fun customFind(searchParameter: String): String? {
        val (field, format, value) = searchParameter.split("=")

        val filteredBooks: Set<Book>? = when (field) {
            "author" -> this.libraryDAO.findAllByAuthor(value)
            "title" -> this.libraryDAO.findAllByTitle(value)
            "publisher" -> this.libraryDAO.findAllByPublisher(value)
            else -> null
        }

        if (filteredBooks != null) {
            return when (format) {
                "json" -> this.libraryPrinter.printJSON(filteredBooks)
                "html" -> this.libraryPrinter.printHTML(filteredBooks)
                "xml"  -> this.libraryPrinter.printXML(filteredBooks)
                "text" -> this.libraryPrinter.printRaw(filteredBooks)
                else -> "[SWITCH] Not a valid field $format $searchParameter"
            }
        }

        return "Not a valid field $format $searchParameter"
    }

    private fun addBook(book: Book): Boolean {
        return try {
            this.libraryDAO.addBook(book)
            true
        } catch (e: Exception) {
            false
        }
    }
}