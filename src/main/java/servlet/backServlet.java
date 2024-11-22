package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Status; // Status クラスがある前提

@WebServlet("/backServlet")
public class backServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 必要なパラメータをリクエストから取得
    	 String name = request.getParameter("name");
         int id = Integer.parseInt(request.getParameter("id"));
         int hp = Integer.parseInt(request.getParameter("hp"));
         int attack = Integer.parseInt(request.getParameter("attack"));
         int defense = Integer.parseInt(request.getParameter("defense"));
         int speed = Integer.parseInt(request.getParameter("speed"));
         String item = request.getParameter("item");
         String itemEffect = request.getParameter("itemEffect");
         

         // Statusオブジェクトを作成
         Status status = new Status(name, id, hp, attack, defense, speed, item, itemEffect);
         
         System.out.println(name);
         
        // Status オブジェクトをリクエスト属性にセット
        request.setAttribute("status", status);

        // mypage.jsp にフォワード
        request.getRequestDispatcher("/jsp/mypage.jsp").forward(request, response);
    }

}
