package com.example.app.di

import android.app.Application
import com.example.app.activity.NewsActivity
import com.example.entity.di.DomainModule
import com.example.presentation.di.PresentationModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        DomainModule::class,
        ApplicationModule::class,
        PresentationModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: com.example.app.Application)
    fun inject(newsActivity: NewsActivity)
}
