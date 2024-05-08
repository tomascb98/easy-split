package project1.easysplit.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project1.easysplit.Domain.Bill;
import project1.easysplit.Exceptions.BadRequestException;
import project1.easysplit.Repository.IBillRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    private final Logger logger = LogManager.getLogger(Bill.class);
    private IBillRepository billRepository;
    @Autowired
    public BillService(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill saveBill(Bill bill) throws BadRequestException {
        bill.getPeople().forEach(person -> person.setBill(bill));
        if(!billRepository.exists(Example.of(bill))){
            return billRepository.save(bill);
        }
        else{
            logger.warn("The Bill: " + bill.getName() + "already exists");
            throw new BadRequestException("The Bill: " + bill.getName() + " already exists");
        }
    }

    public Bill getBill (Long id) throws BadRequestException {
        Optional<Bill> billOptional = billRepository.findById(id);
        if(billOptional.isPresent()){
            logger.info("Successful find of the Bill: " + billOptional.get().getName() + "ID:[" + id + "]");
            return billOptional.get();
        } else {
            logger.warn("ERROR, there is no Bill with ID[" + id + "]");
            throw new BadRequestException("ERROR, there is no Bill with ID[" + id + "]");
        }
    }

    public Bill updateBill (Bill bill) throws BadRequestException {
        Optional<Bill> billOptional = billRepository.findById(bill.getId());
        if(billOptional.isPresent()){
            logger.info("Successful update of the Bill: " + bill.getName() + "ID:[" + bill.getId() + "]");
            return billOptional.get();
        } else {
            logger.warn("ERROR, there is no Bill with ID[" + bill.getId() + "]");
            throw new BadRequestException("ERROR, there is no Bill with ID[" + bill.getId() + "]");
        }
    }

    public void deleteBill (Bill bill) throws BadRequestException {
        if(billRepository.exists(Example.of(bill))){
            logger.info("Successful delete of the Bill: " + bill.getName() + "ID:[" + bill.getId() + "]");
            billRepository.deleteById(bill.getId());
        } else {
            logger.warn("ERROR, there is no Bill with ID[" + bill.getId() + "]");
            throw new BadRequestException("ERROR, there is no Bill with ID[" + bill.getId() + "]");
        }
    }

    public List<Bill> getAllBills () throws BadRequestException {
        List<Bill> bills = billRepository.findAll(Sort.by("id"));
        if(!bills.isEmpty()){
            logger.info("Successful find of the Bills");
            return bills;
        } else {
            logger.warn("ERROR, there are no bills created yet");
            throw new BadRequestException("ERROR, there are no bills created yet");
        }
    }

}
