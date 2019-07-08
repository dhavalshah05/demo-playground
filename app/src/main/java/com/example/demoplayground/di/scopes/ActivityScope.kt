package com.example.demoplayground.di.scopes

import javax.inject.Scope

@Scope
@Target(
        AnnotationTarget.FIELD,
        AnnotationTarget.VALUE_PARAMETER,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER,
        AnnotationTarget.CLASS
)
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityScope