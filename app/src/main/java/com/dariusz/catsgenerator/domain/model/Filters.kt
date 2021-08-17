package com.dariusz.catsgenerator.domain.model

data class Filters(
    val filters: List<Filter>
) {
    companion object {
        fun getFilters() =
            Filters(listOf("blur", "mono", "sepia", "negative", "paint", "pixel")).filters
    }

}