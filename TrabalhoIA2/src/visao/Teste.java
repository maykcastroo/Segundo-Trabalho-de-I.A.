package visao;

import java.util.Scanner;

import controle.Controle;
import modelo.Estado;
import modelo.No;

public class Teste {
	public static void main(String args []){
		int quant = 4;
		int capacidadeCanoa = (int) quant/2 + 1;
		
		Controle c = new Controle(capacidadeCanoa);
		Estado e = new Estado(false, quant, 0, quant, 0);
		No noInicial = new No(null, e);
	
		System.out.println("Legenda: lado onde a canoa se encontra, missionarioas na esquerda, missionarios na direita, canibais na esquerda, canibais na direita");
		System.out.println("true = canoa está na margem direita\n"
				+ "false = canoa está na margem esquerda\n");
		System.out.println("No Inicial: "+e.getMargemCanoa()+", "+quant+", 0, "+quant+", 0");
		Scanner input = new Scanner(System.in);
		String pausa = input.next();
		c.buscaEmProfundidade(noInicial);
	}
}
