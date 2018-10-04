package br.bruno.AulaTeste02;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.bruno.AulaTeste02.Banco;
import br.bruno.AulaTeste02.Conta;
import br.bruno.AulaTeste02.ContaBuilder;
import br.bruno.AulaTeste02.ContaDao;
import br.bruno.AulaTeste02.Dao;
import br.bruno.AulaTeste02.Usuario;

import static org.mockito.Mockito.*;



public class BancoTest {
	
	private Conta c1;
	private Conta c2;
	private Conta c3;
	private Conta c4;
	private Usuario joao;
	private Usuario manoel;
	private Usuario joaquim;
	private Usuario maria;

	@Before
	public void criarAmbiente(){
		c1 = new Conta(100.0, "Basica");
		c2 = new Conta(200.0, "Prime");
		c3 = new Conta(0.0, "Estudante");
		c4 = new Conta(0.0, "Estudante");
		
		joao = new Usuario("Joao da Silva", "111111111", "joaosilva@mailinator.com");
		manoel = new Usuario("Manoel da Silva", "22222222", "manoelsilva@mailinator.com");
		joaquim = new Usuario("Joaquim da SIlva", "333333333", "joaosilva@mailinator.com");
		maria = new Usuario("Maria da Silva", "3333333333", "manoelsilva@mailinator.com");
	}
	

	
	@Test
	public void deveSomarTodasAsContasTrazendoOSaldoDoBanco(){
		
		List<Conta> contas = new ContaBuilder() 
		.addConta(c1, joao)
		.addConta(c2, manoel)
		.addConta(c3, joaquim)
		.addConta(c4, maria)
		.constroi();
		
		/* ContaDaoFalso dao = new ContaDaoFalso();*/

		 Dao dao = mock(ContaDao.class);		
		 dao.salvaConta(contas.get(0));
		 dao.salvaConta(contas.get(1));
		 when(dao.getContas()).thenReturn(contas);
		 
		 doThrow(new RuntimeException()).when(dao).atualizaConta(c3);
			doThrow(new RuntimeException()).when(dao).atualizaConta(c4);
			
			Banco banco = new Banco(dao);
			banco.atualizaJuros(5);
			
			// verificando que o metodo atualizaConta foi realmente invocado!
			verify(dao, times(1)).atualizaConta(contas.get(0));
			
			assertEquals(4, banco.getContas().size(), 0.00001);
		 
		// Banco banco = new Banco(null);
		 
		 ;
		 assertEquals(300, banco.totalSaldo(), 0.00001);
	}
}