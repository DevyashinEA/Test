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
����� Client �������� ���������� � �������
* @param _indexPerson �������������� ����� �������
* @param _firstName ��� ������� �� ����� ���� ������ 2 � ������ 50 ��������� ��������
* @param _surname ������� ������� �� ����� ���� ������ 2 � ������ 50 ��������� ��������
* @param _patronymic �������� ������� �� ����� ���� ������ 1 ��� ������ 50 ��������� ��������, ����� ��������� �������� null
* @param _dateCreateAccaunt ���� �������� �������� �������, �������� �������������
* @param _dateActivity ���� ���������� ���� ����������� �������
* @param _listDeposits ���� ��������� �������
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
	����� ��������� ������� ���������� indexPerson �� ������������ ����� 
	@param check ������������ boolean ����������
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
	 ����� ��������� ������� ���������� name �� ������ (>=2 � <=50) � �������������� ������ ���� ��������� ��������
	 � ������ ������� ��������- ����� ������
	@param check ������������ boolean ����������
    */
	private boolean _checkWriteName(String name) 
	{
		boolean check=false;
	    if (name.matches("[�-��-�]+") || name.matches("[a-zA-z]+"))
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
	 ����� ��������� ������� ���������� patronymic �� ������ (>=2 � <=50 ��� ���������� �����) � �������������� ������ ���� ��������� ��������
	 � ������ ������� ��������- ����� ������
	@param check ������������ boolean ����������
    */
	private boolean _checkWritePatronymic(String patronymic) 
	{
		boolean check=false;
	    if (patronymic.matches("[�-��-�]+") || patronymic.matches("[a-zA-z]+"))
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
	 ����� ���������� _indexPerson
	*/
	public int getIndexPerson()
	{
		return _indexPerson;
	}
	/*
	 ����� ���������� ����� _indexPerson, ���� ������ ����� _checkIndexPerson ������ true
	*/
	public void setIndexPerson (int indexPerson)
	{
		if(_checkIndexPerson(indexPerson))
			this._indexPerson=indexPerson;
	}
	/*
	 ����� ���������� _indexPerson
	*/
	public Account getAccount()
	{
		return _account;
	}
	/*
	 ����� ���������� ����� _indexPerson, ���� ������ ����� _checkIndexPerson ������ true
	*/
	public void setAccount (Account account)
	{
		this._account=account;
	}
	/*
	 ����� ���������� _firstName
	*/
	public String getFirstName()
	{
		return _firstName;
	}
	/*
	 ����� ���������� ����� _firstName, ���� ������ ����� _checkWriteName ������ true
	*/
	public void setFirstName (String firstName)
	{
		if(_checkWriteName(firstName))
			this._firstName=firstName;
	}
	/*
	 ����� ���������� _surname
	*/
	public String getSurname()
	{
		return _surname;
	}
	/*
	 ����� ���������� ����� surname, ���� ������ ����� _checkWriteName ������ true
	*/
	public void setSurname (String surname)
	{
		if(_checkWriteName(surname))
			this._surname=surname;
	}
	/*
	 ����� ���������� _patronymic
	*/
	public String getPatronymic()
	{
		return _patronymic;
	}
	/*
	 ����� ���������� ����� patronymic, ���� ������ ����� _checkWritePatronymic ������ true
	*/
	public void setPatronymic (String patronymic)
	{
		if(_checkWritePatronymic(patronymic))
			this._patronymic=patronymic;
	}
	/*
	 ����� ���������� _dateCreateAccaunt
	*/
	public Date getDateCreateAccaunt()
	{
		return _dateCreateAccaunt;
	}
	/*
	 ����� ��������� ���� ��������� ������� _listDeposits �� ����� .csv
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
	 ����� ��������� � ���� _listDeposits ����� deposit, ��������� ��� � csv
	*/
	public void setListDeposits (Deposit deposit) throws IOException
	{
		String stringeDeposit=deposit.getIndexDeposit() + "," + deposit.getTypeOfDeposit() + "," + deposit.getDateCreateDeposit() +
				"," + deposit.getApprovedByBank() +"," + deposit.getTermDays() +"," + deposit.getPercent() +
				"," + deposit.getPretermPercent() +"," + deposit.getWithPercentCapitalization() +"," + deposit.getDateCloseDeposit() +
				"," + deposit.getDeposit() +"," + deposit.getEarnings(deposit, null,deposit.getClient().getAccount().getToken()) +"," + deposit.getClient();
	    String [] record = stringeDeposit.split(",");						//������ �������� � String ������
		
		String csv = "ClientDeposits"+this.getIndexPerson()+".csv";			//����������� ����� csv
		try 
		{
			CSVWriter writer = new CSVWriter(new FileWriter(csv, true));	//�������� �����
		    writer.writeNext(record);										//������ ��������
		    writer.close();
		}
		finally
		{
			Files.newBufferedWriter(Paths.get(csv));						//� ������ ����������-�������� �����
			CSVWriter writer = new CSVWriter(new FileWriter(csv, true));	//��������
		    writer.writeNext(record);										//������ ��������
		    writer.close();
		}
	}
	public ArrayList<Deposit> deleteDepositFromList(int i)
	{
		String csv = "ClientDeposits"+this.getIndexPerson()+".csv";			//����������� ����� csv
		try 
		{	
			CSVReader reader2 = new CSVReader(new FileReader(csv));			//�������� �����
			ArrayList<String[]> allElements = (ArrayList<String[]>) reader2.readAll();
			allElements.remove(i);											//�������� �������� �� �������
			FileWriter sw = new FileWriter(csv);
			CSVWriter writer = new CSVWriter(sw);							
			writer.writeAll(allElements);									//���������� ����� �����
			writer.close();
		}
		finally
		{
			throw new NullPointerException("Error load file");
		}
	}
}