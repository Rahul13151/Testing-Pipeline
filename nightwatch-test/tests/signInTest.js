const signIn_Tests = {

    before:(browser)=>{
        console.log("Starting the SignIn Test Suite....");
    },

    //Opening and Maximizing browser window before Each Test
    beforeEach:(browser)=>{
        
        console.log("Running Sign in Test")
        browser.window.maximize()           
    },

    "Try Login with Valid Credentials":(browser)=>{
        const signInpage = browser.page.signIn();
        const data = browser.globals;
        signInpage
        .navigate()
        .setEmailValue("@userNameInputBox",data.signInData.email)
        .setPasswordValue(data.signInData.password)
        .clickSignInButton()
        .assert.titleEquals("NashTech QA |Go1%","Test","Testing Login with Valid Email and Invalid Password")
        .saveScreenshot("tests_output/ValidSignIn.png");
    },

    "Try Login with Valid Email and Invalid Password ":(browser)=>{
        const signInpage = browser.page.signIn();
        const data = browser.globals;
        signInpage
        .navigate()
        .setEmailValue("@userNameInputBox",data.signInData.email)
        .setPasswordValue(data.signInData.invalidPassword)
        .clickSignInButton() 
        .assert.textEquals('@errorMessage','Invalid username or password.',"Testing Login with Valid Email and Invalid Password ")
        .saveScreenshot("tests_output/InvalidSignIn.png");
    },
    
    //Terminating the browser session after each test,to remove session cookies
    afterEach :(browser)=>{
        browser.end();
    },
    
    after :(browser)=>{
        console.log("Completed Executing the signIn Test Suite.")
    }

};

module.exports = signIn_Tests;