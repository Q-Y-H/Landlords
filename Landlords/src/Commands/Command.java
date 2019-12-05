package Commands;

public interface Command<ResultType> {
	void execute();
	ResultType getResult();
}
