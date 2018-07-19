<%-- 
    Document   : Juego
    Created on : 16-nov-2016, 19:02:49
    Author     : Leonardo
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
        <title>Lunar Landing in HTML5</title>
        <link rel="stylesheet" href="css/style.css">
        <script type="text/javascript" src="js/jq.js"></script>
        <script src="<c:url value="js/lunar.js"/>"></script>
        <script src="<c:url value="js/dataaccess.js"/>"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>

        <!--script src="https://code.jquery.com/jquery-1.10.2.js"></script-->
    </head>

    <body>

        <div id="login">
            <h1 id="title">Juego de prueba de la practica 3.4.3 de acceso a datos</h1>
            <br>
            <br>

            <div id="somediv"></div>

            <script>
                var idmatch;
                $(document).on("submit", "#form1", function (event) {
                    var $form = $('#form1');
                    var done = "/login";
                    $.post($form.attr("action"), $form.serialize(), function (response) {
                        if (done.localeCompare(response) == 0) {
                            $("#somediv").text("Login done");
                            $("#ventanalr").hide();
                            $("#ventanamenu").show();
                        } else {
                            $("#somediv").text(response);
                        }
                    });
                    event.preventDefault(); // Important! Prevents submitting the form.
                });
                $(document).on("submit", "#form2", function (event) {
                    var $form = $('#form2');
                    $.post($form.attr("action"), $form.serialize(), function (response) {
                        $("#somediv").text(response);
                    });
                    event.preventDefault(); // Important! Prevents submitting the form.
                });
                $(document).on("click", "#buttonhp", function (event) { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
                    $.post("Historialpartidas", function (responseJson) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                        $("#ventanaresultado").show();
                        $("#ventanaresultado").text("Historialpartidas");           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                        var $ul = $("<ul>").appendTo($("#ventanaresultado"));
                        $.each(responseJson, function (index, item) { // Iterate over the JSON array.
                            $("<li>").text(item).appendTo($ul);
                            //$("#ventanaresultado").append(item);      // Create HTML <li> element, set its text content with currently iterated item and append it to the <ul>. 
                        });
                    });
                    event.preventDefault();
                });
                $(document).on("click", "#buttonjo", function (event) { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
                    $.post("Jugadoresonline", function (responseJson) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                        $("#ventanaresultado").show();
                        $("#ventanaresultado").text("Jugadores online");           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                        var $ul = $("<ul>").appendTo($("#ventanaresultado"));
                        $.each(responseJson, function (index, item) { // Iterate over the JSON array.
                            $("<li>").text(item).appendTo($ul);
                            //$("#ventanaresultado").append(item);      // Create HTML <li> element, set its text content with currently iterated item and append it to the <ul>. 
                        });
                    });
                    event.preventDefault();
                });
                $(document).on("click", "#buttont10", function (event) { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
                    $.post("Top10", function (responseJson) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                        $("#ventanaresultado").show();
                        $("#ventanaresultado").text("Top 10");           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                        var $ul = $("<ul>").appendTo($("#ventanaresultado"));
                        $.each(responseJson, function (index, item) { // Iterate over the JSON array.
                            $("<li>").text(item).appendTo($ul);
                            //$("#ventanaresultado").append(item);      // Create HTML <li> element, set its text content with currently iterated item and append it to the <ul>. 
                        });
                    });
                    event.preventDefault();
                });
                $(document).on("click", "#buttonju", function (event) { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
                    $.get("Jugar", function (response) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                        idmatch = response;
                        $("#login").hide();
                        reset();
                    });
                    event.preventDefault();
                });
                $(document).on("click", "#buttonjumenu", function (event) { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
                    var data = "datamatch=" + idmatch + "," + ship.v;
                    $.post("Jugar", data, function (response) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                        $("#login").show()
                        $("#state").hide();
                    });
                    event.preventDefault();
                });
                $(document).on("click", "#buttonjurestart", function (event) { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
                    var data = "datamatch=" + idmatch + "," + ship.v;
                    $.post("Jugar", data, function (response) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                    });
                    $.get("Jugar", function (response) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                        idmatch = response;
                        $("#login").hide();
                        reset();
                    });
                    event.preventDefault();
                });
                $(document).on("click", "#buttoncerrar", function (event) { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
                    $.post("Cerrarsesion", function (response) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                        $("#somediv").text("");
                        $("#ventanaresultado").text("");   
                        $("#ventanalr").show();
                        $("#ventanamenu").hide();
                    });
                    event.preventDefault();
                });
            </script>
            <br>
            <div id="ventanalr">
                <br>
                <p1>Login</p1>
                <form id="form1" method="post" action="login">
                    Usuario <input type="text" name="name"><br>
                    Password <input type="text" name="pass"><br>
                    <input type="submit" name="submitl" value="login">
                </form>
                <br>
                <br>
                <p1>Register</p1>
                <form id="form2" method="post" action="register">
                    Usuario <input type="text" name="name"><br>
                    Password <input type="text" name="pass"><br>
                    Email <input type="text" name="email"><br>
                    <input type="submit" name="submitr" value="register">
                </form>
            </div>
            <div id="ventanamenu" style="display:none;">
                <h1>Menu</h1>
                <form>
                    <button id="buttonhp" >Registro de partidas jugadas</button>
                    <button id="buttont10" >Top 10</button>
                    <button id="buttonjo" >Jugadores online</button>
                    <button id="buttonju">Jugar</button>
                    <button id="buttoncerrar">Cerrar Sesion</button>
                </form>
            </div>
            <div id="ventanaresultado" style="display: none;">

            </div>
        </div>



        <div id="state">
            <div class="container">
                <h1></h1>
                <h2></h2>
                <div class="ads">google ads</div>
                <form>
                    <button id="buttonjumenu">Volver al menu</button>
                    <button id="buttonjurestart">Reiniciar</button>
                </form>
            </div>
        </div>
        <div id="game">
            <div id="gauge"><div></div></div>
            <div id="ship"></div>
            <div id="explode"></div>
            <div id="moon">
                <div id="landing-pad"><div id="ms">-</div></div>
            </div>
        </div>
        <script>
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
                a = s.createElement(o),
                        m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');
            ga('create', 'UA-41665373-8', 'auto');
            ga('send', 'pageview');
        </script>
    </body>
</html>
