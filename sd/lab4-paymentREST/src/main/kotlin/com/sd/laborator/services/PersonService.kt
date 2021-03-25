package com.sd.laborator.services

import com.sd.laborator.interfaces.PersonInterface
import com.sd.laborator.pojo.Person
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class PersonService : PersonInterface {
    companion object {
        private val payers = ConcurrentHashMap<Int, Person>()
    }

    override fun readPerson(id: Int): Person? {
        return payers[id]
    }

    override fun updatePerson(id: Int, person: Person) {
        deletePerson(id)
        createPerson(person)
    }

    override fun deletePerson(id: Int) {
        payers.remove(id)
    }

    override fun createPerson(person: Person) {
        payers[person.id] = person
    }

    override fun getPersons(): List<Person> {
        return payers.map { it.value }.toList()
    }

    override fun personExists(id: Int): Boolean {
        return payers.containsKey(id)
    }
}