package n7.myperfectemptyproject.base

// I am not sure if we need this, but too scared to delete.
abstract class BaseUseCase<Type, in Params> constructor() where Type : Any {

    abstract suspend operator fun invoke(params: Params? = null): Type
}