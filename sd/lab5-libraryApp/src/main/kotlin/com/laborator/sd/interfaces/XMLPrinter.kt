package com.laborator.sd.interfaces

import com.laborator.sd.model.Book

interface XMLPrinter {
    fun printXML(books: Set<Book>): String
}