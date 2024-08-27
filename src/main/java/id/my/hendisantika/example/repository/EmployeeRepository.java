package id.my.hendisantika.example.repository;

import id.my.hendisantika.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-with-postgres-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 27/08/24
 * Time: 07.32
 * To change this template use File | Settings | File Templates.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
