package com.yara.quarkus.starter.common

import arrow.core.Either
import arrow.core.Validated
import arrow.core.identity

/**
This is used to get right hand side data from Either .
Incorrect use of this can break code, highly suggested to be called in Either.catch block, unless you are confident of your output.
 **/
fun <A> Either<*, A>.getUncheckedData(): A = this.fold(
    { f -> if (f is Throwable) throw f else nope(this.toString()) },
    ::identity
)

fun nope(msg: String = ""): Nothing = throw Exception("This should never happen! $msg")

fun <T> Validated<*, T>.getValidData(): T = this.toEither().getUncheckedData()
