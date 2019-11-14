import java.util.ArrayList;
import java.util.Date;

/*
  Интерфейс для управления учетными записями и авторизацией
 */
public interface AccountManager {
    /*
      Метод добавляет нового пользователя системы
     */
    void addAccount(String userName, String password);
    /*
      Метод удаляет пользователя системы
     */
    void removeAccount(String userName, String password);
    /*
      Метод возвращает список всех аккаунтов
     */
    ArrayList<Account> getAllAccounts();
    /*
      Метод авторизирует пользователя и возвращает Token для доступа методам системы
     */
    String authorize(String userName, String password, Date currentTime);

}
