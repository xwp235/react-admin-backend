package com.xwplay.crm.common.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwplay.crm.common.handler.types.MenuName;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@RequiredArgsConstructor
public class MenuNameTypeHandler extends BaseTypeHandler<MenuName> {

    private final ObjectMapper objectMapper;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, MenuName parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            throw new SQLException("Error converting MenuName to String: " + e.getMessage());
        }
    }

    @Override
    public MenuName getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getMenuName(rs.getString(columnName));
    }

    @Override
    public MenuName getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getMenuName(rs.getString(columnIndex));
    }

    @Override
    public MenuName getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getMenuName(cs.getString(columnIndex));
    }

    private MenuName getMenuName(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonString, MenuName.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error parsing JSON: " + e.getMessage());
        }
    }
}
