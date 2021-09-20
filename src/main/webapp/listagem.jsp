<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listagem Geral</title>
<style type="text/css">
table {
	border: 1px solid black;
	border-collapse: collapse;
}

td, th {
	border: 1px solid black;
}
</style>
</head>
<body>

<a href="/"><button>Voltar</button></a>
<h2>Livros</h2>
<table>
	<thead>
		<tr>
			<th>ID</th>
			<th>Titulo</th>
			<th>ISBN</th>
			<th>Autor(es)</th>
			<th>Editora</th>
			<th>Ano de Publicação</th>
			<th>Preço</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="livro" items="${livros }" >
			<tr>
				<td>${livro.id }</td>
				<td>${livro.titulo }</td>
				<td>${livro.ISBN }</td>
				<td>${livro.autores }</td>
				<td>${livro.editora }</td>
				<td>${livro.anoPublicacao }</td>
				<td>${livro.preco }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<h2>Autores</h2>
<table>
	<thead>
		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Apelido</th>
			<th>Nacionalidade</th>
			<th>Livros</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="autor" items="${autores }" >
			<tr>
				<td>${autor.id }</td>
				<td>${autor.nome }</td>
				<td>${autor.apelido }</td>
				<td>${autor.nacionalidade }</td>
				<td>${autor.livros }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<h2>Clientes</h2>
<table>
	<thead>
		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Apelido</th>
			<th>Nacionalidade</th>
			<th>Email</th>
			<th>Senha</th>
			<th>Cartão de Crédito</th>
			<th>Profissão</th>
			<th>Endereço</th>
			<th>Telefone</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="cliente" items="${clientes }" >
			<tr>
				<td>${cliente.id }</td>
				<td>${cliente.nome }</td>
				<td>${cliente.apelido }</td>
				<td>${cliente.nacionalidade }</td>
				<td>${cliente.email }</td>
				<td>${cliente.senha }</td>
				<td>${cliente.numeroCartaoCredito }</td>
				<td>${cliente.profissao }</td>
				<td>${cliente.endereco }</td>
				<td>${cliente.telefone }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<h2>Compras</h2>
<table>
	<thead>
		<tr>
			<th>ID</th>
			<th>Cliente</th>
			<th>Data</th>
			<th>Livros</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="compra" items="${compras }" >
			<tr>
				<td>${compra.id }</td>
				<td>${compra.data }</td>
				<td>${compra.cliente }</td>
				<td>${compra.livros }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<h2>Áreas Científicas</h2>
<table>
	<thead>
		<tr>
			<th>ID</th>
			<th>Descricao</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="areaCientifica" items="${areasCientificas }" >
			<tr>
				<td>${areaCientifica.id }</td>
				<td>${areaCientifica.descricao }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>