$(document).ready(function() 
{
    $('#btnRegister').click(function(evt)
    {
        var allInputs = true;
        
        // check username
        if ($("#txtUsername").val().length == 0)
        {
          allInputs = false;
          $("#usernameValidate").show();
          $("#usernameExists").hide();
        }
        else
          $("#usernameValidate").hide();

        // check email
        if ($("#txtEmail").val().length == 0)
        {
          allInputs = false;
          $("#emailValidate").show();
        }
        else
          $("#emailValidate").hide();
        
        // check password
        if ($("#txtPassword").val().length == 0)
        {
          allInputs = false;
          $("#passwordValidate").show();
        }
        else
          $("#passwordValidate").hide();

        // cancel postback if inputs empty
        if (!allInputs)
          evt.preventDefault();
    });
});