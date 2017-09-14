<h3>Envio de arquivos em Base64</h3>
<form enctype="multipart/x-www-form-urlencoded" 
	action="<%=request.getContextPath()%>/rest/carros/postFotoBase64"
	method="post">
	
	FileName:
	<input name="fileName" type="text" />
	<br /><br />
	Base64:
	<textarea name="base64" type="textarea" cols="100" rows="10"></textarea>
	<br /><br />
	<input type="submit" value="Enviar arquivo" />
</form>

<a href="upload.jsp">Voltar</a>