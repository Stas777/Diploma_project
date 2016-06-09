package com.bsu.mvt.server.core.dao.impl;

import com.bsu.mvt.server.core.dao.UserDAO;
import com.bsu.mvt.server.core.entity.UserEntity;
import com.bsu.mvt.server.core.util.DBUtil;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository(value = "userDAO")
public class JdbcUserDAO extends JdbcDaoSupport implements UserDAO {
    private RowMapper<UserEntity> userRowMapper;

    private final static class UserRowMapper implements RowMapper<UserEntity> {
        public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserEntity e = new UserEntity();
            e.setId(DBUtil.getLong(rs, "id"));
            e.setFirst_name(DBUtil.getString(rs, "first_name"));
            e.setLast_name(DBUtil.getString(rs, "last_name"));
            e.setEmail(DBUtil.getString(rs, "email"));
            e.setPassword(DBUtil.getString(rs, "password"));
            return e;
        }
    }

    @Autowired
    public JdbcUserDAO(@Qualifier("dataSource") DataSource dataSource) {
        setDataSource(dataSource);
        userRowMapper = new UserRowMapper();
    }

    // user
    @Override
    public UserEntity readUser(Long id) throws DataAccessException {
        String sql = "SELECT u.* FROM user u WHERE u.id = ?";
        List<UserEntity> list = getJdbcTemplate().query(sql, userRowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public UserEntity readUser(String email) throws DataAccessException {
        String sql = "SELECT u.* FROM user u WHERE u.email = ?";
        List<UserEntity> list = getJdbcTemplate().query(sql, userRowMapper, email);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long createUser(final UserEntity e) throws DataAccessException {
        final String sql = "INSERT INTO user (first_name, last_name, email, password) VALUES(?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        DBUtil.setString(ps, 1, e.getFirst_name());
                        DBUtil.setString(ps, 2, e.getLast_name());
                        DBUtil.setString(ps, 3, e.getEmail());
                        DBUtil.setString(ps, 4, e.getPassword());
                        return ps;
                    }
                },
                keyHolder) == 1) {
            return (Long) keyHolder.getKey();
        }

        return null;
    }

    @Override
    public boolean updateUser(UserEntity e) throws DataAccessException {
        String sql = "UPDATE user SET first_name = ?, last_name = ?, email  = ?, password  = ? WHERE id = ?";
        int res = getJdbcTemplate().update(sql, e.getFirst_name(), e.getLast_name(), e.getEmail(), e.getPassword(), e.getId());
        return res == 1;
    }

    @Override
    public List<UserEntity> listUser() throws DataAccessException {
        String sql = "SELECT u.* FROM user u";
        List<UserEntity> list = getJdbcTemplate().query(sql, userRowMapper);
        return list.isEmpty() ? null : list;
    }
}
