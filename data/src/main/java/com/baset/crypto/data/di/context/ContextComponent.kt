package com.baset.crypto.data.di.context

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ContextScope

@ContextScope
@Component
interface ContextComponent {

    fun provideApplication(): Context

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ContextComponent
    }
}