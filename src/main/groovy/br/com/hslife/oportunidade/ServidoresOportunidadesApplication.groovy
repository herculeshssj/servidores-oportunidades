package br.com.hslife.oportunidade

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableScheduling
class ServidoresOportunidadesApplication {

	static void main(String[] args) {
		SpringApplication.run(ServidoresOportunidadesApplication, args)
	}

}
