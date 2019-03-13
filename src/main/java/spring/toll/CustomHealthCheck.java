package spring.toll;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthCheck implements HealthIndicator {

	int errorcode = 0;
	
	@Override
	public Health health() {
		
		System.out.println("health check performed, error code is " + errorcode);

		// this will temporarily allow take the service down and it will be taken down by eureka server
		// launch one application with all this enabled to that it would fail for a while
		// and another without so that it would work without issues
		if(errorcode > 2 && errorcode < 10) {
			errorcode++;
			return Health.down().withDetail("Custom Error Code", errorcode).build();
		}
		else {
			errorcode++;
			return Health.up().build();
		}
	}
}
