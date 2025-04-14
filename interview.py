from selenium import webdriver
from selenium.webdriver.common.by import By
import time
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

driver = webdriver.Chrome()
driver.maximize_window()
driver.get("https://www.intervue.io/")
time.sleep(3)
#driver.find_element(By.LINK_TEXT, "Login").click()
#original_window = driver.current_window_handle
#original_window = driver.current_window_handle
original_window = driver.current_window_handle

# Click the login button (which opens new window/tab)
driver.find_element(By.LINK_TEXT, "Login").click()

# Wait for new window to open
time.sleep(2)  # use WebDriverWait in real projects

# Loop through all windows to find the new one
for window_handle in driver.window_handles:
    if window_handle != original_window:
        driver.switch_to.window(window_handle)
        break


driver.find_element(By.XPATH, "(//div[@class='AccessAccount-ColoredButton-Text'][normalize-space()='Login'])[1]").click()
driver.find_element(By.XPATH, '//input[@name="email"]').send_keys("neha@intervue.io")
driver.find_element(By.XPATH, '//input[@name="password"]').send_keys("Ps@neha@123")
time.sleep(3)
driver.find_element(By.XPATH, '//button[@type="submit"]').click()
time.sleep(3)
driver.find_element(By.CSS_SELECTOR, '.search_placeholder').click()
time.sleep(3)
for window_handle in driver.window_handles:
    if window_handle != original_window:
        driver.switch_to.window(window_handle)
        break
"""driver.find_element(By.XPATH, '//input[@placeholder="Type what you want to search for"]').send_keys("hello")
time.sleep(2)
driver.find_element(By.CSS_SELECTOR, '.SearchThrough__PlaceholderText-sc-8f4vh4-0.fEvpzS').click()
time.sleep(3)"""
search_field = WebDriverWait(driver, 10).until(
    EC.visibility_of_element_located((By.XPATH, '//input[@class="SearchBox__StyledInput-ctnsh0-4 lhwsuL"]'))
)
search_field.send_keys("hello")
time.sleep(2)

# Click on the first search result (if one appears)
driver.find_element(By.CSS_SELECTOR, '.SearchThrough__PlaceholderText-sc-8f4vh4-0.fEvpzS').click()
time.sleep(3)
driver.find_element(By.XPATH, '//div[@class="ProfileHeader__UsernameWrap-sc-1gwp6c1-2 jRhmUi"]').click()
driver.find_element(By.XPATH, '//*[@id="app"]/div/div[1]/div/div/div[1]/div/div[2]/div[2]/div[1]/div/div[2]').click()
time.sleep(3)
driver.find_element(By.XPATH, '//a[contains(@href, "logout")]').click()
driver.quit()