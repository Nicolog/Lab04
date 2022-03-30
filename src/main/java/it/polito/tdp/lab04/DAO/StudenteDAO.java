package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public String[] getNomeCognome(int matricola) {
		String sql = "select nome , cognome\r "
				+ "from studente\r "
				+ "where matricola = ?";
		String result[] = {"",""};
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				result[0]= rs.getString("nome");
				result[1]= rs.getString("cognome");
			}
			st.close();
			rs.close();
			conn.close();
			
			return result;
			
		}catch(SQLException e){
			System.err.println("ERRORE nel DAO");
			return null;
		}
		
	}
	
	public List<Corso> getCorsiIscritto(int matricola){
		String sql = "select c.codins, c.crediti, c.nome , c.pd\r "
				+ "from corso c, studente s, iscrizione i\r "
				+ "where s.matricola= ? AND c.codins=i.codins AND i.matricola=s.matricola";
		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				corsi.add(c);
			}
		
			conn.close();
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public boolean isCorrect(int matricola){
		String sql = "select matricola "
				+ "from studente ";
		List<Integer> matricole = new LinkedList<Integer>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				matricole.add(rs.getInt("matricola"));
			}
		
			conn.close();
			if(matricole.contains(matricola))
				return true;
			else
				return false;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
}
