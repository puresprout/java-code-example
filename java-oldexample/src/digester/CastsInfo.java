package digester;

import java.util.ArrayList;
import java.util.List;

public class CastsInfo {

	private List<Cast> casts;

	public List<Cast> getCasts() {
		return casts;
	}

	public void setCasts(List<Cast> casts) {
		this.casts = casts;
	}

	public CastsInfo() {
		casts = new ArrayList<Cast>();
	}

	public void addCast(Cast cast) {
		casts.add(cast);
	}

}
