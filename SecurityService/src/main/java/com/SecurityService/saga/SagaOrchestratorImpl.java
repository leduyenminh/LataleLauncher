package com.SecurityService.saga;

import java.math.BigDecimal;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SagaOrchestratorImpl implements SagaOrchestrator{
    private final ObjectMapper mapper = new ObjectMapper();

  // private final CustomerRepositoryPort customerRepository;

  @Override
  public void compensateEditing(){
    
  }

//   @Override
//   public boolean reserveBalance(PlacedOrderEvent orderEvent) {
//     var customer = findById(orderEvent.customerId());
//     if (customer
//         .getBalance()
//         .subtract(orderEvent.price().multiply(BigDecimal.valueOf(orderEvent.quantity())))
//         .compareTo(BigDecimal.ZERO)
//         < 0) {
//       return false;
//     }
//     customer.setBalance(
//         customer
//             .getBalance()
//             .subtract(orderEvent.price().multiply(BigDecimal.valueOf(orderEvent.quantity()))));
//     customerRepository.saveCustomer(customer);
//     return true;
//   }

//   @Override
//   public void compensateBalance(PlacedOrderEvent orderEvent) {
//     var customer = findById(orderEvent.customerId());
//     customer.setBalance(
//         customer
//             .getBalance()
//             .add(orderEvent.price().multiply(BigDecimal.valueOf(orderEvent.quantity()))));
//     customerRepository.saveCustomer(customer);
//   }
}
