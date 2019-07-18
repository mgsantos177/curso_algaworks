package com.example.demo.algamoney.api.security.Util;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {
	
	public static void main(String [] args ) {
		
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		Scanner s = new Scanner(System.in);
		System.out.println("Digite a senha a ser Codada: ");
		String senha = s.next();
		System.out.println(enconder.encode(senha));	
	}

}
