<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="RESTService.Login" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Button ID="btnLogin" Text="Google Login" runat="server" OnClick="btnLogin_Click" />

            <asp:panel ID="pnlProfile" runat="server" Visible="false">
                <hr />
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
                      
                        <asp:Label Text="Clear: " runat="server"></asp:Label>
                        <asp:Button ID="Button1" runat="server" OnClick="Clear_click" Text="Logout" />
                    </td>
                     </tr>
              

            </table>
                </asp:panel>
        </div>
        
   
    </form>
</body>

</html>
