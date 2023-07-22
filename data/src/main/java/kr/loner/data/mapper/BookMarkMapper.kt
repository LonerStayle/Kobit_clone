package kr.loner.data.mapper

import kr.loner.data.local.model.BookMarkDto
import kr.loner.data.local.model.BookMarkDtoType
import kr.loner.domain.model.BookMark


fun BookMark.toDto() = BookMarkDto(keyKrw, type.toDto(), isLike)
fun BookMarkDto.toDomain() = BookMark(keyKrw, type.toDomain(), isLike)

fun BookMark.Type.toDto(): BookMarkDtoType {
    return when (this) {
        BookMark.Type.Ticker -> BookMarkDtoType.Ticker
    }
}

fun BookMarkDtoType.toDomain(): BookMark.Type {
    return when (this) {
        BookMarkDtoType.Ticker -> BookMark.Type.Ticker
    }
}