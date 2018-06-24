package netbanking.sort;

import java.util.Comparator;

import netbanking.pojo.dao.StatementVO;

public class StatementSorterByDate implements Comparator<StatementVO>{

	@Override
	public int compare(StatementVO o1, StatementVO o2) {
		return o1.getDateInDateFormat().compareTo(o2.getDateInDateFormat()) * -1;
	}

}
