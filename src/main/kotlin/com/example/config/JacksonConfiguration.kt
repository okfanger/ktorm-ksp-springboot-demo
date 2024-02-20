package com.example.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.ktorm.jackson.KtormModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.text.SimpleDateFormat

@Configuration
class JacksonConfiguration {

    fun ktormModule(): Module {
        return KtormModule()
    }

    @Bean
    fun jacksonObjectMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper {
        val module = SimpleModule().apply {
            addSerializer(Long::class.javaObjectType, ToStringSerializer.instance)
            addSerializer(Long::class.javaPrimitiveType, ToStringSerializer.instance)
        }
        return builder.createXmlMapper(false).build<ObjectMapper>()
            .apply {
                //对象的所有字段全部列入
                setSerializationInclusion(JsonInclude.Include.ALWAYS)
                //取消默认转换timestamps形式
                configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                //忽略空Bean转json的错误
                configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
                setDateFormat(SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                registerModule(module)
                registerModule(ktormModule())
            }
    }
}

