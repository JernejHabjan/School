<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm1.aspx.cs" Inherits="Vaja7.WebForm1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <h2>Vnos podatkov o osebi:</h2>
        Ime in priimek:<br />
        <asp:TextBox ID="TextBox3" runat="server" Width="269px"></asp:TextBox>
        <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="TextBox3" ErrorMessage="Obvezno polje"></asp:RequiredFieldValidator>
        <asp:RegularExpressionValidator ID="RegularExpressionValidator1" runat="server" ControlToValidate="TextBox3" ErrorMessage="Neveljaven vnos" ValidationExpression="^[A-Za-z]+ [A-Za-z]+$"></asp:RegularExpressionValidator>
        <br/>
        Naslov elektronske pošte:<br />
        <asp:TextBox ID="TextBox2" runat="server" Width="271px"></asp:TextBox>
        <asp:RegularExpressionValidator ID="RegularExpressionValidator2" runat="server" ControlToValidate="TextBox2" ErrorMessage="Neveljaven naslov elektronske pošte" ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*"></asp:RegularExpressionValidator>
        <br/>
        Opombe:<br/>
        <asp:TextBox ID="TextBox1" runat="server" Height="149px" Width="273px"></asp:TextBox>
        <br />
        <br />
        <asp:Button ID="Button1" runat="server" Text="Potrdi" />
        <asp:Button ID="Button2" runat="server" Text="Prekliči" />
        <br />
    </div>
    </form>
</body>
</html>
