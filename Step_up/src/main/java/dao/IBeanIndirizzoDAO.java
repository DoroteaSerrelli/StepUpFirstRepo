package dao;

import java.sql.SQLException;
import java.util.Collection;

import model.IndirizzoDTO;

public interface IBeanIndirizzoDAO {

	public void doSave(IndirizzoDTO address, String username) throws SQLException;
	boolean doDeleteAddress(int IDAddress, String username) throws SQLException;
	public IndirizzoDTO doRetrieveByKey(int IDAddress, String username) throws SQLException;
	public Collection<IndirizzoDTO> doRetrieveAll(String order, String username) throws SQLException;	//tutti gli indirizzi dell'utente
	boolean doDelete(int IDIndirizzo) throws SQLException;
	
}