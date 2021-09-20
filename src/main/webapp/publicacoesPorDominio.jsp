<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livraria Online - Publicações Por Domínio</title>
<script>
document.addEventListener("DOMContentLoaded", function(){
	document.getElementById("selectDominio").value = "${publicacaoId }";
});

function carregar(select){
	var valor = encodeURIComponent(select.value);

	if(valor > 0){
		window.location="publicacoesPorDominio?dominioId=" + valor;
	} else {
		window.location="publicacoesPorDominio";
	}
}

</script>
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
	<select id="selectDominio" name="dominio" onchange="carregar(this)">
		<c:forEach items="${dominios }" var="dominio">
        	<option value="${dominio.key }">${dominio.value }</option>
        </c:forEach>
    </select>
    
    <h2>Livros</h2>
    <c:if test="${livros.size() > 0 }"> 
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Titulo</th>
					<th>Autores</th>
					<th>ISBN</th>
					<th>Editora</th>
					<th>Ano de Publicação</th>
					<th>Preço</th>
					<th>Área Científica</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="livro" items="${livros }" >
					<tr>
						<td>${livro.id }</td>
						<td>${livro.titulo }</td>
						<td>
							<c:forEach var="autor" items="${livro.autores }" >
								${autor.nome }
							</c:forEach>
						</td>
						<td>${livro.ISBN }</td>
						<td>${livro.editora }</td>
						<td>${livro.anoPublicacao }</td>
						<td>R$ ${livro.preco }</td>
						<td>${livro.areaCientifica.descricao }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${compras.size() <= 0 }">
		<h3>Nenhuma livro encontrado.</h3>
	</c:if>
	
</body>
</html>