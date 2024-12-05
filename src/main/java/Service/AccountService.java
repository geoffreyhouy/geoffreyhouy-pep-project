package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account register(Account account) {
        String username = account.getUsername();
        if (username.length() == 0) {
            return null;
        }
        String password = account.getPassword();
        if (password.length() < 4) {
            return null;
        }
        if (accountDAO.getAccountByUsername(username) != null) {
            return null;
        }
        return accountDAO.register(account);
    }

    public Account login(Account account) {
        String username = account.getUsername();
        String password = account.getPassword();
        return accountDAO.login(username, password);
    }
}
