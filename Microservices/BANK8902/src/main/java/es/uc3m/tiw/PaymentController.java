package es.uc3m.tiw;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import es.uc3m.tiw.domains.Payment;

@Controller
public class PaymentController {
	
	@RequestMapping(value="/payment",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> processPayment( @RequestBody @Validated Payment payment) {
		ResponseEntity<String> response;
		if (payment.getCard().validateCard()) {
			response = new ResponseEntity<>(payment.getTransactionCode().toString(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>("Credit Card Invalid", HttpStatus.PAYMENT_REQUIRED);
		}
		
		return response;
	}
	
	@RequestMapping("/402.html")
    public String render404(Model model) {
        System.out.println("402-bank");
        return "index";
    }

}