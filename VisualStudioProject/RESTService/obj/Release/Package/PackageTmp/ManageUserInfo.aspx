<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ManageUserInfo.aspx.cs" Inherits="RESTService.ManageUserInfo" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Change user info</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="~/style.css" runat="server" />
        <meta charset="utf-8"/>
</head>
<body>

    <!-- ######################################################   Navigation bar ########################################################## -->
    <nav class="navbar navbar-inverse" >
      <div class="container-fluid">
        <div class="navbar-header" >
          <a class="navbar-brand active"  href="http://asistentslivko.azurewebsites.net/MainPage.aspx">Asistent Slivko</a>
        </div>
        <ul class="nav navbar-nav">
     
          <li><a href="http://asistentslivko.azurewebsites.net/Flight.aspx" >Plan</a></li>
         
        </ul>
        <ul class="nav navbar-nav navbar-right">
 
          <li><a href="http://asistentslivko.azurewebsites.net/Login.aspx" > <span class="glyphicon glyphicon-log-in"> <span  id="loginNav" runat="server" >Login</span></span></a></li>
        </ul>
      </div>
    </nav>



    <!-- #######################################################   Change info form  ################################################## -->
    <form id="form1" runat="server">
   

        <table >
            <tr >
                <th style="text-align:center"><asp:Label runat="server" Text="Novo prikazno ime: "></asp:Label></th>
            </tr>
            <tr style="text-align:center">
                <td><input id="name_input" class="textInput" type="text" runat="server"/></td>
            </tr>
        </table>
        <p style="text-align:center">
            <asp:Button ID="b_accept" class="button1" runat="server" OnClick="b_accept_Click" Text="Potrdi spremembe" />
            <asp:Button ID="b_cancel" class="button1" runat="server" OnClick="b_cancel_Click" Text="Prekini" />
        </p>
    </form>
    
    
</body>
</html>
