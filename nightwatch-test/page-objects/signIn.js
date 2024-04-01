const commands = [{
    setEmailValue(selector,email){
        return this.
            setValue(selector,email);
    },
    setPasswordValue(password){
        return this.
        setValue('@passwordInputBox',password);
    },
    clickSignInButton(){
        return this
        .click('@signInButton');
    }
}];
const elements={
    userNameInputBox:"#username",
    passwordInputBox:"#password",
    signInButton:"#kc-login",
    errorMessage:"#input-error"
};
const url = "https://nashtechglobal.qa.go1percent.com/";

const signIn={
    url:url,
    elements:elements,
    commands:commands
};
module.exports= signIn;