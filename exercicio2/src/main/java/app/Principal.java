package app;

import java.util.List;

import dao.DAO;
import dao.XDAO;
import model.X;

import java.util.Scanner;

public class Principal {
	static int selectedOption;
	
	public static void main(String[] args) throws Exception {
	    Scanner scanner = new Scanner (System.in);
		XDAO xDAO = new XDAO();
			while(true) {
				menu();
			switch (selectedOption) {
				case 1: 
					showXs();
					break;
				case 2:
					List<X> baseXs = xDAO.getOrderByCodigo();
					System.out.println("Digite o valor de Y:");
					String Y = scanner.nextLine();
					int codigo = baseXs.size() > 0 ? baseXs.get(baseXs.size() - 1).getCodigo() + 1 : 1;
					X x = new X(codigo, Y);
					if(xDAO.insert(x) == true) {
						System.out.println("Inserção com sucesso -> " + x.toString());
					}
					break;
				case 3:
					showXs();
					System.out.println("Digite o codigo do registro que deseja excluir:");
					int selectedCodigoToExclude = scanner.nextInt();
					xDAO.delete(selectedCodigoToExclude);
					break;
				case 4:
					showXs();
					System.out.println("Digite o codigo do registro que deseja atualizar:");
					int selectedCodigoToUpdate = scanner.nextInt();
					scanner.nextLine();
					X xData = xDAO.get(selectedCodigoToUpdate);
					System.out.println("Novo valor de Y:");
					String updateY = scanner.nextLine();
					xDAO.update(xData, updateY);
					break;
				case 5:
			        System.out.println("Saindo do programa...");
			        System.exit(0);
			         break;
				default:
					System.out.println("Invalid Option!");
			}
		}
	}
	
	private static void menu() {
	    Scanner scanner = new Scanner (System.in);
		
		System.out.println("Menu: \n");
		System.out.println("1: Listar \n");
		System.out.println("2: Inserir \n");
		System.out.println("3: Excluir \n");
		System.out.println("4: Atualizar \n");
		System.out.println("5: Sair \n");
		selectedOption = scanner.nextInt();
		scanner.nextLine();
	}

	private static void showXs() {
		XDAO xDAO = new XDAO();
		List<X> xs = xDAO.getOrderByCodigo();
		System.out.println("Xs: \n");
		for (X x: xs) {
			System.out.println(x.toString());
		}
	}
}