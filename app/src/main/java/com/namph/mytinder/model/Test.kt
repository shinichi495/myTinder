package com.namph.mytinder.model

class A<F>(aa : F) {
    val a = aa
    fun getType () : F {
        return a
    }
}

class B  {
    init {
        val c = C ("","")
        val a = A(c)
        a.getType().a
    }
}

data class C (val a : String, val b : String)