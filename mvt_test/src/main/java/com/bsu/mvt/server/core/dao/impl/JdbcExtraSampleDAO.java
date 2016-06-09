package com.bsu.mvt.server.core.dao.impl;

import com.bsu.mvt.server.core.dao.ExtraSampleDAO;
import com.bsu.mvt.server.core.entity.ExtraSampleEntity;
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

@Repository(value = "extraSampleDAO")
public class JdbcExtraSampleDAO extends JdbcDaoSupport implements ExtraSampleDAO {
    private RowMapper<ExtraSampleEntity> extraSampleRowMapper;

    private final static class ExtraSampleRowMapper implements RowMapper<ExtraSampleEntity> {
        public ExtraSampleEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExtraSampleEntity e = new ExtraSampleEntity();
            e.setId(DBUtil.getLong(rs, "id"));
            e.setSample_id(DBUtil.getLong(rs, "sample_id"));
            e.setType(DBUtil.getString(rs, "type"));
            e.setKey(DBUtil.getString(rs, "key"));
            e.setValue(DBUtil.getString(rs, "value"));
            return e;
        }
    }

    @Autowired
    public JdbcExtraSampleDAO(@Qualifier("dataSource") DataSource dataSource) {
        setDataSource(dataSource);
        extraSampleRowMapper = new ExtraSampleRowMapper();
    }

    // extraSample
    @Override
    public ExtraSampleEntity readExtraSample(Long id) throws DataAccessException {
        String sql = "SELECT es.* FROM extra_sample es WHERE es.id = ?";
        List<ExtraSampleEntity> list = getJdbcTemplate().query(sql, extraSampleRowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long createExtraSample(final ExtraSampleEntity e) throws DataAccessException {
        final String sql = "INSERT INTO extra_sample (`sample_id`, `type`, `key`, `value`) VALUES(?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        DBUtil.setLong(ps, 1, e.getSample_id());
                        DBUtil.setString(ps, 2, e.getType());
                        DBUtil.setString(ps, 3, e.getKey());
                        DBUtil.setString(ps, 4, e.getValue());
                        return ps;
                    }
                },
                keyHolder) == 1) {
            return (Long) keyHolder.getKey();
        }

        return null;
    }

    @Override
    public boolean updateExtraSample(ExtraSampleEntity e) throws DataAccessException {
        String sql = "UPDATE extra_sample SET `sample_id` = ?, `type` = ?, `key`  = ?, `value`  = ? WHERE id = ?";
        int res = getJdbcTemplate().update(sql, e.getSample_id(), e.getType(), e.getKey(), e.getValue(), e.getId());
        return res == 1;
    }

    @Override
    public List<ExtraSampleEntity> listExtraSample() throws DataAccessException {
        String sql = "SELECT es.* FROM extra_sample es";
        List<ExtraSampleEntity> list = getJdbcTemplate().query(sql, extraSampleRowMapper);
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<ExtraSampleEntity> listExtraSample(Long sample_id) throws DataAccessException {
        String sql = "SELECT es.* FROM extra_sample es WHERE es.sample_id = ?";
        List<ExtraSampleEntity> list = getJdbcTemplate().query(sql, extraSampleRowMapper, sample_id);
        return list.isEmpty() ? null : list;
    }

    @Override
    public boolean deleteExtraSampleBySample(Long sample_id) {
        String sql = "DELETE FROM extra_sample WHERE sample_id = ?";
        int res = getJdbcTemplate().update(sql, sample_id);
        return res == 1;
    }
}
