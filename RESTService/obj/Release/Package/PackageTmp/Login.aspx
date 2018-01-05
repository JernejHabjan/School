﻿<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="RESTService.Login" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
    
            <asp:Button ID="btnLogin" Text="Login with Google" runat="server" OnClick="btnLogin_Click"  />
        
            <asp:panel ID="pnlProfile" runat="server" Visible="false">
          
            <table>
                    <tr>
                    <td rowspan="5">
                        <asp:Image ID="ProfileImage" runat="server" Width="50" Height="50"/>
                    </td>
                    </tr>
                   
                   
                    <tr>
                        <td>
                        Name:
                        <asp:Label ID="LblName" runat="server" Text=""></asp:Label>
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Email:
                        <asp:Label ID="LblEmail" runat="server" Text=""></asp:Label>
                    </td>
                     </tr>

                    <tr>
                    <td>
                        Gender:
                        <asp:Label ID="LblGender" runat="server" Text=""></asp:Label>
                    </td>
                     </tr>
                <tr>
                    <td>
                        Type:
                        <asp:Label ID="LblType" runat="server" Text=""></asp:Label>
                    </td>
                     </tr>
                <tr>
                    <td>
                      
                 
                        <asp:Button ID="Button1" runat="server" OnClick="Clear_click" Text="Logout" />
                         <asp:Button ID="Button2" runat="server" OnClick="ChangeInfo" Text="Change personal info" style="height: 26px" />
                    </td>
                     </tr>
              

            </table>
                </asp:panel>
        </div>
        
   
        <asp:Label ID="Label1" runat="server" Text="Label"></asp:Label>
        
   
    </form>
</body>

</html>
