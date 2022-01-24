<%@ page session = "false" %>
<jsp:include page = "/layout/header.jsp" />

<div class="wrapper">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1>Success</h1>
                <p><%= request.getParameter( "message" ) %></p>

                <a href="index.jsp" style="margin-top: 30px;">Go to homepage.</a>
            </div>
        </div>
    </div>
</div>

<jsp:include page = "/layout/footer.jsp" />