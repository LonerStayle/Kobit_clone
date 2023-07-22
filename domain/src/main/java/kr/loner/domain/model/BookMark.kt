package kr.loner.domain.model

data class BookMark(
    val keyKrw: String,
    val type: Type,
    val isLike: Boolean = false
) {
    enum class Type { Ticker }
}
