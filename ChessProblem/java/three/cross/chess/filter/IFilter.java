package three.cross.chess.filter;

public interface IFilter<T> {
	
	public boolean accept(T value);

}
