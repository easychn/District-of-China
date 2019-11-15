package com.easychn.DistrictOfChina;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.easychn.DistrictOfChina.service.impl.DistrictServiceImpl;

@SpringBootApplication
public class DistrictOfChinaApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DistrictOfChinaApplication.class, args);
        parseDistrictData();
	}
	
	public static void parseDistrictData() throws IOException {
        DistrictServiceImpl districtServiceImpl = new DistrictServiceImpl();
        districtServiceImpl.getDistrictDataSource();
	}

}
