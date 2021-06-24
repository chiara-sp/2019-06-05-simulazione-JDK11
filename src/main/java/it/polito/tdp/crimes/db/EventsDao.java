package it.polito.tdp.crimes.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.crimes.model.Event;



public class EventsDao {
	
	public List<Event> listAllEvents(int anno, int mese, int giorno){
		String sql = "SELECT * FROM events "
				+ "where year(reported_date)=? and month(reported_date)=? and day(reported_date)=? "
				+ "order by reported_date" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			st.setInt(2,mese);
			st.setInt(3, giorno);
			
			List<Event> list = new LinkedList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	public List<Integer> getYears(){
		String sql="select distinct year(reported_date) as y "
				+ "from EVENTS "
				+ "order by y"; 
		List<Integer> result= new LinkedList<>();
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					result.add(res.getInt("y"));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return result ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	public List<Integer> getVertici(){
		String sql="select distinct district_id as d "
				+ "from events ";
		List<Integer> result= new LinkedList<>();
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					result.add(res.getInt("d"));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return result ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public double latMedia(Integer district, int anno) {
		String sql="select avg(geo_lat) as d "
				+ "from events "
				+ "where district_id= ? and year(reported_date)=?";
		double result=0;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, district);
			st.setInt(2, anno);
			
			ResultSet res = st.executeQuery() ;
			
			if(res.next()) {
				try {
					result= res.getDouble("d");
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return result ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0 ;
		}
	}
	public double lngMedia(Integer district, int anno) {
		String sql="select avg(geo_lon) as d "
				+ "from events "
				+ "where district_id= ? and year(reported_date)=?";
		double result=0;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, district);
			st.setInt(2, anno);
			
			ResultSet res = st.executeQuery() ;
			
			if(res.next()) {
				try {
					result= res.getDouble("d");
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return result ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0 ;
		}
	}
	public List<Integer> getMonths(int anno){
		String sql="select distinct month(reported_date) as y "
				+ "from EVENTS "
				+ "where year(reported_date)=? "
				+ "order by y"; 
		List<Integer> result= new LinkedList<>();
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					result.add(res.getInt("y"));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return result ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	public List<Integer> getDays(int anno, int mese){
		String sql="select distinct day(reported_date) as y "
				+ "from EVENTS "
				+ "where year(reported_date)=? and month(reported_date)=? "
				+ "order by y"; 
		List<Integer> result= new LinkedList<>();
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			st.setInt(2, mese);
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					result.add(res.getInt("y"));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return result ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	public int getDistrettoMigliore(int anno) {
		String sql="select district_id as id, count(*) as c "
				+ "from events "
				+ "where year(reported_date)=? "
				+ "group by `district_id`"
				+ "order by c";
		int result=0;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			
			ResultSet res = st.executeQuery() ;
			
			if(res.next()) {
				try {
					result= res.getInt("id");
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return result ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0 ;
		}
	}
}
