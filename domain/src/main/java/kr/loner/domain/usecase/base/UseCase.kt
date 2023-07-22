package kr.loner.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.logging.Level
import java.util.logging.Logger

abstract class UseCase<in P, R> {

    /**
     * @return a [Result].
     * @param parameters the input parameters to run the use case with
     */
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            execute(parameters).let {
                Result.success(it)
            }
        } catch (e: Exception) {
            Logger.getLogger(UseCase::class.java.simpleName)
                .log(Level.WARNING, "throw error", e)
            Result.failure(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(param: P): R

}
