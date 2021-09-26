package com.mypracice.couchbase;

import com.mypracice.couchbase.config.CouchbaseProperties;
import com.mypracice.couchbase.repository.N1QLEmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class SpringReactiveCouchbaseApplication
		//implements CommandLineRunner
{
	@Autowired
	private CouchbaseProperties couchbaseProperties;
	@Autowired
	private N1QLEmployeeRepository employeeRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveCouchbaseApplication.class, args);
	}

	//@Override
	public void run(String... args) throws Exception {
		log.info("SpringReactiveCouchbaseApplication.run port :" + couchbaseProperties.getPort());
		log.info("SpringReactiveCouchbaseApplication.run password :" + couchbaseProperties.getPassword());
		log.info("SpringReactiveCouchbaseApplication.run bucket :" + couchbaseProperties.getBucketName());
		log.info("SpringReactiveCouchbaseApplication.run bootstrapHosts :" + couchbaseProperties.getBootstrapHosts());
		log.info("SpringReactiveCouchbaseApplication.run bootstrapHosts :" + couchbaseProperties.getRule());
		employeeRepository.findById("00036d45-bf95-4d8a-9bcb-02e2b1f03d0b").doOnNext(System.out::println).subscribe();
		employeeRepository.findAssetByParameters(new StringBuilder(" offset  0  limit 10"))
				.doOnNext(s->
				System.out.println(s + " "+ Thread.currentThread().getName())
		).subscribe();
		//employeeRepository.findAssetByParameters().log().subscribe();
			/*for (var i = 0; i <59000 ; i++) {
			var employee = new Employee();
			employee.setEmployeeId(UUID.randomUUID().toString());
			employee.setFirstName("Nasruddin");
			employee.setLastName("Khan");
			System.out.println(employeeRepository.save(employee).subscribe());

		}*/

		log.info("SpringReactiveCouchbaseApplication.run complete");

	}
}
