const { Builder, By, until } = require('selenium-webdriver');
const fs = require('fs');
const path = require('path');


(async function testLogin() {
    let driver = await new Builder().forBrowser('chrome').build();
  
    try {
      await driver.get('https://www.intervue.io');
  
      // Wait for and click the login button (top right corner)
      await driver.wait(until.elementLocated(By.css('a[href="/login"]')), 10000);
      await driver.findElement(By.css('a[href="/login"]')).click();
  
      // Wait for login form to appear
      await driver.wait(until.elementLocated(By.name('email')), 10000);

    // Enter credentials (use dummy credentials for test)
    await driver.findElement(By.name('email')).sendKeys('ushavandu665@gmail.com');
    await driver.findElement(By.name('password')).sendKeys('Usha@2001');

    await driver.findElement(By.css('button[type="submit"]')).click();

    // Wait and check if login failed
    await driver.sleep(3000); // simple delay for visual update

    const currentUrl = await driver.getCurrentUrl();
    if (currentUrl.includes('login')) {
      const screenshot = await driver.takeScreenshot();
      const filePath = path.join(__dirname, '../screenshots/login_failure.png');
      fs.writeFileSync(filePath, screenshot, 'base64');
      console.log('Login failed. Screenshot saved at:', filePath);
    } else {
      console.log('Login may have succeeded or redirected to another page.');
    }

  } catch (err) {
    console.error('Test failed:', err);
  } finally {
    await driver.quit();
  }
})();
