package com.sd.laborator.services

import com.sd.laborator.interfaces.Chaining
import org.springframework.stereotype.Service

@Service
class ChainingService(link: ChainingService?) : Chaining {
    private var link: ChainingService? = link

    override fun execute(A: Set<Int>, B: Set<Int>): Set<Pair<Int, Int>> {
            
    }


}