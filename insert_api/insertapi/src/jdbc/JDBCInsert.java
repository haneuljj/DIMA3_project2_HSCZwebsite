package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JDBCInsert {
    public static void main(String[] args) {
		// 접속을 위한 기본설정을 문자열로 세팅
		String dbid = "hr";  // 오라클 계정명
		String dbpw = "hr";  // 오라클 계정비번
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		String hs4digit;
		String imexYear;
		Long exportAmount;
        Long importAmount;

        List<String> HsList = new ArrayList<>(List.of("3924", "3926", "4015", "4016", "8479", "8481", "8517", "8536"));
        List<String> yearList = new ArrayList<>(List.of("202301", "202312", "202201", "202212", "202101", "202112", "202001", "202012", "201901", "201912"));
        // List<Map<String, String>> list = new ArrayList<>();
    
        String key = "JWkYtjoNYzmHaH4M8N5jMQchwqIzkrMG%2BRln8lAXqpt%2BzorzP%2BD%2FqDAnpgS1Z449YfiF61oQltHIk02nZ2%2BQWw%3D%3D";
    
        int yearTemp = 2023; // 초기 연도 설정
        
        for (int i = 0; i < HsList.size(); ++i) {
            for (int j = 0; j < yearList.size() - 1; j += 2) {
                try {
        
                    String apiURL = "https://apis.data.go.kr/1220000/Itemtrade/getItemtradeList?"
                            + "serviceKey=" + key // 인증키
                            + "&strtYymm=" + yearList.get(j)
                            + "&endYymm=" + yearList.get(j + 1)
                            + "&hsSgn=" + HsList.get(i);
                    

                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(apiURL);
                    doc.getDocumentElement().normalize();
        
                    NodeList nList = doc.getElementsByTagName("item");
                    //System.out.println("파싱할 리스트 수 : " + nList.getLength());
        
                    for (int temp = 0; temp < nList.getLength(); temp++) {
                        // Map<String, String> map = new HashMap<>();
        
                        Node nNode = nList.item(temp);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            if ("총계".equals(getTagValue("year", eElement))) {

                                hs4digit = HsList.get(i);
                                imexYear = String.valueOf(yearTemp);
                                exportAmount = Long.parseLong(getTagValue("expDlr", eElement));
                                importAmount = Long.parseLong(getTagValue("impDlr", eElement));
        
                                Connection conn = null;
                                PreparedStatement pstmt = null;
                                String query = """
                                        INSERT INTO year_imex5
                                        VALUES
                                        (
                                            year_seq.NEXTVAL
                                            , ?
                                            , ?
                                            , ?
                                            , ?
                                        )
                                        """;
            
                                try {
                                    Class.forName(driver);
                                    conn = DriverManager.getConnection(url, dbid, dbpw);
                                    System.out.println("DB 접속 성공");
                                    
                                    pstmt = conn.prepareStatement(query);
                                    pstmt.setString(1, hs4digit);
                                    pstmt.setString(2, imexYear);
                                    pstmt.setLong(3, exportAmount);
                                    pstmt.setLong(4, importAmount);
                                    
                                    int result = pstmt.executeUpdate();
                                    System.out.println(HsList.get(i) +  result + "개" +"저장 완료");
                                    pstmt.close();
                                    conn.close();
                                    
                                } catch (ClassNotFoundException | SQLException e) {
                                    System.out.println("DB 접속 실패!");
                                    e.printStackTrace();
                                }
        
                            }
                        }
                    }
                    yearTemp--; // 연도를 하나 감소시킴
        
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } // end of 두번째 for문
        } // end of 첫번째 for문
		
	}

    private static String getTagValue(String tag, Element eElement) {
        NodeList nodeList = eElement.getElementsByTagName(tag);
        if (nodeList != null && nodeList.getLength() > 0) {
            NodeList nlList = nodeList.item(0).getChildNodes();
            if (nlList != null && nlList.getLength() > 0) {
                Node nValue = nlList.item(0);
                if (nValue != null) {
                    return nValue.getNodeValue();
                }
            }
        }
        return null;
    }
}