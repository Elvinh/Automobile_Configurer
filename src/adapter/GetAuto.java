package adapter;

import java.util.Set;
import model.Automobile;

public interface GetAuto {
	public Automobile getAutoObj(String name);
	public Set<?> getKeys();
}
