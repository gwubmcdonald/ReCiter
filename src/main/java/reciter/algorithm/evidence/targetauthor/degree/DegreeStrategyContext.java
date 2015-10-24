package reciter.algorithm.evidence.targetauthor.degree;

import java.util.List;

import reciter.algorithm.evidence.targetauthor.TargetAuthorStrategy;
import reciter.algorithm.evidence.targetauthor.TargetAuthorStrategyContext;
import reciter.model.article.ReCiterArticle;
import reciter.model.author.TargetAuthor;

public class DegreeStrategyContext implements TargetAuthorStrategyContext {
	private final TargetAuthorStrategy strategy;
	
	public DegreeStrategyContext(TargetAuthorStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public double executeStrategy(ReCiterArticle reCiterArticle, TargetAuthor targetAuthor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double executeStrategy(List<ReCiterArticle> reCiterArticles, TargetAuthor targetAuthor) {
		// TODO Auto-generated method stub
		return 0;
	}
}