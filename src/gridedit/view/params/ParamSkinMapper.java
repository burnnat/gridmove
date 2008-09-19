package gridedit.view.params;

import gridmove.model.Skin;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ParamSkinMapper extends JPanel implements ParamComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> filepaths;
	private JTable myTable;
	
	public ParamSkinMapper(Skin skin) {
		filepaths = skin.getMap();
		
		
		SortedMap<String, String> sortedMap = new TreeMap<String, String>(filepaths);
		
		for (String s : Skin.CLASSIC_EDITOR.getMap().keySet()) {
			sortedMap.put(s, filepaths.get(s));
		}
		
		String[][] data = new String[sortedMap.size()][2];
		
		int i = 0;
		for (String key : sortedMap.keySet()) {
			data[i][0] = key;
			data[i][1] = filepaths.get(key);
			i++;
		}
		
		myTable = new JTable(data, new String[] {"Tile Type", "Filepath"});
		
		JScrollPane scrollPane = new JScrollPane(myTable);
		
		add(scrollPane);
	}

	public Map<String, String> getParamValue() {
		filepaths.clear();
		for (int i = 0; i < myTable.getRowCount(); i++) {
			filepaths.put((String)myTable.getValueAt(i, 0), (String)myTable.getValueAt(i, 1));
		}
		return filepaths;
	}

}
