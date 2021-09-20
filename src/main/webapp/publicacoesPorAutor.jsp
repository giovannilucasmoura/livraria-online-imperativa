<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livraria Online - Autor por Publicação</title>
<script>
document.addEventListener("DOMContentLoaded", function(){
	document.getElementById("selectAutor").value = "${autorId }";
});

function carregar(select){
	var valor = encodeURIComponent(select.value);

	if(valor > 0){
		window.location="publicacoesPorAutor?autorId=" + valor;
	} else {
		window.location="publicacoesPorAutor";
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
	<select id="selectAutor" name="livro" onchange="carregar(this)">
		<c:forEach items="${autores }" var="autor">
        	<option value="${autor.key }">${autor.value }</option>
        </c:forEach>
    </select>
    
    <h2>Livros</h2>

    <c:if test="${livros.size() > 0 }"> 
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
	</c:if>
	<c:if test="${autores.size() <= 0 }">
		<h3>Nenhum autor encontrado.</h3>
	</c:if>
	
</body>
</html>