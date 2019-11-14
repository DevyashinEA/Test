import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
/*
Класс Deposit содержит информацию о депозите, методы get/set для доступа к ним, а так же реализацию методов интерфейса DepositManager
* @param _indexDeposit индивидуальный номер депозита создаётся во время добавления нового депозита
* @param _typeOfDeposit тип депозита
* @param _dateCreateDeposit дата создания депозита
* @param _approvedByBank boolean переменная, обозначающая одобрен ли депозит банком
* @param _dateCloseDeposit дата закрытия депозита создаётся во время закрытия депозита
* @param  _termDays срок в днях хранения депозита
* @param  _percent годовой процент
* @param  _pretermPercent досрочный процент при досрочном изъятии вклада
* @param  _withPercentCapitalization присутствие процента капитализации
* @param _deposit начальная сумма депозита
* @param _incomeDeposit начисленная сумма депозита
* @param _client клиент, которому принадлежит депозит
*/
public class Deposit implements DepositManager
{
	private int _indexDeposit;
	private TypeOfDeposit _typeOfDeposit = TypeOfDeposit.StandartDeposit;
	private Date _dateCreateDeposit;
	private boolean _approvedByBank=false;
	private int _termDays;
	private double _percent;
	private double _pretermPercent;
	private boolean _withPercentCapitalization;
	private Date _dateCloseDeposit;
	private double _deposit;
	private double _earnings;
	private Client _client;
	/*
	 Метод возвращает _indexDeposit
	*/	
	public int getIndexDeposit()
	{
		return _indexDeposit;
	}
	/*
	 Метод записывает _indexDeposit
	*/	
	private void setIndexDeposit(Client client)
	{
		this._indexDeposit=Integer.parseInt (Integer.toString(client.getListDeposits().indexOf(this))+  //Порядковое значение в листе депозита клиента 
				Integer.toString(client.getIndexPerson()));												//склеивается с _indexPerson - получается _indexDeposit 
	}
	/*
	 Метод возвращает _typeOfDeposit
	*/	
	public TypeOfDeposit getTypeOfDeposit()
	{
		return _typeOfDeposit;
	}
	/*
	 Метод присваивает новое значение _typeOfDeposit
	*/	
	public void setTypeOfDeposit(TypeOfDeposit typeOfDeposit)
	{
		this._typeOfDeposit=typeOfDeposit;
	}
	/*
	 Метод возвращает _dateCreateDeposit
	*/	
	public Date getDateCreateDeposit()
	{
		return _dateCreateDeposit;
	}
	/*
	 Метод записывает _dateCreateDeposit
	*/	
	private void setDateCreateDeposit(Date dateCreateDeposit)
	{
		this._dateCreateDeposit=dateCreateDeposit;
	}
	/*
	 Метод возвращает _approvedByBank
	*/	
	public boolean getApprovedByBank()
	{
		return _approvedByBank;
	}
	/*
	 Метод присваивает новое значение _approvedByBank
	*/	
	public void setApprovedByBank(boolean approvedByBank)
	{
		this._approvedByBank=approvedByBank;
	}
	/*
	 Метод возвращает _termDays
	*/	
	public int getTermDays()
	{
		return _termDays;
	}
	/*
	 Метод присваивает новое значение _termDays
	*/	
	public void setTermDays(int termDays)
	{
		if (termDays<1)
	    	throw new IllegalArgumentException("term days deposit <1");
		else
			this._termDays=termDays;
	}
	/*
	 Метод присваивает новое значение _percent, если только оно не меньше 0
	*/
	public void setPercent(double percent)
	{
		if(percent<0)
	    	throw new IllegalArgumentException("percent  <0");
		else
			this._percent=percent;
	}
	/*
	 Метод возвращает _percent
	*/	
	public double getPercent()
	{
		return _percent;
	}
	/*
	 Метод присваивает новое значение _pretermPercent, если только оно не меньше 0
	*/
	public void setPretermPercent(double pretermPercent)
	{
		if(pretermPercent<0)
	    	throw new IllegalArgumentException("pretermPercent <0");
		else
			this._pretermPercent=pretermPercent;
	}
	/*
	 Метод возвращает _pretermPercent
	*/	
	public double getPretermPercent()
	{
		return _pretermPercent;
	}
	/*
	 Метод возвращает _withPercentCapitalization
	*/	
	public boolean getWithPercentCapitalization()
	{
		return _withPercentCapitalization;
	}
	/*
	 Метод присваивает новое значение _withPercentCapitalization
	*/	
	public void setWithPercentCapitalization(boolean withPercentCapitalization)
	{
		this._withPercentCapitalization=withPercentCapitalization;
	}
	/*
	 Метод присваивает новое значение _dateCloseDeposit, если только дата закрытия не младше даты открытия
	*/	
	private void setDateCloseDeposit(Date DateCloseDeposit)
	{
		if(_dateCreateDeposit.getTime()<DateCloseDeposit.getTime() || DateCloseDeposit==null)
			this._dateCloseDeposit=DateCloseDeposit;
		else
	    	throw new IllegalArgumentException("Date of close deposit is older than the previous date of create");
	}
	/*
	 Метод возвращает _dateCloseDeposit
	*/	
	public Date getDateCloseDeposit()
	{
		return _dateCloseDeposit;
	}
	/*
	 Метод присваивает новое значение _deposit
	*/	
	public void setDeposit(double deposit)
	{
		this._deposit=deposit;
	}
	/*
	 Метод возвращает _deposit
	*/	
	public double getDeposit()
	{
		return _deposit;
	}
	/*
	 Метод возвращает _client
	*/	
	public Client getClient()
	{
		return _client;
	}
	/*
	 Метод присваивает новое значение _client
	*/	
	public void setClient(Client client)
	{
		this._client=client;
	}
	@Override
	public ArrayList<Deposit> getClientDeposits(Client client,String token) 
	{
		this.getClient().getAccount().authorize(this.getClient().getAccount().getUserName(), this.getClient().getAccount().getPassword(), new Date());
	    return client.getListDeposits(); 							//Возвращается лист депозитов клиента
	}
	@Override
	public ArrayList<Deposit> getAllDeposits(Client client) 
	{
    	ArrayList<Deposit> deposits=new ArrayList<Deposit>();
    	for (Iterator<Deposit> j = client.getListDeposits().iterator(); j.hasNext();) //происходит перебор листа депозитов клиента
	    	if (j.next().getApprovedByBank()) 
	    		deposits.add(j.next()); 								 //записываются только те депозиты, которые одобрены банком
    	return deposits;
	}
	@Override
	public double getEarnings(Deposit deposit, Date currentDate,String token) 
	{
		this.getClient().getAccount().authorize(this.getClient().getAccount().getUserName(), this.getClient().getAccount().getPassword(), new Date());
		if (currentDate!=null)
		{
			if(_approvedByBank)
			{
				deposit.setDateCloseDeposit(currentDate);
		    	double percent=this.getPercent()/100; 						//перевод процентов в абсолютное значение
		    	double pretermPercent=this.getPretermPercent()/100; 		//перевод процентов в абсолютное значение
		    	_earnings=this.getDeposit();
		    	int startDay=this.getDateCreateDeposit().getDay();
		    	int nowDay=currentDate.getDay();
		    	if (nowDay-startDay==this.getTermDays())
		    		if (this.getWithPercentCapitalization())
		    		{
		    			for(int i = 1;i<=this.getTermDays()/30;i++)
		    			{
		    				_earnings=_earnings+_earnings*percent/12; 		//Вывод этого, если прошёл срок TermDays и присутствует WithPercentCapitalization
		    			}
		    		}
		    		else
		    			_earnings=_earnings+_earnings*percent;				//Вывод этого, если прошёл срок TermDays и отсутствует WithPercentCapitalization
		    	else
		    		_earnings=_earnings+_earnings*percent*pretermPercent;	//Вывод этого, если не прошёл срок TermDays. Расчёт по pretermPercent
		    	return Math.round(_earnings*100)/100;						//Округление до 2 знака после запятой
			}
			else
				throw new IllegalArgumentException("Deposit not approved by bank");			//Вывод ошибки, если депозит не был одорен банком
		}
		else
			return _earnings;
	}
	public void setEarnings(double earnings) 
	{
		this._earnings=earnings;
	}
	@Override
	public double removeDeposit(Deposit deposit, Date closeDate, String token) 
    {
		this.getClient().getAccount().authorize(this.getClient().getAccount().getUserName(), this.getClient().getAccount().getPassword(), new Date());
    	double income=deposit.getEarnings(deposit,closeDate, token);			 //Запись дохода
    	deposit.getClient().deleteDepositFromList(deposit.getClient().getListDeposits().indexOf(deposit));			 //Удаление депозита из списка и докумета
    	deposit=null;
    	return income;													 //Возвращение дохода
	}
	//метод для загрузки нового депозита имея все данные
	public Deposit addDeposit(TypeOfDeposit typeOfDeposit, Date dateCreateDeposit, 
			boolean approvedByBank, int termDays, double percent, double pretermPercent, boolean withPercentCapitalization,
			Date dateCloseDeposit, double deposit, double earnings, Client client) {
		this.setIndexDeposit(client);
		this.setDateCreateDeposit(dateCreateDeposit);
		this.setApprovedByBank(approvedByBank);
		this.setTermDays(termDays);
		this.setPercent(percent);
		this.setPretermPercent(pretermPercent);
		this.setWithPercentCapitalization(withPercentCapitalization);
    	this.setDateCloseDeposit(dateCloseDeposit);
    	this.setDeposit(deposit);
    	this.setEarnings(earnings);;
    	this.setClient(client);
		try 
		{
			client.setListDeposits(this);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return this;
	}
	@Override	//метод для добавления нового депозита из интерфейса
	public Deposit addDeposit(Client client, double ammount, double percent, double pretermPercent, int termDays,
			Date startDate, boolean withPercentCapitalization, String token) 
	{
    	this.setPercent(percent);
    	this.setDeposit(ammount);
    	this.setPretermPercent(pretermPercent);
    	this.setTermDays(termDays);
    	this.setDateCreateDeposit(startDate);
    	this.setWithPercentCapitalization(withPercentCapitalization);
    	this.setClient(client);
		try 
		{
			client.setListDeposits(this);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		this.setIndexDeposit(client);
		return this;
	}
}