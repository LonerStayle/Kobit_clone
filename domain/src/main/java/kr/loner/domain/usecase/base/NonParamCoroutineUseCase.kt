package kr.loner.domain.usecase.base

import java.util.logging.Level
import java.util.logging.Logger

abstract class NonParamCoroutineUseCase<R> {

    /**
     * @return a [Result].
     */
    suspend operator fun invoke(): Result<R> {
        return try {
            execute().let {
                Result.success(it)
            }
        } catch (e: Exception) {
            Logger.getLogger(NonParamCoroutineUseCase::class.java.simpleName)
                .log(Level.WARNING, "throw error", e)
            Result.failure(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(): R
}
