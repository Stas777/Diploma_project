package com.bsu.mvt.server.core.dao.impl;

import com.bsu.mvt.server.core.dao.ExtraFileDAO;
import com.bsu.mvt.server.core.entity.ExtraFileEntity;
import com.bsu.mvt.server.core.util.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository(value = "extraFileDAO")
public class JdbcExtraFileDAO extends JdbcDaoSupport implements ExtraFileDAO {
    private RowMapper<ExtraFileEntity> extraFileRowMapper;

    private final static class ExtraFileRowMapper implements RowMapper<ExtraFileEntity> {
        public ExtraFileEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExtraFileEntity e = new ExtraFileEntity();
            e.setId(DBUtil.getLong(rs, "id"));
            e.setPath(DBUtil.getString(rs, "path"));
            e.setFile_hash(DBUtil.getString(rs, "file_hash"));
            e.setDisplay_name(DBUtil.getString(rs, "display_name"));
            e.setDescription(DBUtil.getString(rs, "description"));
            e.setSample_id(DBUtil.getLong(rs, "sample_id"));
            return e;
        }
    }

    @Autowired
    public JdbcExtraFileDAO(@Qualifier("dataSource") DataSource dataSource) {
        setDataSource(dataSource);
        extraFileRowMapper = new ExtraFileRowMapper();
    }

    // extraFile
    @Override
    public ExtraFileEntity readExtraFile(Long id) throws DataAccessException {
        String sql = "SELECT ef.* FROM extra_file ef WHERE ef.id = ?";
        List<ExtraFileEntity> list = getJdbcTemplate().query(sql, extraFileRowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long createExtraFile(final ExtraFileEntity e) throws DataAccessException {
        final String sql = "INSERT INTO extra_file (path, file_hash, display_name, description, sample_id) VALUES(?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        DBUtil.setString(ps, 1, e.getPath());
                        DBUtil.setString(ps, 2, e.getFile_hash());
                        DBUtil.setString(ps, 3, e.getDisplay_name());
                        DBUtil.setString(ps, 4, e.getDescription());
                        DBUtil.setLong(ps, 5, e.getSample_id());
                        return ps;
                    }
                },
                keyHolder) == 1) {
            return (Long) keyHolder.getKey();
        }

        return null;
    }

    @Override
    public boolean updateExtraFile(ExtraFileEntity e) throws DataAccessException {
        String sql = "UPDATE extra_file SET path = ?, file_hash = ?, display_name  = ?, description  = ?, sample_id  = ? WHERE id = ?";
        int res = getJdbcTemplate().update(sql, e.getPath(), e.getFile_hash(), e.getDisplay_name(), e.getDescription(), e.getSample_id(), e.getId());
        return res == 1;
    }

    @Override
    public List<ExtraFileEntity> listExtraFile() throws DataAccessException {
        String sql = "SELECT ef.* FROM extra_file ef";
        List<ExtraFileEntity> list = getJdbcTemplate().query(sql, extraFileRowMapper);
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<ExtraFileEntity> listExtraFile(Long sample_id) throws DataAccessException {
        String sql = "SELECT ef.* FROM extra_file ef WHERE ef.sample_id = ?";
        List<ExtraFileEntity> list = getJdbcTemplate().query(sql, extraFileRowMapper, sample_id);
        return list.isEmpty() ? null : list;
    }

    @Override
    public boolean deleteExtraFileBySample(Long sample_id) {
        String sql = "DELETE FROM extra_file WHERE sample_id = ?";
        int res = getJdbcTemplate().update(sql, sample_id);
        return res == 1;
    }
}
