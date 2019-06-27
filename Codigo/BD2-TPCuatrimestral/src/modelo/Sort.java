package modelo;

import java.util.ArrayList;

public class Sort {
	
	public static void bubbleSort(ArrayList<RankingClientes> list) {
		RankingClientes aux = null;
		for (int i=0; i < list.size() ; i++) {
			for (int j=0 ; j < list.size() ; j++) {
				if (list.get(j).getMontoCompra() < list.get(j).getMontoCompra()) {
					aux = list.get(j);
					list.get(j).setCliente(list.get(j+1).getCliente());
					list.get(j).setMontoCompra(list.get(j+1).getMontoCompra());
					list.get(j+1).setCliente(aux.getCliente());
					list.get(j+1).setMontoCompra(aux.getMontoCompra());
				}
			}
			
		}
		
	}

}
