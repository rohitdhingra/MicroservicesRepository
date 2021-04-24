package com.example.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import rx.Observable;
import rx.Subscriber;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class StockService {

	private static String[] quotes = {"AAPL","GOOG"};
	public Observable<Stock> getStock() {
		return Observable.create(subscriber ->{
			if(!subscriber.isUnsubscribed())
			{
//				try {
					Arrays.stream(quotes).map(this::getStock)
					.filter(Objects::nonNull)
					.forEach(stock->{
						subscriber.onNext(stock);
						sleep(2000);
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

	private Stock getStock(String quote) {
		System.out.println("Retrieving stock info for:"+quote);
		try {
			return YahooFinance.get(quote);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
