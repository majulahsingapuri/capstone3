package com.example.capstone3;

import com.example.capstone3.Transaction.TransactionType;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

  @Autowired private TransactionRepository transactionRepository;

  @Autowired AccountRepository accountRepository;

  @RequestMapping("/create")
  public String showCreateTransaction(@RequestParam("accountId") Long accountId, Model model) {
    model.addAttribute("accountId", accountId);
    return "createtransaction";
  }

  @PostMapping("/create")
  public String createTransaction(
      @RequestParam("accountId") Long accountId,
      @RequestParam("transactionType") TransactionType transactionType,
      @RequestParam("amount") double amount) {
    Transaction transaction = new Transaction();
    transaction.setAccount(accountRepository.findById(accountId).orElse(null));
    transaction.setType(transactionType);
    transaction.setAmount(amount);
    transaction.setDatecreated(LocalDate.now());
    transactionRepository.save(transaction);
    return "redirect:/account/view/" + accountId;
  }
}
