import java.util.Date;
import java.util.ArrayList;

/*
  ��������� ��� ���������� ��������
 */
public interface DepositManager {
    /*
       ����� ��������� � ������� ���������� � ����� ������
     * @param pretermPercent ������� ��� ��������� ������� ������
     */
    Deposit addDeposit(Client client, double ammount, double percent, double pretermPercent, int termDays, Date startDate, boolean withPercentCapitalization, String token);

    /*
      ����� ���������� ������ ������� �������
    */
    ArrayList<Deposit> getClientDeposits(Client client, String token);

    /*
      ����� ���������� ������ ���� ������� �������� ������
     */
    ArrayList<Deposit> getAllDeposits(Client client); //������ �������� ������� ����������� �������

    /*
      ����� ���������� ������� ����� �� ������
     */
    double getEarnings(Deposit deposit, Date currentDate, String token);

    /*
      ����� ������� ������ � ������ � ���������� ����� � ������� � �����. ���� ����� ����������� ��������, �� ����� � ������� �������������� ������ �� �������� ��� ��������� �������.
     */
    double removeDeposit(Deposit deposit, Date closeDate, String token);

}

