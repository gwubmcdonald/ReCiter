package reciter.algorithm.evidence.article.journal.strategy;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import reciter.algorithm.evidence.article.AbstractReCiterArticleStrategy;
import reciter.model.article.ReCiterArticle;
import reciter.model.author.ReCiterAuthor;
import reciter.model.author.TargetAuthor;

public class JournalStrategy extends AbstractReCiterArticleStrategy {

	private final TargetAuthor targetAuthor;

	public JournalStrategy(TargetAuthor targetAuthor) {
		this.targetAuthor = targetAuthor;
	}

	@Override
	public double executeStrategy(ReCiterArticle reCiterArticle, ReCiterArticle otherReCiterArticle) {
		double score = 0;
		boolean isJournalMatch = isJournalMatch(reCiterArticle, otherReCiterArticle);
		if (isJournalMatch) {
			// If the two articles' target author share the same first name, then it's likely that
			// these two articles are written by the same target author.
			for (ReCiterAuthor author : reCiterArticle.getArticleCoAuthors().getAuthors()) {
				boolean isFirstNameMatch = StringUtils.equalsIgnoreCase(
						author.getAuthorName().getFirstName(), targetAuthor.getAuthorName().getFirstName());

				if (isFirstNameMatch) {
					for (ReCiterAuthor otherAuthor : otherReCiterArticle.getArticleCoAuthors().getAuthors()) {
						boolean isOtherFirstNameMatch = StringUtils.equalsIgnoreCase(
								otherAuthor.getAuthorName().getFirstName(), targetAuthor.getAuthorName().getFirstName());
						
						if (isOtherFirstNameMatch) {
							score += 1;
						}
					}
				}
			}
		}
		reCiterArticle.setJournalStrategyScore(score);
		return score;
	}

	@Override
	public double executeStrategy(List<ReCiterArticle> reCiterArticles, ReCiterArticle otherReCiterArticle) {
		double sum = 0;
		for (ReCiterArticle reCiterArticle : reCiterArticles) {
			sum += executeStrategy(reCiterArticle, otherReCiterArticle);
		}
		return sum;
	}

	/**
	 * If a candidate article is published in a journal and another article contains that journal, return true. False
	 * otherwise.
	 * 
	 * Github issue: https://github.com/wcmc-its/ReCiter/issues/83
	 */
	private  boolean isJournalMatch(ReCiterArticle reCiterArticle, ReCiterArticle otherReCiterArticle) {

		if (reCiterArticle.getJournal() != null && 
				otherReCiterArticle.getJournal() != null && 
				reCiterArticle.getJournal().getJournalTitle() != null &&
				otherReCiterArticle.getJournal() != null) {
			return reCiterArticle.getJournal().getJournalTitle().equalsIgnoreCase(otherReCiterArticle.getJournal().getJournalTitle());
		}
		return false;
	}
}