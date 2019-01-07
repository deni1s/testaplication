package com.example.denis.myapplication.utils.parsing

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.util.LinkedHashMap

class AnnotatedConverterFactory(internal val mFactoryMap: Map<Class<*>, Converter.Factory>) : Converter.Factory() {

    @Retention(AnnotationRetention.RUNTIME)
    annotation class Json

    @Retention(AnnotationRetention.RUNTIME)
    annotation class Xml


    override fun responseBodyConverter(
        type: Type?,
        annotations: Array<out Annotation>?,
        retrofit: Retrofit?
    ): Converter<ResponseBody, *>? {
        for (annotation in annotations!!) {
            val factory = mFactoryMap[annotation.javaClass]
            if (factory != null) {
                return factory.responseBodyConverter(type, annotations, retrofit)
            }
        }
        //try to default to json in case no annotation on current method was found
        val jsonFactory = mFactoryMap[Json::class.java]
        return jsonFactory?.responseBodyConverter(type, annotations, retrofit)
    }

    internal class Builder {
        var mFactoryMap: MutableMap<Class<*>, Converter.Factory> = LinkedHashMap()

        fun add(factoryType: Class<out Annotation>?, factory: Converter.Factory?): Builder {
            if (factoryType == null) throw NullPointerException("factoryType is null")
            if (factory == null) throw NullPointerException("factory is null")
            mFactoryMap[factoryType] = factory
            return this
        }

        fun build(): AnnotatedConverterFactory {
            return AnnotatedConverterFactory(mFactoryMap)
        }

    }
}