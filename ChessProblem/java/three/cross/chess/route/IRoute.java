package three.cross.chess.route;


public interface IRoute<T> {
	
	public void process(T value) throws Exception;

}
