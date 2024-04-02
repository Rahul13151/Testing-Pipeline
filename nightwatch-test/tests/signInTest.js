const { signInData } = require("../test-data/data");
const signInPage = browser.page.signIn();


describe('Login FE testing', ()=> {
    const data = browser.globals;
    before((browser)=>{
        console.log("Starting the SignIn Test Suite....");
        browser
        .window.maximize()
        .window.setSize(1920,1000);
        signInPage.navigate();
        console.log("Running Sign in Test");
    });

    it('LB-1132 : Verify "Go1Percent" logo, carousel images, carousel caption and the footer message (TC-337) ',function(browser){
        signInPage
        .assert.visible("@goLogo","Checking for Go Logo to be present")
        .assert.visible('@onePercentLogo',"Checking for One Percent Logo to be present")
        .assert.visible('@crouselImage',"Checking the crousel image")
        .assert.visible('@crouselCaption',"Checking the crousel Caption")
        .assert.visible('@footer',"Checking Footer message Visible");
    });
    it('LB-1133 : Verify that tag line with text "Get 1% Better Everyday" is displayed (TC-338)',function(browser){
        signInPage
        .assert.textEquals('@getOnePercentTagLine','Get 1% Better Everyday')
        .assert.visible('@getOnePercentTagLine');
    })
    it('LB-1134 : Verify that carousel image changes while clicking on carousel button (TC-339)',function(browser){
        // signInPage.assert.to.be.visible
        
    });
    it('LB-1136 : Verify that specific text between login options is present on the web page (TC-341)',function(browser){
        signInPage
        .assert.visible("@textBetweenLogInOption")
        .assert.textEquals("@textBetweenLogInOption","or do it via E-mail")
        // Validating text is present between both the logIn Options
        .getLocation('#kc-social-providers ul', function(result1) {
            browser.getLocation('#kc-social-providers h4', function(result2) {
              browser.getLocation('#kc-form-login', function(result3) {
                this.assert.ok(result1.value.y < result2.value.y && result2.value.y < result3.value.y, 'Element 2 is between Element 1 and Element 3');
              });
            });
          })    
        
    });
    it(' LB-1135 : Verify that login page heading contains text "Sign in to Go 1%" is displayed (TC-340)',function(browser){
        signInPage
        .assert.textContains("@pageHeading","Sign in to Go 1%")
        .assert.visible("@pageHeading");
    });
    it('LB-1137 : Verify that clicking on the Microsoft logo redirects to the Microsoft login page (TC-342)',function(browser){
        signInPage
        .clickMicrosoftButton()
        browser
        .assert.urlContains("https://login.microsoftonline.com/")
        .back();
    });
    it('LB-1138 : Verify that login fails with invalid credentials and an alert message is displayed (TC-343)',function(browser){
        signInPage
        .navigate()
        .setEmailValue("@userNameInputBox",data.signInData.email)
        .setPasswordValue(data.signInData.invalidPassword)
        .clickSignInButton();
        signInPage
        .assert.visible("@errorMessage")
        .assert.textContains("@errorMessage","Invalid username or password.");
    });
});
