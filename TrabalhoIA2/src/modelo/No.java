package modelo;
import java.util.ArrayList;
import java.util.Scanner;

public class No {
	private Estado estado;
	private No pai;
	private No irmaoDireito;
	private ArrayList<No> filhos = new ArrayList<No>();
	private boolean raiz;
	private boolean expandido;
	
	public No(No pai, Estado estado){
		if(pai == null){
			raiz = true;
		}
		else{
			raiz = false;
		}
		this.pai = pai;
		this.estado = estado;
		this.irmaoDireito = null;
		this.expandido = false;
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
	
	public ArrayList<No> getFilhos(){
		return this.filhos;
	}
	
	public void setIrmaoDireito(No irmaoDireito){
		this.irmaoDireito = irmaoDireito;
	}
	
	public No getIrmaoDireito(){
		return this.irmaoDireito;
	}
	
	public boolean isRaiz(){
		return raiz;
	}
	
	public boolean isExpandido() {
		return this.expandido;
	}
	
	public void expandir(){
		this.expandido = true;
	}
}
