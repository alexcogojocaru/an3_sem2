package com.laborator.sd.interfaces

import com.laborator.sd.model.Book

interface RawPrinter {
    fun printRaw(books: Set<Book>): String
}