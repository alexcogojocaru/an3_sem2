import interfaces.BankAccountBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProcessBankOperationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String operation = req.getParameter("operation");
        String amountString = req.getParameter("amount");

        Integer amount = (!amountString.equals("")) ? Integer.parseInt(amountString) : 0;

        BankAccountBeanRemote bankAccount;

        bankAccount = (BankAccountBeanRemote) req.getSession().getAttribute("bankAccountBean");
        if (bankAccount == null) {
            try {
                InitialContext ctx = new InitialContext();
                bankAccount = (BankAccountBeanRemote) ctx.lookup("bankaccount#interfaces.BankAccountBeanRemote");
                req.getSession().setAttribute("bankAccountBean", bankAccount);
            } catch (NamingException e) {
                e.printStackTrace();
                resp.getWriter().print("Error");
            }
        }

        Integer accountBalance = null;
        String message = "";

        if (operation.equals("deposit")) {
            bankAccount.deposit(amount);
            message = "In contul dvs. a fost depusa suma " + amount + ".";
        }
        else if (operation.equals("withdraw")) {
            if (bankAccount.withdraw(amount)) {
                message = "Din contul dvs. s-a retras suma de: " + amount + ".";
            }
            else {
                message = "Operatiunea a esuat! Fonduri insuficiente.";
            }
        }
        else if (operation.equals("balance")) {
            accountBalance = bankAccount.getBalance();
        }

        message += "<br /><br />";
        if (accountBalance != null) {
            message += "Sold cont: " + accountBalance;
        }
        message += "<br /><a href='./'>Inapoi la meniul principal</a>";
        resp.setContentType("text/html");
        resp.getWriter().print(message);
    }
}
