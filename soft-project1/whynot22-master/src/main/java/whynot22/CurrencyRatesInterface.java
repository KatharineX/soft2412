package whynot22;

import java.util.ArrayList;
import java.io.File;

interface CurrencyRatesInterface {

		public boolean initialDisplay();

		public ArrayList<String> takeInput();

		public ArrayList<Pair> extractFunction(ArrayList<String> input);

		public ArrayList<Double> convertToAUD(ArrayList<Pair> input);

		public double finalSumAUD(ArrayList<Double> arrayOfSums);

		public boolean secondDisplay();

		public double AUDtoNewCurrency(int x, ExchangeRate rates, double sum);

}
