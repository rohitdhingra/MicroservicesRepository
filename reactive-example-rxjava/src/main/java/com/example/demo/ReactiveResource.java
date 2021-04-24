package com.example.demo;

import rx.Observable;
import rx.functions.Action1;
import yahoofinance.Stock;

public class ReactiveResource {
	public static void main(String[] args) {
		Observable<Stock> stockQuote = new ReactiveResource().getStockQuote();
		System.out.println("Going to subscribe");
		stockQuote.subscribe(ReactiveResource::callBack, ReactiveResource::onError, ReactiveResource::onCompleted);
		System.out.println("Processing Completed");
	}

	private static void onError(Throwable throwable) {
		System.out.println(throwable);
	}

	private static Action1<? super Stock> callBack(Stock stock) {

		System.out.println(String.format("Quote:%s, Price: %s, Day's High: %s, Day's Low: %s", stock.getSymbol(),
				stock.getQuote().getPrice(), stock.getQuote().getDayHigh(), stock.getQuote().getDayLow()));
		return null;
	}

	private static void onCompleted() {
		System.out.println("Completed Successfully!");
	}

	private Observable<Stock> getStockQuote() {
		return new StockService().getStock();
	}
}
