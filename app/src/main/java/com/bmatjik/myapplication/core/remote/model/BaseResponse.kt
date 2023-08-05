package com.bmatjik.myapplication.core.remote.model

abstract class BaseResponse(
    open val status:String,
    open val code:String?="",
    open val message:String?=""
)
