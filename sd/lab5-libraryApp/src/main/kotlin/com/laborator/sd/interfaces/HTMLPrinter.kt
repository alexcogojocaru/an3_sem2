package com.laborator.sd.interfaces

import com.laborator.sd.model.Book

interface HTMLPrinter {
    fun printHTML(books: Set<Book>): String
}