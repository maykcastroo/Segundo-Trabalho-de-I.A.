package modelo;
import java.util.ArrayList;
import java.util.Scanner;

public class No {
	private Estado estado;
	private No pai;
	private ArrayList<No> filhos = new ArrayList<No>();
	private boolean raiz;
	
	public No(No pai, Estado estado){
		if(pai == null){
			raiz = true;
		}
		else{
			raiz = false;
		}
		this.pai = pai;
		this.estado = estado;
	}
	
	public Estado getEstado(){
		return estado;
	}
	
	public No getPai(){
		return pai;
	}
	
	public boolean addFilho(No filho){
		filhos.add(filho);
		return true;
	}
}
