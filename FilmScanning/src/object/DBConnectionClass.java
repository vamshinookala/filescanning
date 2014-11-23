package object;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class DBConnectionClass {

	/**
	 * @param args
	 * @throws  ;
	 */
	private  Connection ObjConnection;
	
	public void  DataBaseConnectionClass() throws Exception{
		  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		  
		  String dbURL = "jdbc:sqlserver://10.99.1.54:1433;database=OMS_DPI_QA";
		  String userName = "VLTest"; 
		  String userPwd = "VLTest*";
		  ObjConnection = DriverManager.getConnection(dbURL,userName,userPwd);
		  System.out.println("connection exists"+ObjConnection);
		
		 
	 }
	
	public ArrayList<String> executeStatement(String QueryStatement) throws SQLException{
		ArrayList<String> ObjDBTabelData=new ArrayList<String>();
		Statement  objStatement =ObjConnection.createStatement();
		ResultSet objResultSet=objStatement.executeQuery(QueryStatement);
		ResultSetMetaData objResultSetMetaData = objResultSet.getMetaData();
		int numberOfColumns=objResultSetMetaData.getColumnCount();
		 while (objResultSet.next()) {
			 for (int i = 1; i <=numberOfColumns; i++) {
				 String value=objResultSet.getString(i);
				 if(value!=null){
				 String ObjDBValue=objResultSet.getString(i);
				 ObjDBTabelData.add(ObjDBValue);
				 }
			 }
		}
		return ObjDBTabelData;
	}	
		public String executeStatementForSingleValue(String QueryStatement) throws SQLException{
			String ReturnValue=null;
			Statement  objStatement =ObjConnection.createStatement();
			ResultSet objResultSet=objStatement.executeQuery(QueryStatement);
			ResultSetMetaData objResultSetMetaData = objResultSet.getMetaData();
			int numberOfColumns=objResultSetMetaData.getColumnCount();
			System.out.println(numberOfColumns);
			 while (objResultSet.next()) {
				 for (int i = 1; i <=numberOfColumns; i++) {
					 String value=objResultSet.getString(i);
					 if(value!=null){
					 ReturnValue=objResultSet.getString(i);
					 
					 }
				 }
			}
			return ReturnValue;
	}
		
		public int rowCount(String QueryStatement) throws SQLException
		{
			
			
			int size=0;
			Statement obStatement=ObjConnection.createStatement();
			ResultSet objResultSet=obStatement.executeQuery(QueryStatement);
			while(objResultSet.next())
			{
			 size++;
			}
			
			return size;
			
			
		}

public void  closeDataBase() throws SQLException{
	ObjConnection.close();
	System.out.println("Connection closed");
}

public void sample()
{
	String str="hi;this;count;sample";
	StringTokenizer stoken=new StringTokenizer(str,";");
	System.out.println(stoken.countTokens());;
}

public static void main(String[] args) throws Exception {
	
	DBConnectionClass db=new DBConnectionClass();
	db.sample();
	/*db.DataBaseConnectionClass();
	System.out.println("Row Count in the orders table"+"  "+db.rowCount("select * from BatchDetail where BatchId=81111"));
	db.closeDataBase();*/
	
	
	
}
}
