<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livraria Online - Compras por Cliente</title>
<script>
document.addEventListener("DOMContentLoaded", function(){
	document.getElementById("selectCliente").value = "${clienteId }";
});

function carregar(select){
	var valor = encodeURIComponent(select.value);

	if(valor > 0){
		window.location="comprasPorCliente?clienteId=" + valor;
	} else {
		window.location="comprasPorCliente";
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
	<select id="selectCliente" name="cliente" onchange="carregar(this)">
		<c:forEach items="${clientes }" var="cliente">
        	<option value="${cliente.key }">${cliente.value }</option>
        </c:forEach>
    </select>
    
    <h2>Compras</h2>
    <c:if test="${compras.size() > 0 }"> 
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Data</th>
					<th>Cliente</th>
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
	</c:if>
	<c:if test="${compras.size() <= 0 }">
		<h3>Nenhuma compra encontrada.</h3>
	</c:if>
	
</body>
</html>