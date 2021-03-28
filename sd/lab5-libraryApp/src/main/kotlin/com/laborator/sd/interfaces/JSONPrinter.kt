package com.laborator.sd.interfaces

import com.laborator.sd.model.Book

interface JSONPrinter {
    fun printJSON(books: Set<Book>): String
}