package three.cross.chess.router;

import three.cross.chess.domain.ICreate;
import three.cross.chess.domain.IDestroy;

public interface IRouter<T> extends ICreate, IDestroy{

	public boolean process (T value) throws Exception;

}
