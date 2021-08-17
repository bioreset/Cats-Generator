package com.dariusz.catsgenerator.domain.model

data class Cat(
    val urlToShow: String
) {
    companion object {
        fun getFinalCatUrl(input: String) = Cat(input).urlToShow
    }
}