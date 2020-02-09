package n7.myperfectemptyproject.base

abstract class BaseUseCase<Type, in Params> constructor() where Type : Any {

    abstract suspend fun execute(params: Params? = null): Result<Type>
}
