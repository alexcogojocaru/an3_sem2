package com.laborator.sd.model

class Book(private val data: Content) {
    var name: String?
        get() {
            return data.name
        }
        set(value) {
            data.name = value
        }

    var author: String?
        get() {
            return data.author
        }
        set(value) {
            data.author = value
        }

    var content: String?
        get() {
            return data.text
        }
        set(value) {
            data.text = value
        }

    var publisher: String?
        get() {
            return data.publisher
        }
        set(value) {
            data.publisher = value
        }

    fun hasAuthor(author: String): Boolean {
        return data.author?.toLowerCase().equals(author.toLowerCase())
    }

    fun hasTitle(title: String): Boolean {
        return data.name?.toLowerCase().equals(title.toLowerCase())
    }

    fun publishedBy(publisher: String): Boolean {
        return data.publisher?.toLowerCase().equals(publisher.toLowerCase())
    }
}