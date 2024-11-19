package Excepciones;

import java.util.Scanner;

class SaldoInsuficienteException extends Exception{
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}

public class CuentaBancaria {
	public static void main(String[] args) throws Exception {
		int saldo = 500;
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el monto de dinero que desea retirar: ");
		int monto = sc.nextInt();
		int saldoact = saldo - monto;
		if (monto > saldo) {
			throw new SaldoInsuficienteException("El monto a retirar no puede ser mayor que el sueldo.");
		} else {
			System.out.println("Retiro de monto completado, ha retirado: " + monto + "€ ");
			System.out.println("Su saldo actual es de: " + saldoact + "€ ");
		}
	}
}
