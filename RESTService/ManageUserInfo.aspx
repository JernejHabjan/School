<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ManageUserInfo.aspx.cs" Inherits="RESTService.ManageUserInfo" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>




            <asp:Label ID="Label1" runat="server" Text="Novo prikazno ime: "></asp:Label>
            <input id="name_input" type="text" runat="server"/></div>
        <asp:Label ID="Label2" runat="server" Text="Nov email: "></asp:Label>
        <input id="email_input" type="text" runat="server"/><p>
            <asp:Button ID="b_accept" runat="server" OnClick="b_accept_Click" Text="Potrdi spremembe" />
            <asp:Button ID="b_cancel" runat="server" OnClick="b_cancel_Click" Text="Prekini" />
        </p>
    </form>
    <p>
    
</body>
</html>
