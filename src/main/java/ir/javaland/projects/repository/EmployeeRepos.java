package ir.javaland.projects.repository;

import ir.javaland.projects.model.Employee;

public class EmployeeRepos extends CrudRepos<Employee, Long> {
    @Override
    protected Class<Employee> getEntityClass() {
        return Employee.class;
    }

    private static EmployeeRepos employeeRepos = null;

    private EmployeeRepos() {
    }

    public static EmployeeRepos getInstance() {
        if (employeeRepos == null)
            employeeRepos = new EmployeeRepos();
        return employeeRepos;
    }

    public void deleteAll() {
        session.beginTransaction();
        session.createQuery("delete from Employee where id >= 0");
        session.getTransaction().commit();
    }
}
