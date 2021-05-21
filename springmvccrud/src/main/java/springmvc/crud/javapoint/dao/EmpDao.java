package springmvc.crud.javapoint.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import springmvc.crud.javapoint.beans.Emp;

@Repository
public class EmpDao {

    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int save(Emp emp) {
        String sql = "insert into emp(name, salary, designation) values'" + emp.getName() + "',"
                + emp.getSalary() + ",'" + emp.getDesignation() + "')";
        return template.update(sql);
    }
    
    public int update(Emp emp) {
        String sql = "update emp set name='" + emp.getName() + "', salary=" + emp.getSalary()
                + ",designation='" + emp.getDesignation() + "' where id=" + emp.getId();
        return template.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from emp where id=" + id;
        return template.update(sql);
    }

    public List<Emp> getEmployees() {
        //查詢所有emp
        //每行mapRow()處理, 返回emp裝到list裡
        return template.query("select * from emp", new RowMapper<Emp>() {
            public Emp mapRow(ResultSet rs, int row) throws SQLException {
                Emp e = new Emp();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setSalary(rs.getFloat(3));
                e.setDesignation(rs.getString(4));
                return e;
            }
        });
    }

    public Emp getEmpById(int id) {
        String sql = "select * from emp where id=?";
        return template.queryForObject(sql, new Object[] { id },
                new BeanPropertyRowMapper<Emp>(Emp.class));
    }

}
