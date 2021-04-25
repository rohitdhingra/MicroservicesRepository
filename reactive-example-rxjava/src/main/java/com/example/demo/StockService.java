package com.example.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import rx.Observable;
import rx.Subscriber;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class StockService {

	private static String[] quotes = {"AAPL","GOOG","INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
	public Observable<Stock> getStock() {
		return Observable.create(subscriber ->{
			if(!subscriber.isUnsubscribed())
			{
//				try {
					Arrays.stream(quotes).map(stock -> getStock(stock,subscriber))
					.filter(Objects::nonNull)
					.forEach(stock->{
						subscriber.onNext(stock);
						sleep(2000);
						subscriber.onError(new RuntimeException("Custom Runtime exception"));	
						
					});
//					Stock stock = YahooFinance.get("GOOG");
//					subscriber.onNext(stock);
//				} catch (IOException e) {
//					subscriber.onError(e);
//				}
			}
			subscriber.onCompleted();
		});
	}

	private void sleep(int i) {

		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Stock getStock(String quote, Subscriber<? super Stock> subscriber) {
		System.out.println("Retrieving stock info for:"+quote);
		try {
			if(quote.equals("GOOG"))
			{
				throw new IOException("Custom Exception");
			}
			return YahooFinance.get(quote);
		} catch (IOException e) {
			subscriber.onError(e);
		}
		return null;
	}
}
