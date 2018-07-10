package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.TransactionsRepository;
import vp.spring.rcs.model.Transactions;

@Component
public class TransactionsService {
	
	@Autowired
	TransactionsRepository transactionsRepository;

	public Transactions findOne(Long id) {
		return transactionsRepository.findOne(id);
	}

	public List<Transactions> findAll() {
		return transactionsRepository.findAll();
	}

	public Transactions save(Transactions transactions) {
		return transactionsRepository.save(transactions);
	}

	public void remove(Long id) {
		transactionsRepository.delete(id);
	}
}
