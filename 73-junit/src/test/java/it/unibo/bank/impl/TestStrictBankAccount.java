package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        mRossi = new AccountHolder("Mario", "Rossi", 0);
        bankAccount = new StrictBankAccount(mRossi, 0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
        assertEquals(0.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals(mRossi, bankAccount.getAccountHolder());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        final double expectedBalance = bankAccount.getBalance() - StrictBankAccount.MANAGEMENT_FEE + 100 - StrictBankAccount.TRANSACTION_FEE;
        bankAccount.deposit(mRossi.getUserID(), 100.0);
        bankAccount.chargeManagementFees(mRossi.getUserID());
        assertEquals(bankAccount.getBalance(), expectedBalance);

    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
        final double expectedBalance = bankAccount.getBalance();
        try{
            bankAccount.withdraw(mRossi.getUserID(), -100.0);
        }catch(IllegalArgumentException e){
            assertEquals(expectedBalance, bankAccount.getBalance());
            assertNotNull(e);
            assertFalse(e.getMessage().isBlank()); 
        }
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        final double expectedBalance = bankAccount.getBalance();
        try{
            bankAccount.withdraw(mRossi.getUserID(), bankAccount.getBalance() + 100);
        }catch(IllegalArgumentException e){
            assertEquals(expectedBalance, bankAccount.getBalance());
            assertFalse(e.getMessage().isBlank()); 
        }
    }
}
