<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm2.aspx.cs" Inherits="WebApplication1.WebForm2" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:GridView ID="GridView1" runat="server" AllowPaging="True" AllowSorting="True" AutoGenerateColumns="False" DataKeyNames="ProductID" DataSourceID="SqlDataSource1">
            <Columns>
                <asp:BoundField DataField="ProductID" HeaderText="ID" InsertVisible="False" ReadOnly="True" SortExpression="ProductID" />
                <asp:BoundField DataField="ProductName" HeaderText="Naziv Izdelka" SortExpression="ProductName" />
                <asp:BoundField DataField="CategoryName" HeaderText="Naziv Kategorije" SortExpression="CategoryName" />
                <asp:BoundField DataField="ContactTitle" HeaderText="Naziv dobavitelja" SortExpression="ContactTitle" />
                <asp:BoundField DataField="UnitsInStock" HeaderText="Zaloga" SortExpression="UnitsInStock" />
            </Columns>
        </asp:GridView>
    
    </div>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString %>" SelectCommand="SELECT Products.ProductID, Products.ProductName, Categories.CategoryName, Suppliers.ContactTitle, Products.UnitsInStock FROM Products INNER JOIN Categories ON Products.CategoryID = Categories.CategoryID INNER JOIN Suppliers ON Products.SupplierID = Suppliers.SupplierID AND Products.ProductName LIKE '%' + @productName + '%'">
            <SelectParameters>
                <asp:QueryStringParameter DefaultValue="%" Name="productName" QueryStringField="productName" />
            </SelectParameters>
        </asp:SqlDataSource>
    </form>
</body>
</html>
