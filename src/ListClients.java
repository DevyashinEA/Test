import java.util.ArrayList;
/*
 * Класс ListClients содержит список всех клиентов
 */
public class ListClients 
{
	private ArrayList<Client> _allClients=new ArrayList<Client>();
	public ArrayList<Client> getListClients()
	{
		return _allClients;
	}
	public void setListClients(Client client)
	{
		this._allClients.add(client);
	}
}
