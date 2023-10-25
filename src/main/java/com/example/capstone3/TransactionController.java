package com.example.capstone3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.capstone3.Transaction.TransactionType;

import java.time.LocalDate;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired AccountRepository accountRepository;
    @RequestMapping("/create")
    public String showCreateTransaction(@RequestParam("accountId") Long accountId,@RequestParam("balance") Double balance, Model model){
        model.addAttribute("accountId", accountId);
        model.addAttribute("balance", balance);
        return "createtransaction";
    }

    @PostMapping("/create")
    public String createTransaction(@RequestParam("accountId") Long accountId,
                                @RequestParam("transactionType") TransactionType transactionType,
                                @RequestParam("amount") double amount,
                                @RequestParam("balance") Double balance,
                                RedirectAttributes redirectAttributes) {

        if (transactionType.equals(TransactionType.Debit) && amount > balance){
            redirectAttributes.addFlashAttribute("error", "Insufficient balance. Please enter a smaller amount.");
          
            return "redirect:/transaction/create?accountId=" + accountId + "&balance=" + balance;
        }
        Transaction transaction = new Transaction();
        transaction.setAccount(accountRepository.findById(accountId).orElse(null));
        transaction.setType(transactionType);
        transaction.setAmount(amount);
        transaction.setDatecreated(LocalDate.now());
        transactionRepository.save(transaction);
        return "redirect:/account/view/" + accountId;
    }

}
