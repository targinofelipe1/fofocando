package br.edu.ifpb.fofoca

import com.example.fofoca.Fofoca

class Fofocando {
    private val fofocas = mutableListOf<Fofoca>()

    fun addFofoca(fofoca: Fofoca) {
        fofocas.add(fofoca)
    }

    fun getTotalFofocas(): Int {
        return fofocas.size
    }

    fun getRandomFofoca(): Fofoca {
        return fofocas.random()
    }
}