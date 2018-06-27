<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Vaja13.aspx.cs" Inherits="WcfService1.Vaja13" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
        </div>
        <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" DataSourceID="ObjectDataSource1">
            <Columns>
                <asp:BoundField DataField="ProduktId" HeaderText="ProduktId" SortExpression="ProduktId" />
                <asp:BoundField DataField="ProduktIme" HeaderText="ProduktIme" SortExpression="ProduktIme" />
                <asp:BoundField DataField="ProduktKategorija" HeaderText="ProduktKategorija" SortExpression="ProduktKategorija" />
            </Columns>
        </asp:GridView>
        <asp:ObjectDataSource ID="ObjectDataSource1" runat="server" SelectMethod="vrniProdukte" TypeName="WcfService1.Service1"></asp:ObjectDataSource>
    </form>
</body>
</html>
