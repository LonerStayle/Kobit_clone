package kr.loner.domain.usecase.base

abstract class NonParamUseCase<R> {
    abstract suspend operator fun invoke():R
}