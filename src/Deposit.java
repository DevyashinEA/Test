import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
/*
����� Deposit �������� ���������� � ��������, ������ get/set ��� ������� � ���, � ��� �� ���������� ������� ���������� DepositManager
* @param _indexDeposit �������������� ����� �������� �������� �� ����� ���������� ������ ��������
* @param _typeOfDeposit ��� ��������
* @param _dateCreateDeposit ���� �������� ��������
* @param _approvedByBank boolean ����������, ������������ ������� �� ������� ������
* @param _dateCloseDeposit ���� �������� �������� �������� �� ����� �������� ��������
* @param  _termDays ���� � ���� �������� ��������
* @param  _percent ������� �������
* @param  _pretermPercent ��������� ������� ��� ��������� ������� ������
* @param  _withPercentCapitalization ����������� �������� �������������
* @param _deposit ��������� ����� ��������
* @param _incomeDeposit ����������� ����� ��������
* @param _client ������, �������� ����������� �������
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
	 ����� ���������� _indexDeposit
	*/	
	public int getIndexDeposit()
	{
		return _indexDeposit;
	}
	/*
	 ����� ���������� _indexDeposit
	*/	
	private void setIndexDeposit(Client client)
	{
		this._indexDeposit=Integer.parseInt (Integer.toString(client.getListDeposits().indexOf(this))+  //���������� �������� � ����� �������� ������� 
				Integer.toString(client.getIndexPerson()));												//����������� � _indexPerson - ���������� _indexDeposit 
	}
	/*
	 ����� ���������� _typeOfDeposit
	*/	
	public TypeOfDeposit getTypeOfDeposit()
	{
		return _typeOfDeposit;
	}
	/*
	 ����� ����������� ����� �������� _typeOfDeposit
	*/	
	public void setTypeOfDeposit(TypeOfDeposit typeOfDeposit)
	{
		this._typeOfDeposit=typeOfDeposit;
	}
	/*
	 ����� ���������� _dateCreateDeposit
	*/	
	public Date getDateCreateDeposit()
	{
		return _dateCreateDeposit;
	}
	/*
	 ����� ���������� _dateCreateDeposit
	*/	
	private void setDateCreateDeposit(Date dateCreateDeposit)
	{
		this._dateCreateDeposit=dateCreateDeposit;
	}
	/*
	 ����� ���������� _approvedByBank
	*/	
	public boolean getApprovedByBank()
	{
		return _approvedByBank;
	}
	/*
	 ����� ����������� ����� �������� _approvedByBank
	*/	
	public void setApprovedByBank(boolean approvedByBank)
	{
		this._approvedByBank=approvedByBank;
	}
	/*
	 ����� ���������� _termDays
	*/	
	public int getTermDays()
	{
		return _termDays;
	}
	/*
	 ����� ����������� ����� �������� _termDays
	*/	
	public void setTermDays(int termDays)
	{
		if (termDays<1)
	    	throw new IllegalArgumentException("term days deposit <1");
		else
			this._termDays=termDays;
	}
	/*
	 ����� ����������� ����� �������� _percent, ���� ������ ��� �� ������ 0
	*/
	public void setPercent(double percent)
	{
		if(percent<0)
	    	throw new IllegalArgumentException("percent  <0");
		else
			this._percent=percent;
	}
	/*
	 ����� ���������� _percent
	*/	
	public double getPercent()
	{
		return _percent;
	}
	/*
	 ����� ����������� ����� �������� _pretermPercent, ���� ������ ��� �� ������ 0
	*/
	public void setPretermPercent(double pretermPercent)
	{
		if(pretermPercent<0)
	    	throw new IllegalArgumentException("pretermPercent <0");
		else
			this._pretermPercent=pretermPercent;
	}
	/*
	 ����� ���������� _pretermPercent
	*/	
	public double getPretermPercent()
	{
		return _pretermPercent;
	}
	/*
	 ����� ���������� _withPercentCapitalization
	*/	
	public boolean getWithPercentCapitalization()
	{
		return _withPercentCapitalization;
	}
	/*
	 ����� ����������� ����� �������� _withPercentCapitalization
	*/	
	public void setWithPercentCapitalization(boolean withPercentCapitalization)
	{
		this._withPercentCapitalization=withPercentCapitalization;
	}
	/*
	 ����� ����������� ����� �������� _dateCloseDeposit, ���� ������ ���� �������� �� ������ ���� ��������
	*/	
	private void setDateCloseDeposit(Date DateCloseDeposit)
	{
		if(_dateCreateDeposit.getTime()<DateCloseDeposit.getTime() || DateCloseDeposit==null)
			this._dateCloseDeposit=DateCloseDeposit;
		else
	    	throw new IllegalArgumentException("Date of close deposit is older than the previous date of create");
	}
	/*
	 ����� ���������� _dateCloseDeposit
	*/	
	public Date getDateCloseDeposit()
	{
		return _dateCloseDeposit;
	}
	/*
	 ����� ����������� ����� �������� _deposit
	*/	
	public void setDeposit(double deposit)
	{
		this._deposit=deposit;
	}
	/*
	 ����� ���������� _deposit
	*/	
	public double getDeposit()
	{
		return _deposit;
	}
	/*
	 ����� ���������� _client
	*/	
	public Client getClient()
	{
		return _client;
	}
	/*
	 ����� ����������� ����� �������� _client
	*/	
	public void setClient(Client client)
	{
		this._client=client;
	}
	@Override
	public ArrayList<Deposit> getClientDeposits(Client client,String token) 
	{
		this.getClient().getAccount().authorize(this.getClient().getAccount().getUserName(), this.getClient().getAccount().getPassword(), new Date());
	    return client.getListDeposits(); 							//������������ ���� ��������� �������
	}
	@Override
	public ArrayList<Deposit> getAllDeposits(Client client) 
	{
    	ArrayList<Deposit> deposits=new ArrayList<Deposit>();
    	for (Iterator<Deposit> j = client.getListDeposits().iterator(); j.hasNext();) //���������� ������� ����� ��������� �������
	    	if (j.next().getApprovedByBank()) 
	    		deposits.add(j.next()); 								 //������������ ������ �� ��������, ������� �������� ������
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
		    	double percent=this.getPercent()/100; 						//������� ��������� � ���������� ��������
		    	double pretermPercent=this.getPretermPercent()/100; 		//������� ��������� � ���������� ��������
		    	_earnings=this.getDeposit();
		    	int startDay=this.getDateCreateDeposit().getDay();
		    	int nowDay=currentDate.getDay();
		    	if (nowDay-startDay==this.getTermDays())
		    		if (this.getWithPercentCapitalization())
		    		{
		    			for(int i = 1;i<=this.getTermDays()/30;i++)
		    			{
		    				_earnings=_earnings+_earnings*percent/12; 		//����� �����, ���� ������ ���� TermDays � ������������ WithPercentCapitalization
		    			}
		    		}
		    		else
		    			_earnings=_earnings+_earnings*percent;				//����� �����, ���� ������ ���� TermDays � ����������� WithPercentCapitalization
		    	else
		    		_earnings=_earnings+_earnings*percent*pretermPercent;	//����� �����, ���� �� ������ ���� TermDays. ������ �� pretermPercent
		    	return Math.round(_earnings*100)/100;						//���������� �� 2 ����� ����� �������
			}
			else
				throw new IllegalArgumentException("Deposit not approved by bank");			//����� ������, ���� ������� �� ��� ������ ������
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
    	double income=deposit.getEarnings(deposit,closeDate, token);			 //������ ������
    	deposit.getClient().deleteDepositFromList(deposit.getClient().getListDeposits().indexOf(deposit));			 //�������� �������� �� ������ � ��������
    	deposit=null;
    	return income;													 //����������� ������
	}
	//����� ��� �������� ������ �������� ���� ��� ������
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
	@Override	//����� ��� ���������� ������ �������� �� ����������
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