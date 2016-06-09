package com.bsu.mvt.server.core.util;

import java.sql.*;

public class DBUtil {
    public static void setDouble(PreparedStatement ps, int index, Double value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.DOUBLE);
        } else {
            ps.setDouble(index, value);
        }

    }

    public static void setFloat(PreparedStatement ps, int index, Float value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.FLOAT);
        } else {
            ps.setFloat(index, value);
        }

    }

    public static void setLong(PreparedStatement ps, int index, Long value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.BIGINT);
        } else {
            ps.setLong(index, value);
        }

    }

    public static void setInteger(PreparedStatement ps, int index, Integer value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.INTEGER);
        } else {
            ps.setInt(index, value);
        }

    }

    public static void setString(PreparedStatement ps, int index, String value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.VARCHAR);
        } else {
            ps.setString(index, value);
        }
    }

    public static void setStringAsChar(PreparedStatement ps, int index, String value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.CHAR);
        } else {
            ps.setString(index, value);
        }
    }

    public static void setDate(PreparedStatement ps, int index, java.util.Date value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.TIMESTAMP);
        } else {
            ps.setTimestamp(index, new Timestamp(value.getTime()));
        }
    }

    public static void setBoolean(PreparedStatement ps, int index, Boolean value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.BOOLEAN);
        } else {
            ps.setBoolean(index, value);
        }
    }

    public static Double getDouble(ResultSet rs, String fieldName) throws SQLException {
        if (rs.getObject(fieldName) == null) {
            return null;
        }
        return rs.getDouble(fieldName);
    }

    public static Float getFloat(ResultSet rs, String fieldName) throws SQLException {
        if (rs.getObject(fieldName) == null) {
            return null;
        }
        return rs.getFloat(fieldName);
    }

    public static Long getLong(ResultSet rs, String fieldName) throws SQLException {
        if (rs.getObject(fieldName) == null) {
            return null;
        }
        return rs.getLong(fieldName);
    }

    public static Integer getInt(ResultSet rs, String fieldName) throws SQLException {
        if (rs.getObject(fieldName) == null) {
            return null;
        }
        return rs.getInt(fieldName);
    }

    public static String getString(ResultSet rs, String fieldName) throws SQLException {
        if (rs.getObject(fieldName) == null) {
            return null;
        }
        return rs.getString(fieldName);
    }

    public static java.util.Date getDate(ResultSet rs, String fieldName) throws SQLException {
        if (rs.getObject(fieldName) == null) {
            return null;
        }
        return getDate(rs.getTimestamp(fieldName));
    }

    public static java.util.Date getDate(java.util.Date date) {
        return date == null ? null : new java.util.Date(date.getTime());
    }

    public static Boolean getBoolean(ResultSet rs, String fieldName) throws SQLException {
        return rs.getObject(fieldName) != null && rs.getBoolean(fieldName);
    }
}
