package com.sd.laborator.interfaces

import com.sd.laborator.pojo.Person

interface PersonInterface {
    fun createPerson(person: Person)
    fun readPerson(id: Int): Person?
    fun updatePerson(id: Int, person: Person)
    fun deletePerson(id: Int)
    fun getPersons(): List<Person>
}