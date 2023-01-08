package com.sicpa.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SicpaServiceApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SicpaServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		for (int i = 0; i < 3; i++) {
			String bCryptPasswordEncoder = passwordEncoder.encode("sicpa-web");
			log.info(bCryptPasswordEncoder);
		}
	}
}
