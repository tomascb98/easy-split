package project1.easysplit.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project1.easysplit.Domain.Bill;
import project1.easysplit.Exceptions.BadRequestException;
import project1.easysplit.Service.BillService;

@RestController
@RequestMapping("/bill")
public class BillController {

    private BillService billService;
    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<Bill> saveBill (@RequestBody Bill bill) throws BadRequestException {
        System.out.println(bill.getPeople());
        Bill billSaved = billService.saveBill(bill);
        return ResponseEntity.status(HttpStatus.OK)
                .body(billSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBill(@PathVariable Long id) throws BadRequestException {
        Bill bill = billService.getBill(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(bill);
    }



}
