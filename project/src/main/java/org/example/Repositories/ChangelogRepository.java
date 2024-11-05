package org.example.Repositories;

import org.example.Entities.ChangeLog;
import org.example.Models.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ChangelogRepository {

    // Метод для получения всех записей журнала
    public List<ChangeLog> getAllChangeLogs() {
        List<ChangeLog> changeLogs = new ArrayList<>();
        String query = "SELECT id, worker_id, change_time, work_time, changed_attributes FROM changelogs";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ChangeLog changeLog = mapResultSetToChangeLog(rs);
                changeLogs.add(changeLog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return changeLogs;
    }

    // Метод для получения записи журнала по ID
    public ChangeLog getChangeLogById(int id) {
        String query = "SELECT id, worker_id, change_time, work_time, changed_attributes FROM changelogs WHERE id = ?";
        ChangeLog changeLog = null;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                changeLog = mapResultSetToChangeLog(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return changeLog;
    }

    // Метод для добавления новой записи в журнал
    public boolean addChangeLog(ChangeLog changeLog) {
        String query = "INSERT INTO changelogs (worker_id, change_time, work_time, changed_attributes) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            setChangeLogParams(pstmt, changeLog);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для обновления записи журнала по ID
    public boolean updateChangeLog(ChangeLog changeLog) {
        String query = "UPDATE changelogs SET worker_id = ?, change_time = ?, work_time = ?, changed_attributes = ? " +
                "WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            setChangeLogParams(pstmt, changeLog);
            pstmt.setInt(5, changeLog.getId()); // Устанавливаем ID записи
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для удаления записи журнала по ID
    public boolean deleteChangeLogById(int id) {
        String query = "DELETE FROM changelogs WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Вспомогательный метод для маппинга ResultSet на ChangeLog
    private ChangeLog mapResultSetToChangeLog(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int workerId = rs.getInt("worker_id");
        Date changeTime = rs.getTimestamp("change_time");
        int workTime = rs.getInt("work_time");
        String changedAttributes = rs.getString("changed_attributes");

        return new ChangeLog(id, workerId, changeTime, workTime, changedAttributes);
    }

    // Вспомогательный метод для установки параметров PreparedStatement
    private void setChangeLogParams(PreparedStatement pstmt, ChangeLog changeLog) throws SQLException {
        pstmt.setInt(1, changeLog.getWorkerId());
        pstmt.setTimestamp(2, new Timestamp(changeLog.getChangeTime().getTime()));
        pstmt.setInt(3, changeLog.getWorkTime());
        pstmt.setString(4, changeLog.getChangedAttributes());
    }
}
