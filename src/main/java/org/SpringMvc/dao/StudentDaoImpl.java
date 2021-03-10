package org.SpringMvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.SpringMvc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
@Component
public class StudentDaoImpl implements StudentDao {
    @Autowired
	private JdbcTemplate jdbcTemplate;

	public StudentDaoImpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}

	@Override
	public int create(Student student) {
		
		String sql = "insert into student1(stu_name,stu_email,stu_course) values(?,?,?)";

		try {
			KeyHolder keyholder=new GeneratedKeyHolder();

			jdbcTemplate.update("alter table student1 AUTO_INCREMENT=1");
			int counter = jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps=con.prepareStatement(sql,new String[] {"stu_id"});
					
					ps.setObject(1,student.getName());
					ps.setObject(2, student.getEmail());
					ps.setObject(3, student.getCourse());
					return ps;
				}
				
			},keyholder
					);
			Number key=keyholder.getKey();
			
			
			return  key.intValue();

		}catch(DuplicateKeyException d) {
			return -1;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	
    @Override
	public List<Student> read()
    {
 
        return jdbcTemplate.query("SELECT * FROM STUDENT1", new Object[] {},
                (rs, rowNum) -> new Student(rs.getInt("stu_id"), rs.getString("stu_name"), rs.getString("stu_email"),rs.getString("stu_course")));
    }

	@Override
	public List<Student> findStudentById(int studentId) {

		List<Student> studentList = jdbcTemplate.query("SELECT * FROM STUDENT1 where stu_id=?",
				new Object[] { studentId }, new RowMapper<Student>() {

					@Override
					public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
						Student student = new Student();

						student.setId(rs.getInt("stu_id"));
						student.setName(rs.getString("stu_name"));
						student.setEmail(rs.getString("stu_email"));
						student.setCourse(rs.getString("stu_course"));

						return student;
					}

				});

		return studentList;
	}

	@Override
	public int update(Student student) {
		String sql = "update  student1 set stu_name=?, stu_email=?, stu_course=? where stu_id=?";

		try {

			int counter = jdbcTemplate.update(sql,
					new Object[] { student.getName(), student.getEmail(), student.getCourse(), student.getId() });

			return counter;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(int studentId) {
		String sql = "delete from student1 where stu_id=?;";

		try {

			int counter = jdbcTemplate.update(sql, new Object[] { studentId });

			return counter;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
