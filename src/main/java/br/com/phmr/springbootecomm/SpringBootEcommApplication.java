package br.com.phmr.springbootecomm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.phmr.springbootecomm.domain.Categoria;
import br.com.phmr.springbootecomm.domain.Cidade;
import br.com.phmr.springbootecomm.domain.Cliente;
import br.com.phmr.springbootecomm.domain.Endereco;
import br.com.phmr.springbootecomm.domain.Estado;
import br.com.phmr.springbootecomm.domain.ItemPedido;
import br.com.phmr.springbootecomm.domain.Pagamento;
import br.com.phmr.springbootecomm.domain.PagamentoComBoleto;
import br.com.phmr.springbootecomm.domain.PagamentoComCartao;
import br.com.phmr.springbootecomm.domain.Pedido;
import br.com.phmr.springbootecomm.domain.Produto;
import br.com.phmr.springbootecomm.domain.enums.EstadoPagamento;
import br.com.phmr.springbootecomm.domain.enums.TipoCliente;
import br.com.phmr.springbootecomm.repositories.CategoriaRepository;
import br.com.phmr.springbootecomm.repositories.CidadeRepository;
import br.com.phmr.springbootecomm.repositories.ClienteRepository;
import br.com.phmr.springbootecomm.repositories.EnderecoRepository;
import br.com.phmr.springbootecomm.repositories.EstadoRepository;
import br.com.phmr.springbootecomm.repositories.ItemPedidoRepository;
import br.com.phmr.springbootecomm.repositories.PagamentoRepository;
import br.com.phmr.springbootecomm.repositories.PedidoRepository;
import br.com.phmr.springbootecomm.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringBootEcommApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	EstadoRepository estadoRepository;
	@Autowired
	CidadeRepository cidadeRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	PagamentoRepository pagamentoRepository;
	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEcommApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var cat1 = new Categoria(null, "Informática");
		var cat2 = new Categoria(null, "Escritório");
		var cat3 = new Categoria(null, "Teste (Cat3)");

		var p1 = new Produto(null, "Computador", 2500.00);
		var p2 = new Produto(null, "Impressora", 740.98);
		var p3 = new Produto(null, "Mouse", 27.40);
		var p4 = new Produto(null, "Prod. Teste (Prod4)", 27.40);

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));

		String[][] nomesUF = { { "Minas Gerais", "MG", "Uberlândia;Belo Horizonte;Lagamar" },
				{ "Sao Paulo", "SP", "São Paulo;Campinas;Campos do Jordão" },
				{ "Rio de Janeiro", "RJ", "Rio de Janeiro; Búzios; Ilha Grande" },
				{ "Espírito Santo", "ES", "Vitória;Vila Velha" },
				{ "Rio Grande do Sul", "RS", "Porto Alegre;Santa Cruz do Sul" },
				{ "Santa Catarina", "SC", "Florianópolis;Balneário Camburiú" }, { "Paraná", "PR" },
				{ "Distrito Federal", "DF" }, { "Goiás", "GO" }, { "Mato Grosso", "MT" },
				{ "Mato Grosso do Sul", "MS" }, { "Acre", "AC" }, { "Roraima", "RR" }, { "Rondônia", "RO" },
				{ "Amazonas", "AM" }, { "Maranhão", "MA" }, { "Pará", "PA" }, { "Tocantins", "TO" }, { "Piauí", "PI" },
				{ "Amapá", "AP" }, { "Bahia", "BA" }, { "Sergipe", "SE" }, { "Alagoas", "AL" }, { "Paraíba", "PB" },
				{ "Rio Grande do Norte", "RN" }, { "Pernambuco", "PE" }, { "Ceará", "CE" } };

		for (String[] nomeUF : nomesUF) {
			Estado estado = new Estado(null, nomeUF[0], nomeUF[1]);
			estadoRepository.save(estado);
			if (nomeUF.length == 3) {
				String[] nomeCidades = nomeUF[2].split(";");
				for (String nomeCidade : nomeCidades) {
					Cidade cidade = new Cidade(null, nomeCidade, estado);
					cidadeRepository.save(cidade);
				}
			}
		}

		Cliente cli1 = new Cliente(null, "João Ninguém", "joaoninguem@teste.com.br", "12345678901",
				TipoCliente.PESSOA_FISICA);

		Cliente cli2 = new Cliente(null, "JN Consultoria Java", "jn@cons.com.br", "99922244455511",
				TipoCliente.PESSOA_JURIDICA);

		cli1.getTelefones().addAll(Arrays.asList("56123456768", "167852345876"));
		cli2.getTelefones().addAll(Arrays.asList("78989945678", "869812345876"));

		Optional<Cidade> cid = cidadeRepository.findById(1);
		Endereco end1 = new Endereco(null, "Rua das Estrelas", "1254", "Apto 701", "Cidade Jardim", "38412751", cli1,
				cid.orElse(null));

		cid = cidadeRepository.findById(2);
		Endereco end2 = new Endereco(null, "Rua da Alegria", "13", "Casa", "Andrômeda", "76543443", cli1,
				cid.orElse(null));

		cid = cidadeRepository.findById(3);
		Endereco end3 = new Endereco(null, "Av da Evolução", "7113", "Casa", "Magalhães", "87699943", cli2,
				cid.orElse(null));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		Pedido ped1 = new Pedido(null, sdf.parse("13/10/2020 13:15"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("29/07/2019 17:27"), cli2, end2);

		Pagamento pag1 = new PagamentoComBoleto(null, EstadoPagamento.QUITADO, ped1, sdf.parse("30/11/2020 21:00"),
				sdf.parse("01/12/2020 00:00"));
		ped1.setPagamento(pag1);
		Pagamento pag2 = new PagamentoComCartao(null, EstadoPagamento.PARCELADO, ped2, 7);
		ped2.setPagamento(pag2);

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 1.7, 7, 157.71);
		ItemPedido ip2 = new ItemPedido(ped2, p2, 8.50, 145, 90.70);
		ItemPedido ip3 = new ItemPedido(ped2, p3, 18.87, 13, 107.90);

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
