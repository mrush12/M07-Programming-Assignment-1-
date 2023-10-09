import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.*;
import javax.servlet.http.*;

import LoanPackage.Loan; // Assuming the Loan class is in a package named LoanPackage

public class LoanServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get data
        double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
        double annualInterestRate = Double.parseDouble(request.getParameter("interestRate"));
        int numberOfYears = Integer.parseInt(request.getParameter("numberOfYears"));

        // Calculate total loan payments using the Loan class
        Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);
        double monthlyPayment = loan.getMonthlyPayment();
        double totalPayment = loan.getTotalPayment();

        // Format the results
        DecimalFormat df = new DecimalFormat("#.##");
        String monthlyPaymentFormatted = df.format(monthlyPayment);
        String totalPaymentFormatted = df.format(totalPayment);

        // Display the results
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Loan Calculation Result</title></head><body>");
        out.println("<h1>Loan Calculation Result</h1>");
        out.println("<p>Monthly Payment: $" + monthlyPaymentFormatted + "</p>");
        out.println("<p>Total Payment: $" + totalPaymentFormatted + "</p>");
        out.println("</body></html>");
    }
}
