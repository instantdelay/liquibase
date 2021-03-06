package liquibase.integration.ant.test

import groovy.sql.Sql
import org.apache.tools.ant.BuildException
import org.apache.tools.ant.ProjectComponent
import org.apache.tools.ant.taskdefs.condition.Condition

import java.sql.DatabaseMetaData
import java.sql.ResultSet

class ColumnExistsCondition extends ProjectComponent implements Condition {
    private String driver
    private String url
    private String user
    private String password
    private String table
    private String column

    @Override
    boolean eval() throws BuildException {
        Sql sql = null
        try {
            sql = Sql.newInstance(url, user, password, driver)
            DatabaseMetaData metaData = sql.getConnection().getMetaData()
            ResultSet columns = metaData.getColumns(null, null, table, column)
            return columns.next()
        } finally {
            sql?.close()
        }
    }

    void setDriver(String driver) {
        this.driver = driver
    }

    void setUrl(String url) {
        this.url = url
    }

    void setUser(String user) {
        this.user = user
    }

    void setPassword(String password) {
        this.password = password
    }

    void setTable(String table) {
        this.table = table
    }

    void setColumn(String column) {
        this.column = column
    }
}
