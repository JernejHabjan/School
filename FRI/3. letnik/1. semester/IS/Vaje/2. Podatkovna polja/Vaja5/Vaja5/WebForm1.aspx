<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm1.aspx.cs" Inherits="Vaja5.WebForm1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:TextBox ID="vrednost" runat="server"></asp:TextBox>
        <asp:Button ID="increase" runat="server" OnClick="increase_Click" Text="Povečaj" />
        <asp:Button ID="decrease" runat="server" OnClick="decrease_Click" Text="Zmanjšaj" />
    
    </div>
    </form>
</body>
</html>
