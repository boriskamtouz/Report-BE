package com.wcs.report.helpers;

import org.hibernate.resource.jdbc.spi.StatementInspector;

public class QueryLimitInterceptor implements StatementInspector {
    @Override
    public String inspect(String sql) {
        if (sql.toLowerCase().startsWith("select") &&
                !sql.toLowerCase().contains("limit")) {
            sql = sql + " LIMIT 100";
            return sql;
        }
        return sql;
    }
}
