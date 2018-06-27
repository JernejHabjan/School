<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Vaja17.aspx.cs" Inherits="RESTService.Vaja17" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
        </div>
        <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" DataKeyNames="ID" DataSourceID="SqlDataSource2">
            <Columns>
                <asp:BoundField DataField="ID" HeaderText="ID" ReadOnly="True" SortExpression="ID" />
            </Columns>
        </asp:GridView>
        <asp:SqlDataSource ID="SqlDataSource2" runat="server" ConnectionString="<%$ ConnectionStrings:SlivkoConnectionString %>" SelectCommand="SELECT * FROM [blabla]"></asp:SqlDataSource>
    </form>
</body>
</html>
