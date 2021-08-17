package com.dariusz.catsgenerator.utils

import com.dariusz.catsgenerator.domain.model.*
import com.dariusz.catsgenerator.utils.Constants.API_URL

object UrlBuilder {

    fun getRandomCatUrl(
        text: Text? = "",
        filter: Filter? = "",
        tag: Tag? = "",
        color: Color? = "",
        size: Size? = ""
    ) =
        API_URL + "cat/says/" + text + "?&filter=" + filter + "&tag=" + tag + "&color=" + color + "&size=" + size

}