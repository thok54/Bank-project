package exercise.repository;

import exercise.util.DataBaseUtil;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

public abstract class AbstractMySqlRepositoryTest {

    @Mock
    protected Connection connection;

    @Mock
    protected PreparedStatement preparedStatement;

    @Mock
    protected ResultSet resultSet;

    protected void executeQuery(DataBaseUtil util, String query) throws SQLException {
        when(util.startConnection()).thenReturn(connection);
        when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }
}
