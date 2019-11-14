import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
/*
 * Класс ListClients содержит список всех клиентов
 */
public class ListAccounts 
{
	private ArrayList<Account> _allAccount=new ArrayList<Account>();
	public ArrayList<Account> getListAccounts()
	{
		return _allAccount;
	}
	public void setListAccounts(Account account)
	{
		this._allAccount.add(account);
	}
}
