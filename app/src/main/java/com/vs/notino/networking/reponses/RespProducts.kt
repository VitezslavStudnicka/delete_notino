package com.vs.notino.networking.reponses

import com.vs.notino.models.Product
import com.vs.notino.networking.models.Metadata

data class RespProducts(
    val vpProductByIds: List<Product>?,
    val metadata: Metadata?
)