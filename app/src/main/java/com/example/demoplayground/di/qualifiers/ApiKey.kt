package com.example.demoplayground.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.SOURCE)
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
annotation class ApiKey