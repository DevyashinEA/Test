import java.util.ArrayList;
import java.util.Date;
import java.io.FileReader;
import java.io.FileWriter;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.CSVReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/*
Класс Client содержит информацию о клиенте
* @param _indexPerson индивидуальный номер клиента
* @param _firstName имя клиента не может быть меньше 2 и больше 50 буквенных символов
* @param _surname фамилия клиента не может быть меньше 2 и больше 50 буквенных символов
* @param _patronymic отчество клиента не может быть равным 1 или больше 50 буквенных символов, может принимать значение null
* @param _dateCreateAccaunt дата создания аккаунта клиента, создаётся автоматически
* @param _dateActivity лист содержащий даты активностей клиента
* @param _listDeposits лист депозитов клиента
*/
public class Client
{
	private int _indexPerson;
	private Account _account;
	private String  _firstName="anonim";
	private String  _surname="anonimus";
	private String  _patronymic;
	private Date _dateCreateAccaunt=new Date();
	private ArrayList<Deposit> _listDeposits=new ArrayList<Deposit>();
	/*
	Метод проверяет входную переменную indexPerson на правильность ввода 
	@param check возвращаемая boolean переменная
   */
	private boolean _checkIndexPerson (int indexPerson)
	{
		boolean check=false;
		if(indexPerson>=100000 && indexPerson<=999999)
			check=true;
		else
			throw new IllegalArgumentException("incorrect index");
		return check;
	}
	/*
	 Метод проверяет входную переменную name на длинну (>=2 и <=50) и принадлежность только лишь буквенным символам
	 в случае провала проверки- вывод ошибки
	@param check возвращаемая boolean переменная
    */
	private boolean _checkWriteName(String name) 
	{
		boolean check=false;
	    if (name.matches("[а-яА-Я]+") || name.matches("[a-zA-z]+"))
	    {
	    	if (name.length()<=50 && name.length()>=2)
	    	{
	    		check=true;
	    	}
	    	else
	    	{
				throw new IllegalArgumentException("value length 'FirstName' exceeds 50 or less than 2");
	    	}
	    }
	    else 
	    {
	    	throw new IllegalArgumentException("The text field value is not literal");
	    }
		return check;
	}
	/*
	 Метод проверяет входную переменную patronymic на длинну (>=2 и <=50 или переменная пуста) и принадлежность только лишь буквенным символам
	 в случае провала проверки- вывод ошибки
	@param check возвращаемая boolean переменная
    */
	private boolean _checkWritePatronymic(String patronymic) 
	{
		boolean check=false;
	    if (patronymic.matches("[а-яА-Я]+") || patronymic.matches("[a-zA-z]+"))
	    {
	    	if (patronymic.length()<=50 && patronymic.length()>=2 || patronymic.length()==0)
	    	{
	    		check=true;
	    	}
	    	else
	    	{
				throw new IllegalArgumentException("value length 'FirstName' exceeds 50 or less than 2");
	    	}
	    }
	    else 
	    {
	    	throw new IllegalArgumentException("The text field value is not literal");
	    }
	    return check;
	}
	/*
	 Метод возвращает _indexPerson
	*/
	public int getIndexPerson()
	{
		return _indexPerson;
	}
	/*
	 Метод записывает новый _indexPerson, если только метод _checkIndexPerson вернул true
	*/
	public void setIndexPerson (int indexPerson)
	{
		if(_checkIndexPerson(indexPerson))
			this._indexPerson=indexPerson;
	}
	/*
	 Метод возвращает _indexPerson
	*/
	public Account getAccount()
	{
		return _account;
	}
	/*
	 Метод записывает новый _indexPerson, если только метод _checkIndexPerson вернул true
	*/
	public void setAccount (Account account)
	{
		this._account=account;
	}
	/*
	 Метод возвращает _firstName
	*/
	public String getFirstName()
	{
		return _firstName;
	}
	/*
	 Метод записывает новый _firstName, если только метод _checkWriteName вернул true
	*/
	public void setFirstName (String firstName)
	{
		if(_checkWriteName(firstName))
			this._firstName=firstName;
	}
	/*
	 Метод возвращает _surname
	*/
	public String getSurname()
	{
		return _surname;
	}
	/*
	 Метод записывает новый surname, если только метод _checkWriteName вернул true
	*/
	public void setSurname (String surname)
	{
		if(_checkWriteName(surname))
			this._surname=surname;
	}
	/*
	 Метод возвращает _patronymic
	*/
	public String getPatronymic()
	{
		return _patronymic;
	}
	/*
	 Метод записывает новый patronymic, если только метод _checkWritePatronymic вернул true
	*/
	public void setPatronymic (String patronymic)
	{
		if(_checkWritePatronymic(patronymic))
			this._patronymic=patronymic;
	}
	/*
	 Метод возвращает _dateCreateAccaunt
	*/
	public Date getDateCreateAccaunt()
	{
		return _dateCreateAccaunt;
	}
	/*
	 Метод загружает лист депозитов клиента _listDeposits из файла .csv
	*/
	public ArrayList<Deposit> getListDeposits()
	{
		try {
			CSVReader csvReader = new CSVReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("ClientDeposits"+this.getIndexPerson()+".csv")));
	        String[] nextRecord;
	        DateFormat format = new SimpleDateFormat("dd, MM, yyyy");
	        while ((nextRecord = csvReader.readNext()) != null) 
	        {
	        	Deposit newDeposit= new Deposit();
	        	newDeposit.addDeposit(TypeOfDeposit.valueOf(nextRecord[0]), format.parse(nextRecord[1]), 
	        			Boolean.valueOf(nextRecord[2]), Integer.parseInt(nextRecord[3]), Double.valueOf(nextRecord[4]), Double.valueOf(nextRecord[5]), 
	        			Boolean.valueOf(nextRecord[6]), format.parse(nextRecord[7]), Double.valueOf(nextRecord[8]), Double.valueOf(nextRecord[8]), this);
	        }
	        csvReader.close();
			return _listDeposits;
		}
		finally
		{
			throw new NullPointerException("Error load file");
		}
	}
	/*
	 Метод добавляет в лист _listDeposits новый deposit, записывая его в csv
	*/
	public void setListDeposits (Deposit deposit) throws IOException
	{
		String stringeDeposit=deposit.getIndexDeposit() + "," + deposit.getTypeOfDeposit() + "," + deposit.getDateCreateDeposit() +
				"," + deposit.getApprovedByBank() +"," + deposit.getTermDays() +"," + deposit.getPercent() +
				"," + deposit.getPretermPercent() +"," + deposit.getWithPercentCapitalization() +"," + deposit.getDateCloseDeposit() +
				"," + deposit.getDeposit() +"," + deposit.getEarnings(deposit, null,deposit.getClient().getAccount().getToken()) +"," + deposit.getClient();
	    String [] record = stringeDeposit.split(",");						//Запись депозита в String массив
		
		String csv = "ClientDeposits"+this.getIndexPerson()+".csv";			//Определение имени csv
		try 
		{
			CSVWriter writer = new CSVWriter(new FileWriter(csv, true));	//Открытие файла
		    writer.writeNext(record);										//Запись депозита
		    writer.close();
		}
		finally
		{
			Files.newBufferedWriter(Paths.get(csv));						//В случае отсутствия-создание файла
			CSVWriter writer = new CSVWriter(new FileWriter(csv, true));	//Открытие
		    writer.writeNext(record);										//Запись депозита
		    writer.close();
		}
	}
	public ArrayList<Deposit> deleteDepositFromList(int i)
	{
		String csv = "ClientDeposits"+this.getIndexPerson()+".csv";			//Определение имени csv
		try 
		{	
			CSVReader reader2 = new CSVReader(new FileReader(csv));			//Открытие файла
			ArrayList<String[]> allElements = (ArrayList<String[]>) reader2.readAll();
			allElements.remove(i);											//Удаление депозита по индексу
			FileWriter sw = new FileWriter(csv);
			CSVWriter writer = new CSVWriter(sw);							
			writer.writeAll(allElements);									//Перезапись всего файла
			writer.close();
		}
		finally
		{
			throw new NullPointerException("Error load file");
		}
	}
}