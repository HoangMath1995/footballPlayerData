package com.example.dao;

import com.example.entity.Indexer;
import com.example.entity.PlayerInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    // Phương thức thêm cầu thủ mới và chỉ số của họ
    public void addPlayer(String playerName, int playerAge, int indexId, float value) throws SQLException, ClassNotFoundException {
        // Validation (Câu hỏi 6)
        Indexer indexer = getIndexerById(indexId);
        if (value < indexer.getValueMin() || value > indexer.getValueMax()) {
            throw new IllegalArgumentException("Value is out of range for the selected index.");
        }

        Connection conn = null;
        PreparedStatement psPlayer = null;
        PreparedStatement psPlayerIndex = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction

            // 1. Kiểm tra xem cầu thủ đã tồn tại chưa, nếu chưa thì thêm mới
            int playerId = getPlayerIdByNameAndAge(playerName, playerAge);
            if (playerId == -1) {
                String insertPlayerSQL = "INSERT INTO player (name, age) VALUES (?, ?)";
                psPlayer = conn.prepareStatement(insertPlayerSQL, Statement.RETURN_GENERATED_KEYS);
                psPlayer.setString(1, playerName);
                psPlayer.setInt(2, playerAge);
                psPlayer.executeUpdate();
                rs = psPlayer.getGeneratedKeys();
                if (rs.next()) {
                    playerId = rs.getInt(1);
                }
            }

            // 2. Thêm thông tin vào bảng player_index
            String insertPlayerIndexSQL = "INSERT INTO player_index (player_id, index_id, value) VALUES (?, ?, ?)";
            psPlayerIndex = conn.prepareStatement(insertPlayerIndexSQL);
            psPlayerIndex.setInt(1, playerId);
            psPlayerIndex.setInt(2, indexId);
            psPlayerIndex.setFloat(3, value);
            psPlayerIndex.executeUpdate();

            conn.commit(); // Hoàn tất transaction
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback nếu có lỗi
            }
            throw e;
        } finally {
            // Đóng tài nguyên
            if (rs != null) rs.close();
            if (psPlayer != null) psPlayer.close();
            if (psPlayerIndex != null) psPlayerIndex.close();
            if (conn != null) conn.close();
        }
    }

    // (Thêm vào trong lớp PlayerDAO)

    // Câu hỏi 3: Lấy tất cả thông tin cầu thủ để hiển thị
    public List<PlayerInfo> getAllPlayerInfo() throws SQLException, ClassNotFoundException {
        List<PlayerInfo> playerInfos = new ArrayList<>();
        String sql = "SELECT pi.id, p.name as playerName, p.age, i.name as indexName, pi.value " +
                "FROM player_index pi " +
                "JOIN player p ON pi.player_id = p.player_id " +
                "JOIN indexer i ON pi.index_id = i.index_id ORDER BY pi.id";
        try (Connection conn = DBContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Tạo đối tượng PlayerInfo và thêm vào danh sách
            }
        }
        return playerInfos;
    }

    // Lấy tất cả các chỉ số (để hiển thị trong dropdown)
    public List<Indexer> getAllIndexers() throws SQLException, ClassNotFoundException {
        List<Indexer> indexers = new ArrayList<>();
        // ... logic để lấy dữ liệu từ bảng indexer
        return indexers;
    }

    // Câu hỏi 5: Xóa một bản ghi trong player_index
    public void deletePlayerIndex(int playerIndexId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM player_index WHERE id = ?";
        // ... logic để xóa
    }

    // Câu hỏi 4: Lấy thông tin chi tiết của một bản ghi để sửa
    public PlayerInfo getPlayerInfoById(int playerIndexId) throws SQLException, ClassNotFoundException {
        PlayerInfo playerInfo = null;
        String sql = "SELECT pi.id, p.name as playerName, p.age, i.name as indexName, pi.value, p.player_id, i.index_id " +
                "FROM player_index pi " +
                "JOIN player p ON pi.player_id = p.player_id " +
                "JOIN indexer i ON pi.index_id = i.index_id WHERE pi.id = ?";
        // ... logic để lấy dữ liệu
        return playerInfo;
    }

    // Câu hỏi 4 & 6: Cập nhật thông tin và validation
    public void updatePlayerIndex(int playerIndexId, String playerName, int playerAge, int indexId, float value) throws SQLException, ClassNotFoundException {
        // Validation (Câu hỏi 6)
        Indexer indexer = getIndexerById(indexId);
        if (value < indexer.getValueMin() || value > indexer.getValueMax()) {
            throw new IllegalArgumentException("Value is out of range for the selected index.");
        }

        // Logic cập nhật:
        // 1. Lấy player_id từ player_index_id
        // 2. Cập nhật bảng player
        // 3. Cập nhật bảng player_index
    }

    // Phương thức hỗ trợ để lấy Indexer bằng ID (dùng cho validation)
    private Indexer getIndexerById(int indexId) throws SQLException, ClassNotFoundException {
        // ... logic để lấy Indexer
    }

    // Phương thức hỗ trợ để lấy Player ID bằng tên và tuổi
    private int getPlayerIdByNameAndAge(String name, int age) throws SQLException, ClassNotFoundException {
        // ... logic để tìm player
    }
}
