package reciter.algorithm.evidence.targetauthor.grant.strategy;

import java.util.List;

import reciter.algorithm.evidence.targetauthor.AbstractTargetAuthorStrategy;
import reciter.model.article.ReCiterArticle;
import reciter.model.author.AuthorName;
import reciter.model.author.ReCiterAuthor;
import reciter.model.author.TargetAuthor;

public class CoauthorStrategy extends AbstractTargetAuthorStrategy {

	@Override
	public double executeStrategy(ReCiterArticle reCiterArticle, TargetAuthor targetAuthor) {

		List<AuthorName> authorNames = targetAuthor.getGrantCoauthors();

		for (ReCiterAuthor author : reCiterArticle.getArticleCoAuthors().getAuthors()) {
			// do not match target author's name
			if (!author.getAuthorName().firstInitialLastNameMatch(targetAuthor.getAuthorName())) {
				for (AuthorName authorName : authorNames) {
					if (authorName.isFullNameMatch(author.getAuthorName())) {
						return 1;
					}
				}
			}
		}
		return 0;
	}

	@Override
	public double executeStrategy(List<ReCiterArticle> reCiterArticles, TargetAuthor targetAuthor) {
		// TODO Auto-generated method stub
		return 0;
	}

}