package com.dariusz.catsgenerator.domain.model

data class Tags(
    val tagsNames: List<Tag> = listOf()
) {
    companion object {
        fun getTags(input: List<Tag>) = Tags(input).tagsNames
    }
}