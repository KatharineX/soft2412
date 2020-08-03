package VM;
import java.util.ArrayList;

public class ExchangeRates {

    public CurrencyPair AUD = new CurrencyPair("AUD", 1);
    public CurrencyPair USD = new CurrencyPair("USD", 0.69);
    public CurrencyPair SGD = new CurrencyPair("SGD", 0.95);
    public CurrencyPair EUR = new CurrencyPair("EUR", 0.62);
    public CurrencyPair JPY = new CurrencyPair("JPY", 73.74);

    public ArrayList<CurrencyPair> availableCurrencies = new ArrayList<CurrencyPair>();


    public ExchangeRates(){

        availableCurrencies.add(AUD);
        availableCurrencies.add(USD);
        availableCurrencies.add(SGD);
        availableCurrencies.add(EUR);
        availableCurrencies.add(JPY);

    }

    public ArrayList<CurrencyPair> getCurrencies(){
        return this.availableCurrencies;
    }
}
