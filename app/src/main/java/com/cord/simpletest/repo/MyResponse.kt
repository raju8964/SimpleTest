package com.cord.simpletest.repo

sealed class MyResponse<T>(val data:T?=null,val errormessage:String?=null)
{
    class Loading<T>:MyResponse<T>()
    class Success<T>(val mData:T?=null):MyResponse<T>(data = mData)
    class Error<T>(val error:String):MyResponse<T>(errormessage=error)
}