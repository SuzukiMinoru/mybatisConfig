package com.yz.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.CHAR)
public class MyTypeHandler extends BaseTypeHandler<MyString> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, MyString parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getText());
    }

    @Override
    public MyString getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new MyString(rs.getString(columnName));

    }

    @Override
    public MyString getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new MyString(rs.getString(columnIndex));
    }

    @Override
    public MyString getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
       return new MyString(cs.getString(columnIndex));
    }
}
