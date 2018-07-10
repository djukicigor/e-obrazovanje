package vp.spring.rcs.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vp.spring.rcs.model.Transactions;
import vp.spring.rcs.service.TransactionsService;
import vp.spring.rcs.web.dto.CommonResponseDTO;

@RestController
@RequestMapping(value = "/api/transactions")
public class TransactionsController {

	@Autowired
	TransactionsService transactionsService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Transactions>> getAll() {
		List<Transactions> transactions = transactionsService.findAll();
		return new ResponseEntity<>(transactions, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Transactions> getById(@PathVariable Long id) {
		Transactions transactions = transactionsService.findOne(id);
		
		if (transactions != null) {
			return new ResponseEntity<>(transactions, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transactions> create(@RequestBody Transactions transactions) {
		Transactions retVal = transactionsService.save(transactions);

		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transactions> update(@PathVariable Long id,
			@RequestBody Transactions transactions) {
		if (transactionsService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		transactions.setId(id);
		Transactions retVal = transactionsService.save(transactions);

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CommonResponseDTO> delete(@PathVariable Long id) {
		Transactions transactions = transactionsService.findOne(id);
		if (transactions != null) {
			transactionsService.remove(id);
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.NOT_FOUND);
		}
	}
}
