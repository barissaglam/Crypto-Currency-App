package barissaglam.core.data

interface Mapper<ResponseModel, UiModel> {
    fun toMapUiModel(model: ResponseModel): UiModel
}
