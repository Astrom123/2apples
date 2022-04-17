package apples.gameschedule.configuration

import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


@Configuration
class AppConfiguration {

    private val localTimeFormat = "HH:mm"

    @Bean
    fun jsonCustomizer(): Jackson2ObjectMapperBuilderCustomizer? {
        return Jackson2ObjectMapperBuilderCustomizer { builder: Jackson2ObjectMapperBuilder ->
            builder.serializers(LocalTimeSerializer(DateTimeFormatter.ofPattern(localTimeFormat)))
                .deserializers(LocalTimeDeserializer(DateTimeFormatter.ofPattern(localTimeFormat)))
        }
    }
}