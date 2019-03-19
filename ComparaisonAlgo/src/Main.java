import java.util.Random;

import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.classifiers.Evaluation;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


 
public class Main {
	
	protected static void useClassifier(Instances data) throws Exception {
	    AttributeSelectedClassifier classifier = new AttributeSelectedClassifier();
	    CfsSubsetEval eval = new CfsSubsetEval();
	    GreedyStepwise search = new GreedyStepwise();
	    search.setSearchBackwards(true);
	    J48 base = new J48();
	    classifier.setClassifier(base);
	    classifier.setEvaluator(eval);
	    classifier.setSearch(search);
	    Evaluation evaluation = new Evaluation(data);
	    evaluation.crossValidateModel(classifier, data, 5, new Random(1));
	    System.out.println(evaluation.toSummaryString());
	  }
	
	public static void main(String[] args) {	
		
		try {
			DataSource source = new DataSource("/home/etudiant/Téléchargements/weka-3-8-3/data/optdigits.arff");
			Instances data = source.getDataSet();
			if(data.classIndex() == -1){
				data.setClassIndex(data.numAttributes() -1);
			}
			useClassifier(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
