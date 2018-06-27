<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm1.aspx.cs" Inherits="WebApplication1.WebForm1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    <h1>Pregled naročil</h1>
        <hr/>
        <h2>Stranka</h2>
        <p>
            <asp:DropDownList ID="DropDownList1" runat="server" AutoPostBack="True" DataSourceID="strankeSource" DataTextField="Customer" DataValueField="CustomerID" Height="27px" Width="185px">
            </asp:DropDownList>
        </p>
        <hr/>
<h2>Naročila stranke</h2>
        <p>
            <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" DataKeyNames="OrderID" DataSourceID="narocila">
                <Columns>
                    <asp:CommandField ShowSelectButton="True" />
                    <asp:BoundField DataField="OrderID" HeaderText="OrderID" InsertVisible="False" ReadOnly="True" SortExpression="OrderID" />
                    <asp:BoundField DataField="Employee" HeaderText="Employee" ReadOnly="True" SortExpression="Employee" />
                    <asp:BoundField DataField="OrderDate" HeaderText="OrderDate" SortExpression="OrderDate" />
                    <asp:BoundField DataField="RequiredDate" HeaderText="RequiredDate" SortExpression="RequiredDate" />
                    <asp:BoundField DataField="ShippedDate" HeaderText="ShippedDate" SortExpression="ShippedDate" />
                    <asp:BoundField DataField="ShipName" HeaderText="ShipName" SortExpression="ShipName" />
                    <asp:BoundField DataField="ShipAddress" HeaderText="ShipAddress" SortExpression="ShipAddress" />
                </Columns>
            </asp:GridView>
        </p>
        <hr/>
        <h2>Podrobnosti naročila</h2>
        <p>
            <asp:GridView ID="GridView2" runat="server" AutoGenerateColumns="False" DataKeyNames="OrderID,ProductID" DataSourceID="orderDetails">
                <Columns>
                    <asp:BoundField DataField="OrderID" HeaderText="OrderID" ReadOnly="True" SortExpression="OrderID" />
                    <asp:BoundField DataField="ProductID" HeaderText="ProductID" ReadOnly="True" SortExpression="ProductID" />
                    <asp:BoundField DataField="UnitPrice" HeaderText="UnitPrice" SortExpression="UnitPrice" />
                    <asp:BoundField DataField="Quantity" HeaderText="Quantity" SortExpression="Quantity" />
                    <asp:BoundField DataField="Discount" HeaderText="Discount" SortExpression="Discount" />
                </Columns>
            </asp:GridView>
        </p>
        <p>
            <asp:SqlDataSource ID="strankeSource" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString %>" SelectCommand="SELECT CustomerID, CompanyName + ' (' + ContactName + ', ' + ContactTitle + ')' AS Customer FROM Customers"></asp:SqlDataSource>
            <asp:SqlDataSource ID="narocila" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString %>" SelectCommand="SELECT Orders.OrderID, Employees.LastName + ' ' + Employees.FirstName + ' (' + CAST(Employees.EmployeeID AS VARCHAR(50)) + ')' AS Employee, Orders.OrderDate, Orders.RequiredDate, Orders.ShippedDate, Orders.ShipName, Orders.ShipAddress FROM Orders INNER JOIN Employees ON Orders.EmployeeID = Employees.EmployeeID WHERE (Orders.CustomerID = @CustomerID)">
                <SelectParameters>
                    <asp:ControlParameter ControlID="DropDownList1" Name="CustomerID" PropertyName="SelectedValue" />
                </SelectParameters>
            </asp:SqlDataSource>
        </p>
        <p>
            <asp:SqlDataSource ID="orderDetails" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString %>" SelectCommand="SELECT * FROM [Order Details] WHERE ([OrderID] = @OrderID)">
                <SelectParameters>
                    <asp:ControlParameter ControlID="GridView1" Name="OrderID" PropertyName="SelectedValue" Type="Int32" />
                </SelectParameters>
            </asp:SqlDataSource>
        </p>
        

    </div>
    </form>
</body>
</html>
