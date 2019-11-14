import java.util.ArrayList;
import java.util.Date;

/*
  ��������� ��� ���������� �������� �������� � ������������
 */
public interface AccountManager {
    /*
      ����� ��������� ������ ������������ �������
     */
    void addAccount(String userName, String password);
    /*
      ����� ������� ������������ �������
     */
    void removeAccount(String userName, String password);
    /*
      ����� ���������� ������ ���� ���������
     */
    ArrayList<Account> getAllAccounts();
    /*
      ����� ������������ ������������ � ���������� Token ��� ������� ������� �������
     */
    String authorize(String userName, String password, Date currentTime);

}
