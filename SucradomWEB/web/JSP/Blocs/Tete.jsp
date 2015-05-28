<%@page import="sucradom.utile.Base"%>
<%
    String teteHref = Base.CONTEXT_PATH;
%>
<div class="navbar navbar-fixed-top" >
    <div  Style="font-weight: inherit; background-color: #FFD093; border-color:#ad6704">
        <div class="container" >
            <div class="pull-left">
                <a class="brand" href='<%=teteHref%>/Accueil'>
                    <img src="http://localhost/Ecommerce-Templates%20(1)/Ecommerce-Templates/frontend/img/paques1.jpg" alt="" width="120"/>
                </a>
                <a class="brand" href='<%=teteHref%>/Accueil'>Accueil</a>
            </div>


            <div class="pull-right">
                <a class="brand" href='<%=teteHref%>/Catalogue'>Catalogue</a>
                <a class="brand" href='<%=teteHref%>/Panier'>Panier</a>
                <a class="brand" href='<%=teteHref%>/Compte'>Compte</a>
                <a class="brand" href='<%=teteHref%>/Connexion'>Connexion</a>




            </div>


            <div class="nav-collapse collapse">
                <form class="navbar-form form-search pull-right">
                    <input id="Search" name="Search" type="text" class="input-medium search-query">
                    <button type="submit" class="btn">Rechercher</button>
                </form>
            </div>
        </div>
    </div>
</div>
<br>


