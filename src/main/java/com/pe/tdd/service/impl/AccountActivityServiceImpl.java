package com.pe.tdd.service.impl;

import com.pe.tdd.domain.Account;
import com.pe.tdd.domain.AccountActivity;
import com.pe.tdd.domain.CreditCardPaymentResponse;
import com.pe.tdd.domain.MobileCarrierRechargeBuyResponse;
import com.pe.tdd.domain.ServiceProviderPaymentResponse;
import com.pe.tdd.domain.SpeiTransfer;
import com.pe.tdd.domain.TefTransfer;
import com.pe.tdd.exception.InvalidAccountException;
import com.pe.tdd.repository.AccountActivityRepository;
import com.pe.tdd.service.AccountActivityService;
import com.pe.tdd.service.AccountService;

import java.util.List;

public class AccountActivityServiceImpl implements AccountActivityService {

    private final AccountActivityRepository accountActivityRepository;

    private final AccountService accountService;

    public AccountActivityServiceImpl(AccountActivityRepository accountActivityRepository, AccountService accountService) {
        this.accountActivityRepository = accountActivityRepository;
        this.accountService = accountService;
    }

    @Override
    public List<AccountActivity> findActivitiesByAccount(Long accountNumber, String userName) {
        Account foundAccount = accountService.findAccount(accountNumber, userName);
        if (foundAccount == null) {
            throw new InvalidAccountException();
        }
        return accountActivityRepository.findActivitiesByAccount(accountNumber);
    }

    @Override
    public AccountActivity addAccountActivity(ServiceProviderPaymentResponse serviceProviderPaymentResponse, Account account) {
        AccountActivity accountActivity = new AccountActivity();
        accountActivity.setAmount(serviceProviderPaymentResponse.getAmount());
        accountActivity.setDescription(serviceProviderPaymentResponse.getDescription());
        accountActivity.setAuthorizationCode(serviceProviderPaymentResponse.getAuthorizationCode());
        accountActivity.setId(1L);

        accountService.updateBalance(
                accountActivity.getAmount().negate(),
                account
        );

        return accountActivityRepository.save(accountActivity);
    }

    @Override
    public AccountActivity addAccountActivity(SpeiTransfer speiTransfer, Account account) {
        AccountActivity accountActivity = new AccountActivity();
        accountActivity.setAmount(speiTransfer.getAmount());
        accountActivity.setDescription("Transferencia SPEI");
        accountActivity.setAuthorizationCode(speiTransfer.getAuthorizationCode());
        accountActivity.setId(1L);

        accountService.updateBalance(
                accountActivity.getAmount().negate(),
                account
        );

        return accountActivityRepository.save(accountActivity);
    }

    @Override
    public AccountActivity addAccountActivity(CreditCardPaymentResponse creditCardPaymentResponse, Account account) {
        AccountActivity accountActivity = new AccountActivity();
        accountActivity.setAmount(creditCardPaymentResponse.getAmount());
        accountActivity.setDescription("Pago de tarjeta de credito");
        accountActivity.setAuthorizationCode(creditCardPaymentResponse.getAuthorizationCode());
        accountActivity.setId(1L);

        accountService.updateBalance(
                accountActivity.getAmount().negate(),
                account
        );

        return accountActivityRepository.save(accountActivity);
    }

    @Override
    public AccountActivity addAccountActivity(MobileCarrierRechargeBuyResponse mobileCarrierRechargeBuyResponse, Account account) {
        AccountActivity accountActivity = new AccountActivity();
        accountActivity.setAmount(mobileCarrierRechargeBuyResponse.getAmount());
        accountActivity.setDescription(mobileCarrierRechargeBuyResponse.getDescription());
        accountActivity.setAuthorizationCode(mobileCarrierRechargeBuyResponse.getAuthorizationCode());
        accountActivity.setId(1L);

        accountService.updateBalance(
                accountActivity.getAmount().negate(),
                account
        );

        return accountActivityRepository.save(accountActivity);
    }

    @Override
    public AccountActivity addAccountActivity(TefTransfer tefTransfer, Account account) {
        AccountActivity accountActivity = new AccountActivity();
        accountActivity.setAmount(tefTransfer.getAmount());
        accountActivity.setDescription("Transferencia SPEI");
        accountActivity.setAuthorizationCode(tefTransfer.getAuthorizationCode());
        accountActivity.setId(1L);

        accountService.updateBalance(
                accountActivity.getAmount().negate(),
                account
        );

        return accountActivityRepository.save(accountActivity);
    }

}
