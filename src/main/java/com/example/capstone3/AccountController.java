package com.example.capstone3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired AccountRepository accountRepository;
    @RequestMapping("/create")
    public String showCreateAccount(Model model){
        List<Customer> customers = customerRepository.findAll();
        System.out.println(customers);
        model.addAttribute("customers", customers);
        return "createaccount";
    }

    @PostMapping("/create")
    public String createAccount(@RequestParam("customerId") Long customerId,
                                @RequestParam("accountType") String accountType,
                                @RequestParam("accountName") String accountName) {
        Account account = new Account();
        account.setCustomer(customerRepository.findById(customerId).orElse(null));
        if (accountType.equals("Savings")) {
            account.setAccountType(Account.AccountType.Savings);
        }
        else {
            account.setAccountType(Account.AccountType.Current);
        }
        account.setName(accountName);
        account.setStatus(Account.Status.Active);
        accountRepository.save(account);
        return "redirect:/";
    }

    @RequestMapping("/view/{id}")
    public String viewAccount(@PathVariable("id") Long id, Model model) {
        Account account = accountRepository.findById(id).get();
        List<Transaction> transactions = transactionRepository.findByAccount(account);
        double balance = transactionRepository.getBalance(id);
        model.addAttribute("transactions", transactions);
        model.addAttribute("accountId", id);
        model.addAttribute("balance", balance);
        return "viewaccount";
    }

    @RequestMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") Long id) {
        accountRepository.deleteById(id);
        return "redirect:/";
    }
}
