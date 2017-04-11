/*
 * ChanAccess.java
 *
 * Created on 2004년 5월 6일 (목), 오후 4:9
 */

package jdbc;

import java.sql.*;

/**
 *
 * @author  박성현
 */
public class ChanAccess {
    private String driverName = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
    private String connectionURL = "jdbc:microsoft:sqlserver://dev:1433;DatabaseName=MXDB";
    private Connection con = null;
    
    /** Creates a new instance of ChanAccess */
    public ChanAccess() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ChanAccess theApp = new ChanAccess();
        
        int 영향을받은수 = 0;
        
        try {
            theApp.connectDatabase();

            System.out.println("[목록]");
            theApp.listChan();

            System.out.println("\n[선택]");
            theApp.selectChan("Test");

            System.out.println("\n[삽입]");
            영향을받은수 = theApp.insertChan("Test2", "테스트2", "d:\\", "메롱");
            System.out.println(영향을받은수 + "개의 레코드가 영향을 받았습니다.");

            System.out.println("\n[수정]");
            영향을받은수 = theApp.updateChan("Test2", "테스트2다", "", "");
            System.out.println(영향을받은수 + "개의 레코드가 영향을 받았습니다.");

            System.out.println("\n[삭제]");
            영향을받은수 = theApp.deleteChan("Test2");
            System.out.println(영향을받은수 + "개의 레코드가 영향을 받았습니다.");
            
            theApp.disconnectDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void connectDatabase() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        
        con = DriverManager.getConnection(connectionURL,"sa","ibk");
    }
    
    private void listChan() throws SQLException {
        Statement stmt = con.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM TB_CHAN");
        
        System.out.println("CHAN_GB\tCHAN_NM\tREMOTE_DIR\tBIGO");
        while (rs.next())
        {
            System.out.println(rs.getString("chan_Gb") + "\t" + rs.getString("chan_Nm") + "\t" + rs.getString("remote_Dir") + "\t" + rs.getString("bigo"));
        }
        
        rs.close();
        stmt.close();
    }
    
    private void selectChan(String chanGb) throws SQLException {
        PreparedStatement pStmt = con.prepareStatement("SELECT * FROM TB_CHAN WHERE CHAN_GB=?");
        
        pStmt.setString(1, chanGb);
        
        ResultSet rs = pStmt.executeQuery();
        
        System.out.println("CHAN_GB\tCHAN_NM\tREMOTE_DIR\tBIGO");
        while (rs.next())
        {
            System.out.println(rs.getString("chan_Gb") + "\t" + rs.getString("chan_Nm") + "\t" + rs.getString("remote_Dir") + "\t" + rs.getString("bigo"));
        }

        rs.close();
        pStmt.close();
    }
    
    private int insertChan(String chanGb, String chanNm, String remoteDir, String bigo) throws SQLException {
        PreparedStatement pStmt = con.prepareStatement("INSERT INTO TB_CHAN VALUES (?, ?, ?, ?)");
        
        pStmt.setString(1, chanGb);
        pStmt.setString(2, chanNm);
        pStmt.setString(3, remoteDir);
        pStmt.setString(4, bigo);
        
        int cnt = pStmt.executeUpdate();
        
        pStmt.close();

        return cnt;
    }
    
    private int updateChan(String chanGb, String chanNm, String remoteDir, String bigo) throws SQLException {
        PreparedStatement pStmt = con.prepareStatement("UPDATE TB_CHAN SET CHAN_NM=?, REMOTE_DIR=?, BIGO=? WHERE CHAN_GB=?");
        
        pStmt.setString(1, chanNm);
        pStmt.setString(2, remoteDir);
        pStmt.setString(3, bigo);
        pStmt.setString(4, chanGb);
        
        int cnt = pStmt.executeUpdate();

        pStmt.close();
        
        return cnt;
    }
    
    private int deleteChan(String chanGb) throws SQLException {
        PreparedStatement pStmt = con.prepareStatement("DELETE TB_CHAN WHERE CHAN_GB=?");
        
        pStmt.setString(1, chanGb);
        
        int cnt = pStmt.executeUpdate();

        pStmt.close();

        return cnt;
    }
    
    private void disconnectDatabase() throws SQLException {
        con.close();
    }
}