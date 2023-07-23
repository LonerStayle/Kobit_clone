package kr.loner.domain.usecase.base

abstract class UseCase<P, R> {
    abstract suspend operator fun invoke(param: P): R
}