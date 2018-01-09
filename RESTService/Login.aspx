﻿<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="RESTService.Login" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Login</title>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="~/style.css" runat="server" />


</head>
<body>

    <!-- ######################################################## Navigation bar ########################################################## -->
    
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

    <!-- ####################################################  Login form  #######################################################  -->
    <form id="form1" runat="server">
        <div>
            <asp:Button ID="btnLogin" class="button1" Text="Login with Google" runat="server" OnClick="btnLogin_Click"  />
            <asp:panel ID="pnlProfile" runat="server" Visible="false">
                <table>
                    <tr>
                        <td rowspan="5"><asp:Image ID="ProfileImage" style="padding:30px;" runat="server" Width="100" Height="100"/></td>
                    </tr>
                    <tr>
                        <td>Name:<asp:Label ID="LblName" runat="server" Text=""></asp:Label></td>
                    </tr>
                    <tr>
                        <td>Email:<asp:Label ID="LblEmail" runat="server" Text=""></asp:Label></td>
                    </tr>
                    <tr>
                        <td><asp:Button class="button1" ID="Button2" runat="server" OnClick="ChangeInfo" Text="Change personal info" style="height: 26px" /></td>
                    </tr>
                    <tr>
                        <td><asp:Button class="button1" ID="Button1" runat="server" OnClick="Clear_click" Text="Logout" /></td>
                    </tr>
                </table>
           </asp:panel>
        </div>
    </form>
</body>

</html>
