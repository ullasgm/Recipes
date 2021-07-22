package com.example.recipes.data

import com.contentful.java.cda.CDAAsset
import com.contentful.java.cda.CDAEntry
import com.contentful.java.cda.image.ImageOption
import com.example.recipes.data.entity.Recipe


fun fromRestEntry(entry: CDAEntry, locale: String): Recipe = Recipe(
    entry.getField<String?>(locale, "title").orEmpty(),
    try {
        entry.getField<CDAAsset?>(locale, "photo")
            ?.urlForImageWith(ImageOption.https(), ImageOption.formatOf(ImageOption.Format.webp))
            .orEmpty()
    } catch (_: Throwable) {
        ""
    },
    entry.getField<List<CDAEntry>>(locale, "tags")
        .orEmpty()
        .map { it.getField("name") },
    entry.getField<String?>(locale, "description").orEmpty(),
    entry.getField<Double?>(locale, "calories").or(0.0).toInt(),
    entry.getField<CDAEntry?>(locale, "chef")?.getField("name")
)

private fun Double?.or(default: Double): Double = this ?: default

