package ro.traian.eapw.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import ro.traian.eapw.entity.Employee;
import ro.traian.eapw.record.EmployeeRecord;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<EmployeeRecord> findAllEmployees() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<EmployeeRecord> query = cb.createQuery(EmployeeRecord.class);
        Root<Employee> root = query.from(Employee.class);

        query.select(cb.construct(
                EmployeeRecord.class,
                root.get("id"),
                root.get("name"),
                root.get("role")));

        return entityManager
                .createQuery(query)
                .getResultList();
    }

    public EmployeeRecord findEmployeeById(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<EmployeeRecord> query = cb.createQuery(EmployeeRecord.class);
        Root<Employee> root = query.from(Employee.class);

        query.select(cb.construct(
                EmployeeRecord.class,
                root.get("id"),
                root.get("name"),
                root.get("role")));

        query.where(cb.equal(root.get("id"), id));

        return entityManager.createQuery(query).getSingleResult();
    }

    @Transactional
    public EmployeeRecord saveEmployee(EmployeeRecord employeeRecord) {
        Employee employee = new Employee();

        employee.setName(employeeRecord.name());
        employee.setRole(employeeRecord.role());

        entityManager.persist(employee);

        return this.findEmployeeById(employee.getId());
    }

    @Transactional
    public EmployeeRecord updateEmployee(Long id, EmployeeRecord employeeRecord) {
        Employee employee = entityManager.find(Employee.class, id);

        employee.setName(employeeRecord.name());
        employee.setRole(employeeRecord.role());

        entityManager.merge(employee);

        return this.findEmployeeById(id);
    }

    @Transactional
    public boolean deleteEmployee(Long id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);

        return true;
    }

}
