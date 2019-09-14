import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class models a bank account. It holds the name of the owner, the current balance
 * and a list of transactions that were performed on this bank account.
 * 
 * @author 123456ab Paul Bouman
 *
 */

public class BankAccount
{
	private String owner;
	private int balance;
	private List<Transaction> transactions;
	
	/**
	 * This constructor stores the name of the owner and the initial balance of the bank account.
	 * By default, the list of transactions associated with the bank account is empty.
	 * 
	 * @param ownerName the name of the owner of the new bank account
	 * @param initialBalance the initial balance of the new bank account
	 */
	public BankAccount(String ownerName, int initialBalance)
	{
		owner = ownerName;
		balance = initialBalance;
		transactions = new ArrayList<>();
	}
	
	/**
	 * Performs the withdraw money transaction on this bank account. The transaction is only
	 * performed if there is sufficient balance on the account.
	 * 
	 * @param amount the amount of money to withdraw from the bank account
	 * @throws IllegalArgumentException if the amount exceeds the current balance of the account
	 */
	public void withdrawMoney(int amount) throws IllegalArgumentException
	{
		if (amount > balance)
		{
			throw new IllegalArgumentException("Cannot withdraw "+amount+" with a balance of "+balance);
		}
		balance -= amount;
		Transaction transaction = new Transaction(amount, "Withdraw");
		transactions.add(transaction);
	}

	/**
	 * Provides the owner associated with this bank account, that was provided during the creation
	 * of this bank account object.
	 * 
	 * @return the name of the owner of this bank account
	 */
	public String getOwner()
	{
		return owner;
	}

	/**
	 * Provides the current balance of this bank account. The current balance is the initial balance
	 * provided when the object was created adjusted by succesful transactions involving this bank
	 * account.
	 * 
	 * @return the current balance of the bank account
	 */
	public int getBalance()
	{
		return balance;
	}

	/**
	 * Provides the list of transactions that involve this bank account.
	 * 
	 * Note that this list cannot be modified.
	 * 
	 * @return an unmodifiable list of transactions involving this bank account.
	 */
	public List<Transaction> getTransactions()
	{
		return Collections.unmodifiableList(transactions);
	}
	
	/**
	 * Computes the balance after applying a given interest rate for a number of periods.
	 * <p>
	 * It is assumed that interest is paid at the end of each period, and that the interest is compounded.
	 * </p>
	 * <p>
	 * The formula used for this is: balance &times; (1 + rate)<sup>periods</sup>
	 * </p>
	 * <p>
	 * <strong>Example:</strong> a bank account with a balance of 100 will become 110.25 after applying an interest
	 * of 0.05 for two periods.
	 * </p> 
	 * 
	 * @param periods the number of periods in which interest should be applied
	 * @param rate the interest rate applied in every period
	 * @return the balanced after the interest is applied for the number of periods to the current balance 
	 * @throws IllegalArgumentException if periods is smaller than zero
	 */
	public double computeBalanceAfterInterest(int periods, double rate) throws IllegalArgumentException {
		if (periods < 0) {
			throw new IllegalArgumentException("The number of periods can not be negative");
		}
		return balance * Math.pow(1+rate, periods);
	}
}
