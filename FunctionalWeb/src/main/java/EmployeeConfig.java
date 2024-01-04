import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

public class EmployeeConfig {
    @Bean
    EmployeeRepository employeeRepository() {
        return new EmployeeRepository();
    }
    @Bean
    RouterFunction<ServerResponse> getAllEmployeesRoute() {
        return route(GET("/employees"), req -> ok().body(employeeRepository().findAllEmployees(), Employee.class));
    }
}
