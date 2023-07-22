package kr.loner.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookMarkDto(
    @PrimaryKey
    val keyKrw: String = "",
    val type: BookMarkDtoType,
    val isLike: Boolean = false
)

enum class BookMarkDtoType { Ticker }
