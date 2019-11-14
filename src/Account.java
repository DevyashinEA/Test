import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Account implements AccountManager
{
	private int _indexAccount=Math.floorDiv(new Date().getSeconds(), 100);
	private String  _userName="anonim";
	private String  _password="1111";
	private Client _client;
	private String _token;
	private Date _dateCreateAccaunt=new Date();
	private ArrayList<Date> _dateActivity=new ArrayList<Date>();
	{
		_dateActivity.add(_dateCreateAccaunt); 			//дата создания аккаунта становится первой записью в листе активности клиента
	}
	private ListClients listClients=new ListClients();	//необходимо доорганизвать загрузку списка клиентов!
	private ListAccounts listAccounts=new ListAccounts();	//необходимо доорганизвать загрузку списка клиентов!
	/*
	 Метод сравнивает даты. Если последняя запись в _dateActivity старше входной переменной Date - вывод ошибки
	*/	
	private void _checkWriteDateActivity(Date dateActivity) 
	{
		if (dateActivity.getTime()<_dateActivity.get(_dateActivity.size()-1).getTime())
	    	throw new IllegalArgumentException("Date of new activity is older than the previous date of activity");
	}
	/*
	 Метод возвращает лист _dateActivity
	*/
	public ArrayList<Date> getDateActivity()
	{
		return _dateActivity;
	}
	/*
	 Метод добавляет в лист _dateActivity новую dateActivity. 
	 В случае если метод _checkWriteDateActivity вернул false, выдаст предупреждение
	*/
	public void setDateActivity (Date dateActivity)
	{
		_checkWriteDateActivity(dateActivity);
		this._dateActivity.add(dateActivity);
	}
	/*
	 * Метод производит шифрование поступившей строки
	 */
	private static final String md5(final String toEncrypt) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(toEncrypt.getBytes());
            final byte[] bytes = digest.digest();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02X", bytes[i]));
            }
            return sb.toString().toLowerCase();
        } catch (Exception exc) {
            return ""; // Impossibru!
        }
    }
	/*
	 * Метод записывает новый_indexAccount
	 */
	public void setIndexAccount(int indexAccount)
	{
		this._indexAccount=indexAccount;
	}
	public int getIndexAccount()
	{
		return _indexAccount;
	}
	public void setUserName(String userName)
	{
		this._userName=userName;
	}
	public String getUserName()
	{
		return _userName;
	}
	public void setPassword(String password)
	{
		this._password=md5(password);
	}
	private void setExistPassword(String password)
	{
		this._password=password;
	}
	public String getPassword()
	{
		return _password;
	}
	public void setClient(Client client)
	{
		this._client=client;
	}
	public Client getClient()
	{
		return _client;
	}
	public void setToken(Date currentTime)
	{
		this._token=Token.getInstance(this, currentTime);
	}
	public String getToken()
	{
		return _token;
	}

	@Override
	public void addAccount(String userName, String password) 
	{
	}

	public void addAccount(int indexAccount, String userName, String password, int indexClient, int client) 
	{
		this.setIndexAccount(indexAccount);
		this.setUserName(userName);
		this.setExistPassword(password);
		for (int i = 0; i < listClients.getListClients().size(); i++) 
		{
			Client addClient = listClients.getListClients().get(i);
			if(addClient.getIndexPerson()==indexClient)
				this.setClient(addClient);
		}
	}

	@Override
	public void removeAccount(String userName, String password) 
	{
		for (int i = 0; i < listAccounts.getListAccounts().size(); i++) 
		{
			Account removeAccount = listAccounts.getListAccounts().get(i);
			if(removeAccount.getUserName()==userName && removeAccount.getPassword()==password)
				listAccounts.getListAccounts().remove(i);
		}
	}

	@Override
	public	ArrayList<Account> getAllAccounts() 
	{
		return listAccounts.getListAccounts();
	}

	@Override
	public String authorize(String userName, String password, Date currentTime) 
	{
		if(userName==this.getUserName() && this.md5(password)==this.getPassword())
		{
			this.setToken(currentTime);
		}
		else
		{
			userName=null; password=null;
			Scanner in = new Scanner(System.in);
		    System.out.print("Input a userName: ");
		    userName = in.nextLine();
		    System.out.print("Input a password: ");
		    password = in.nextLine();
		    this.authorize(userName, password, currentTime);
		}
		return this.getToken();
	}
	public static class Token 
	{
	    private static String instance;
	    private Token(){}
	    public static String getInstance(Account account, Date currentDate)
	    {
	    	long testToken = Integer.parseInt(account.getIndexAccount()+"1"+account.getDateActivity().get(currentDate.getMinutes()));
	        long infoToken = Integer.parseInt(instance);
		    if(testToken-infoToken>30 || testToken-infoToken<0)			//Если предыдущая авторизация вышла за 30минутный промежуток
		    {
		        account.authorize(null, null, currentDate);				//То вызов авторизации
		        long token = Integer.parseInt(account.getIndexAccount()+"1"+account.getDateActivity().get(account.getDateActivity().size()).getMinutes());
		        instance = Long.toString(token);						//И создание нового токена
		    }
		    return instance;											// вернуть ранее созданный объект
	    }
	}

}
