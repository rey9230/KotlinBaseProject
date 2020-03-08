package n7.myperfectemptyproject.base

abstract class BaseUseCase<Type, in Params> constructor() where Type : Any {

    abstract suspend operator fun invoke(params: Params? = null): Type
}