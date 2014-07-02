package org.sistima.vivliothikis.web.yphresia;

import db_connection.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.StringTokenizer;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "Sistima_Vivliothikis_Web_Yphresies")
public class Sistima_Vivliothikis_Web_Yphresies {

    private static final String BOOK_REQUEST_INSERT = "INSERT INTO books_requests(book_request_title, book_request_ekdoseis, book_request_siggrafeis, "
            + " book_request_glwssa_grafis, book_request_etos_ekdosis) VALUES(?, ?, ?, ?, ?)";

    private static final String BOOK_RESERVATION_INSERT = "INSERT INTO kratiseis_vivliwn(kratisi_isbn, kratisi_titlos, kratisi_ekdoseis, kratisi_thlefwno)"
            + " VALUES(?, ?, ?, ?)";

    //==============================================================
    @WebMethod(operationName = "book_request_service")
    public boolean book_request_service(@WebParam(name = "infos") String infos) {

        PreparedStatement ps = null;
        try {
            Connection conn = DbConnection.getInstance().getConnection();
            ps = conn.prepareStatement(BOOK_REQUEST_INSERT);

            StringTokenizer st = new StringTokenizer(infos, "$");
            int i = 1;
            while (st.hasMoreTokens()) {
                ps.setString(i, st.nextToken());
                i++;
            }

            int rows = ps.executeUpdate();
            infos = null;
            return rows > 0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return false;
    }//book_request_service.

    @WebMethod(operationName = "book_reservation_service")
    public boolean book_reservation_service(@WebParam(name = "infos") String infos) {
        PreparedStatement ps = null;
        try {
            Connection conn = DbConnection.getInstance().getConnection();
            ps = conn.prepareStatement(BOOK_RESERVATION_INSERT);

            StringTokenizer st = new StringTokenizer(infos, "$");

            ps.setString(1, st.nextToken());
            ps.setString(2, st.nextToken());
            ps.setString(3, st.nextToken());
            ps.setString(4, st.nextToken());

            int rows = ps.executeUpdate();
            infos = null;
            return rows > 0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return false;
    }//book_reservation_service.

}//Sistima_Vivliothikis_Web_Yphresies.
