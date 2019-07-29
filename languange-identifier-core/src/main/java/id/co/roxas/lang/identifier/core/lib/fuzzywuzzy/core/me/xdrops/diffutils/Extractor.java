package id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.algorithms.Utils;
import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.models.BoundExtractedResult;
import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.models.ExtractedResult;

public class Extractor<T> {

	private int cutoff;

	public Extractor() {
		this.cutoff = 0;
	}

	public Extractor(int cutoff) {
		this.cutoff = cutoff;
	}

	public Extractor with(int cutoff) {
		this.setCutoff(cutoff);
		return this;
	}

	/**
	 * Returns the list of choices with their associated scores of similarity in a
	 * list of {@link ExtractedResult}
	 *
	 * @param query   The query string
	 * @param choices The list of choices
	 * @param func    The function to apply
	 * @return The list of results
	 */
	public List<ExtractedResult> extractWithoutOrder(String query, Collection<String> choices, Applicable func) {
		List<ExtractedResult> yields = new ArrayList<>();
		int index = 0;

		for (String s : choices) {

			int score = func.apply(query, s);

			if (score >= cutoff) {
				yields.add(new ExtractedResult(s, score, index));
			}
			index++;
		}

		return yields;
	}

	public List<ExtractedResult> extractWithoutOrderMap(String query,Map<String,String> choices, Applicable func) {
		List<ExtractedResult> yields = new ArrayList<>();
		int index = 0;
		for (Entry<String, String> extractedResult: choices.entrySet()) {
			String word = extractedResult.getKey();
			String meaning = extractedResult.getValue();
			int score = func.apply(query,meaning);

			if (score >= cutoff) {
				yields.add(new ExtractedResult(word,meaning, score, index));
			}
			index++;
		}

		return yields;
	}
	
	@SuppressWarnings("unchecked")
	public List<ExtractedResult<T>> extractWithoutOrderMap2(String query,Map<Class<T>, String> choices, Applicable func) {
		List<ExtractedResult<T>> yields = new ArrayList<>();
		int index = 0;
		for (Entry<Class<T>, String> extractedResult: choices.entrySet()) {
			String meaning = extractedResult.getValue();
			int score = func.apply(query,meaning);

			if (score >= cutoff) {
				yields.add(new ExtractedResult(extractedResult.getKey(),meaning, score, index));
			}
			index++;
		}

		return yields;
	}

	/**
	 * Returns the list of choices with their associated scores of similarity in a
	 * list of {@link ExtractedResult}
	 *
	 * @param query            The query string
	 * @param choices          The list of choices
	 * @param toStringFunction The ToStringFunction to be applied to all choices.
	 * @param func             The function to apply
	 * @return The list of results
	 */
	public <T> List<BoundExtractedResult<T>> extractWithoutOrder(String query, Collection<T> choices,
			ToStringFunction<T> toStringFunction, Applicable func) {

		List<BoundExtractedResult<T>> yields = new ArrayList<>();
		int index = 0;

		for (T t : choices) {

			String s = toStringFunction.apply(t);
			int score = func.apply(query, s);

			if (score >= cutoff) {
				yields.add(new BoundExtractedResult<>(t, s, score, index));
			}
			index++;
		}

		return yields;

	}

	/**
	 * Find the single best match above a score in a list of choices.
	 *
	 * @param query   A string to match against
	 * @param choices A list of choices
	 * @param func    Scoring function
	 * @return An object containing the best match and it's score
	 */
	public ExtractedResult extractOne(String query, Collection<String> choices, Applicable func) {
		List<ExtractedResult> extracted = extractWithoutOrder(query, choices, func);

		return Collections.max(extracted);
	}

	/**
	 * Find the single best match above a score in a list of choices.
	 *
	 * @param query            A string to match against
	 * @param choices          A list of choices
	 * @param toStringFunction The ToStringFunction to be applied to all choices.
	 * @param func             Scoring function
	 * @return An object containing the best match and it's score
	 */
	public <T> BoundExtractedResult<T> extractOne(String query, Collection<T> choices,
			ToStringFunction<T> toStringFunction, Applicable func) {

		List<BoundExtractedResult<T>> extracted = extractWithoutOrder(query, choices, toStringFunction, func);

		return Collections.max(extracted);

	}

	/**
	 * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain the
	 * top @param limit most similar choices
	 *
	 * @param query   The query string
	 * @param choices A list of choices
	 * @param func    The scoring function
	 * @return A list of the results
	 */
	public List<ExtractedResult> extractTop(String query, Collection<String> choices, Applicable func) {
		List<ExtractedResult> best = extractWithoutOrder(query, choices, func);
		Collections.sort(best, Collections.<ExtractedResult>reverseOrder());

		return best;
	}

	public List<ExtractedResult> extractTopMap(String query, Map<String, String> choices, Applicable func) {
		List<ExtractedResult> best = extractWithoutOrderMap(query, choices, func);
		Collections.sort(best, Collections.<ExtractedResult>reverseOrder());
		return best;
	}
	
	public <T> List<ExtractedResult> extractTopMap2(String query, Map<Class<T>, String> choices, Applicable func) {
		List<ExtractedResult> best = extractWithoutOrderMap2(query, choices, func);
		Collections.sort(best, Collections.<ExtractedResult>reverseOrder());
		return best;
	}

	/**
	 * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain the
	 * top @param limit most similar choices
	 *
	 * @param query            The query string
	 * @param choices          A list of choices
	 * @param toStringFunction The ToStringFunction to be applied to all choices.
	 * @param func             The scoring function
	 * @return A list of the results
	 */
	public <T> List<BoundExtractedResult<T>> extractTop(String query, Collection<T> choices,
			ToStringFunction<T> toStringFunction, Applicable func) {

		List<BoundExtractedResult<T>> best = extractWithoutOrder(query, choices, toStringFunction, func);
		Collections.sort(best, Collections.<BoundExtractedResult<T>>reverseOrder());

		return best;
	}

	/**
	 * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain the
	 * top @param limit most similar choices
	 *
	 * @param query   The query string
	 * @param choices A list of choices
	 * @param limit   Limits the number of results and speeds up the search (k-top
	 *                heap sort) is used
	 * @return A list of the results
	 */
	public List<ExtractedResult> extractTop(String query, Collection<String> choices, Applicable func, int limit) {
		List<ExtractedResult> best = extractWithoutOrder(query, choices, func);

		List<ExtractedResult> results = Utils.findTopKHeap(best, limit);
		Collections.reverse(results);

		return results;
	}

	/**
	 * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain the
	 * top @param limit most similar choices
	 *
	 * @param query            The query string
	 * @param choices          A list of choices
	 * @param toStringFunction The ToStringFunction to be applied to all choices.
	 * @param limit            Limits the number of results and speeds up the search
	 *                         (k-top heap sort) is used
	 * @return A list of the results
	 */
	public <T> List<BoundExtractedResult<T>> extractTop(String query, Collection<T> choices,
			ToStringFunction<T> toStringFunction, Applicable func, int limit) {

		List<BoundExtractedResult<T>> best = extractWithoutOrder(query, choices, toStringFunction, func);

		List<BoundExtractedResult<T>> results = Utils.findTopKHeap(best, limit);
		Collections.reverse(results);

		return results;
	}

	public int getCutoff() {
		return cutoff;
	}

	public void setCutoff(int cutoff) {
		this.cutoff = cutoff;
	}
}
