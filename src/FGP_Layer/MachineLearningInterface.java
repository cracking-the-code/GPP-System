package FGP_Layer;

public interface MachineLearningInterface 
{
	void train(String csvPath, int period);
	void predict();
	String persistJson();
}
