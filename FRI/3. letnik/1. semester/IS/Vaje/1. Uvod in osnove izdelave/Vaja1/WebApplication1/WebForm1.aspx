<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm1.aspx.cs" Inherits="WebApplication1.WebForm1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:TextBox ID="val1" runat="server"></asp:TextBox>
&nbsp;&nbsp;<asp:TextBox ID="val2" runat="server"></asp:TextBox>
        &nbsp; = <asp:Label ID="result" runat="server" Text="Rezultat"></asp:Label>
        <br />
        <br />
        <asp:Button ID="plus" runat="server" OnClick="plus_Click" Text="+" />
&nbsp;<asp:Button ID="minus" runat="server" Text="-" OnClick="minus_Click" />
&nbsp;<asp:Button ID="times" runat="server" Text="*" OnClick="times_Click" />
&nbsp;<asp:Button ID="divide" runat="server" Text="/" OnClick="divide_Click" />
    
    </div>
        <asp:Button ID="clear" runat="server" OnClick="clear_Click" Text="Ponastavi" />
    </form>
</body>
</html>