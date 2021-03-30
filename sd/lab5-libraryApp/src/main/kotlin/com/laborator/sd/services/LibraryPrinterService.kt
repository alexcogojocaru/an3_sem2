package com.laborator.sd.services

import com.laborator.sd.interfaces.LibraryPrinter
import com.laborator.sd.model.Book
import org.springframework.stereotype.Service

@Service
class LibraryPrinterService: LibraryPrinter {
    override fun printHTML(books: Set<Book>): String {
        var content: String = "<html><head><title>Libraria mea</title></head><body>"
        books.forEach {
            content += "<p><h3>${it.name}</h3><h4>${it.author}</h4><h5>${it.publisher}</h5><h5>${it.content}</h5></p><br/>"
        }

        content += "</body></html>"
        return content
    }

    override fun printJSON(books: Set<Book>): String {
        var content: String = "[\n"
        books.forEach {
            content += if (it != books.last()) {
                "    {\"Titlu\": \"${it.name}\",\"Author\": \"${it.author}\", \"Editura\": \"${it.publisher}\", \"Text\": \"${it.content}\"},\n"
            } else {
                "    {\"Titlu\": \"${it.name}\",\"Author\": \"${it.author}\", \"Editura\": \"${it.publisher}\", \"Text\": \"${it.content}\"}\n"
            }
        }

        content += "]\n"
        return content
    }

    override fun printRaw(books: Set<Book>): String {
        var content: String = ""

        books.forEach {
            content += "${it.name}\n${it.author}\n${it.publisher}\n${it.content}\n\n"
        }

        return content
    }

    override fun printXML(books: Set<Book>): String {
        var content: String = "<books>\n"

        books.forEach {
            content +=
                    "\t<book>\n" +
                    "\t\t<title>${it.name}</title>\n" +
                    "\t\t<author>${it.author}</author>\n" +
                    "\t\t<publisher>${it.publisher}</publisher>\n" +
                    "\t\t<content>${it.content}</content>\n" +
                    "\t</book>\n"
        }

        content += "</books>"
        return content
    }
}