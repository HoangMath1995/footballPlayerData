package com.example.controller;

import com.example.dao.PlayerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/PlayerServlet")
public class PlayerServlet extends HttpServlet {
    private PlayerDAO playerDAO;

    public void init() {
        playerDAO = new PlayerDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Mặc định là hiển thị danh sách
        }

        try {
            switch (action) {
                case "add":
                    addPlayer(request, response);
                    break;
                case "delete":
                    deletePlayer(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updatePlayer(request, response);
                    break;
                default:
                    listPlayers(request, response);
                    break;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    // Câu hỏi 3: Đọc và hiển thị tất cả cầu thủ
    private void listPlayers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        List<PlayerInfo> players = playerDAO.getAllPlayerInfo();
        List<Indexer> indexers = playerDAO.getAllIndexers();
        request.setAttribute("players", players);
        request.setAttribute("indexers", indexers);
        request.getRequestDispatcher("player.jsp").forward(request, response);
    }

    // Câu hỏi 2: Thêm cầu thủ mới
    private void addPlayer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        String playerName = request.getParameter("playerName");
        int playerAge = Integer.parseInt(request.getParameter("playerAge"));
        int indexId = Integer.parseInt(request.getParameter("indexId"));
        float value = Float.parseFloat(request.getParameter("value"));

        try {
            playerDAO.addPlayer(playerName, playerAge, indexId, value);
        } catch (IllegalArgumentException e) {
            // Xử lý lỗi validation (Câu hỏi 6)
            request.getSession().setAttribute("errorMessage", e.getMessage());
        }
        response.sendRedirect("PlayerServlet");
    }

    // Câu hỏi 5: Xóa cầu thủ
    private void deletePlayer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        playerDAO.deletePlayerIndex(id);
        response.sendRedirect("PlayerServlet");
    }

    // Câu hỏi 4: Hiển thị form sửa thông tin
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PlayerInfo existingPlayer = playerDAO.getPlayerInfoById(id);
        List<Indexer> indexers = playerDAO.getAllIndexers();
        request.setAttribute("player", existingPlayer);
        request.setAttribute("indexers", indexers);
        request.getRequestDispatcher("editPlayer.jsp").forward(request, response);
    }

    // Câu hỏi 4: Cập nhật thông tin cầu thủ
    private void updatePlayer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        int playerIndexId = Integer.parseInt(request.getParameter("id"));
        String playerName = request.getParameter("playerName");
        int playerAge = Integer.parseInt(request.getParameter("playerAge"));
        int indexId = Integer.parseInt(request.getParameter("indexId"));
        float value = Float.parseFloat(request.getParameter("value"));

        try {
            playerDAO.updatePlayerIndex(playerIndexId, playerName, playerAge, indexId, value);
        } catch (IllegalArgumentException e) {
            // Xử lý lỗi validation (Câu hỏi 6)
            request.getSession().setAttribute("errorMessage", e.getMessage());
        }
        response.sendRedirect("PlayerServlet");
    }
}
