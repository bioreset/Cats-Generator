package com.dariusz.catsgenerator.domain.model

data class Colors(
    val colors: List<Color>
) {
    companion object {
        fun getColors() =
            Colors(listOf("black", "white", "red", "blue", "green", "orange", "purple")).colors
    }
}