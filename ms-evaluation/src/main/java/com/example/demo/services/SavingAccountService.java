package com.example.demo.services;

import com.example.demo.entities.CustomerWorksheetEntity;
import com.example.demo.entities.SavingAccountEntity;
import com.example.demo.repositories.CustomerWorksheetRepository;
import com.example.demo.repositories.SavingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SavingAccountService {
    @Autowired
    SavingAccountRepository savingAccountRepository;

    @Autowired
    CustomerWorksheetRepository worksheetRepository;

    public List<SavingAccountEntity> getAll(){return savingAccountRepository.findAll();}
    public SavingAccountEntity findByRut(String rut){return savingAccountRepository.findByRut(rut);}

    public void R71(String rut){
        CustomerWorksheetEntity worksheetEntity = worksheetRepository.findByRut(rut);

        SavingAccountEntity account = savingAccountRepository.findByRut(rut);
        int negativePoints = account.getNegativePoints();

        if (account.getBalance() > (worksheetEntity.getSalary() * 0.1)){
            account.setNegativePoints(negativePoints + 1);
            savingAccountRepository.save(account);
        }
    }

    public void R72consistentHistory(String rut){
        SavingAccountEntity account = savingAccountRepository.findByRut(rut);
        float balance = account.getBalance();
        int negativePoints = account.getNegativePoints();

        if (account.getRetiredCash() > (balance * 0.5)) {
            account.setNegativePoints(negativePoints + 1);
            savingAccountRepository.save(account);
        }
    }

    public void R73periodicDeposit(String rut){
        SavingAccountEntity account = savingAccountRepository.findByRut(rut);
        CustomerWorksheetEntity worksheetEntity = worksheetRepository.findByRut(rut);

        int negativePoints = account.getNegativePoints();

        float periodicDeposit = account.getPeriodicDeposit();
        float salary = worksheetEntity.getSalary();

        if (periodicDeposit >= (salary * 0.05)){
            negativePoints+=1;
            account.setNegativePoints(negativePoints);
            savingAccountRepository.save(account);
        }
    }

    public void R74balanceSeniority(String rut){
        SavingAccountEntity account = savingAccountRepository.findByRut(rut);
        int negativePoints = account.getNegativePoints();
        float monto = account.getBalance();

        if (account.getSeniority() < 2){
            if (account.getBalance() >= (monto * 0.2)){
                account.setNegativePoints(negativePoints + 1);
                savingAccountRepository.save(account);
            }
        }else{
            if (account.getBalance() >= (monto * 0.1)){
                account.setNegativePoints(negativePoints + 1);
                savingAccountRepository.save(account);
            }
        }
    }

    public void R75recentlyRecent(String rut){
        SavingAccountEntity account = savingAccountRepository.findByRut(rut);
        int negativePoints = account.getNegativePoints();
        float balance = account.getBalance();

        if (account.getRecentlyRetiredCash() < (balance * 0.7)){
            account.setNegativePoints(negativePoints + 1);
            savingAccountRepository.save(account);
        }
    }

    public void rateEvaluation(String rut){
        R71(rut);
        R72consistentHistory(rut);
        R73periodicDeposit(rut);
        R74balanceSeniority(rut);
        R75recentlyRecent(rut);

        SavingAccountEntity account = savingAccountRepository.findByRut(rut);
        int negativePoints = account.getNegativePoints();

        if (negativePoints >= 5) {
            account.setTypeCapacity(1); // solida
        }
        else{
            if (negativePoints >= 3){
                account.setTypeCapacity(2); // moderada
            }
            account.setTypeCapacity(3); // insuficiente
        }
        savingAccountRepository.save(account);
    }

    public Boolean save(SavingAccountEntity savingAccountEntity){
        savingAccountRepository.save(savingAccountEntity);
        return true;
    }

    public Boolean deleteById(Long id){
        savingAccountRepository.deleteById(id);
        return true;
    }

}
