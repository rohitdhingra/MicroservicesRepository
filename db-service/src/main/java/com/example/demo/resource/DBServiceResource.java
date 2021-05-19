package com.example.demo.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.QuoteDTO;
import com.example.demo.model.Quote;
import com.example.demo.repository.QuoteRepository;

@RestController
@RequestMapping("/rest/db")
public class DBServiceResource {

	@Autowired
	private QuoteRepository quotesRepository;

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String userName)
	{
		return getQuoteByUserName(userName);
//		 null;
	}
	private List<String> getQuoteByUserName(final String userName) {
		return quotesRepository.findByUserName(userName).stream().map(quote ->quote.getQuote()).collect(Collectors.toList());
	}
	@PostMapping("/add")
	public List<String> add(@RequestBody final QuoteDTO quoteDTO)
	{
		quoteDTO.getQuotes().stream().forEach(q -> {
			quotesRepository.save(new Quote(quoteDTO.getUserName(),q));
		});
		
		return getQuoteByUserName(quoteDTO.getUserName());
	}
	@DeleteMapping("/delete/{userName}")
	public List<String> delete(@PathVariable("userName") final String userName)
	{
		List<Quote> quotes = quotesRepository.findByUserName(userName);
		quotesRepository.deleteInBatch(quotes);
		return getQuoteByUserName(userName);
		
	}
	
}
