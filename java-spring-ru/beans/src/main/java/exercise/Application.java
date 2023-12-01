package exercise;

import java.time.LocalTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;

// BEGIN
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;
// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Bean
    @RequestScope
    public Daytime getDaytime() {
        var dayTime = LocalTime.of(6, 0);
        var nightTime = LocalTime.of(22, 00);
        var time = LocalTime.now();

        return !time.isBefore(dayTime) && time.isBefore(nightTime)
            ? new Day() : new Night();
    }
    // END
}
