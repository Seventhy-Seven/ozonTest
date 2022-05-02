package ru.ozon;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OzonTest {

//    @Before
//    public void init(){
//        ChromeDriver driver = new ChromeDriver();
//    }

    ChromeDriver driver;


    private String searchButton = "#stickyHeader > div.ch0 > div > form > button > svg";
    private String addButton = "#layoutPage > div.gt > div.container.tg0 > div:nth-child(2) > div:nth-child(2) > div.ri3 > div:nth-child(1) > div > div > div:nth-child(1) > div.p4i > div.io7.i8o > div > div > div > button > span > span";
    private String resource = "https://www.ozon.ru/";
    private String deleteButton = "#layoutPage > div.gt > div > div > div.container.tg0 > div:nth-child(4) > div.tg6.gt4 > div:nth-child(1) > div > div.p3a > div:nth-child(2) > div.a9n > div > div > div > div.ao7.o7a > div > div.a8o > div > div:nth-child(2) > button > span > span";
    private String yesButton = "body > div.vue-portal-target > div > div.ui-i3 > div > div > section > div.am7.undefined > div > button > span > span";
    private String deleteButton2 = "#layoutPage > div.gt > div > div > div.container.tg0 > div:nth-child(4) > div.tg6.gt4 > div:nth-child(1) > div > div.p3a > div.an9 > div.a9n > div > div > div > div.ao7.o7a > div > div.a8o > div > div:nth-child(2) > button > span > span";

    private String closeButton = "#layoutPage > div.gt > div > div > div:nth-child(3) > div > div > div > div.ui-i3 > div > div > div > div > div.c1c > div > button > span > svg > path";
    @Test
    public void addProducts(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\79122\\Downloads\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(resource);
        driver.manage().window().setSize(new Dimension(1400, 800));  //устанавливаем размер экрана побольше

        driver.findElement(By.name("text")).sendKeys("флешка");
        driver.findElement(By.cssSelector(searchButton)).click(); //ищем
        driver.findElement(By.cssSelector(addButton)).click();    //добавляем

        driver.get(resource);
        driver.findElement(By.name("text")).sendKeys("принтер");
        driver.findElement(By.cssSelector(searchButton)).click();  //ищем
        driver.findElement(By.cssSelector(addButton)).click();     //добавляем

        driver.get("https://www.ozon.ru/cart");   //открываем корзину
        driver.findElement(By.cssSelector(closeButton)).click();  //закрываем всплывающий баннер
        driver.get("https://www.ozon.ru/cart");

        try{
            assertThat(driver.findElement(By.cssSelector(".g3s:nth-child(4) > .tsCaptionBold")).getText(), is("2"));  //выполняем проверку, на то, что в корзине 2 товара
        }catch (Exception e){
            e.printStackTrace();  //если селектор на странице не найдётся, выбрасываем исключение и идёи дальше
            System.out.println("selector not found");
        }finally {
            driver.findElement(By.cssSelector(deleteButton)).click();
            driver.findElement(By.cssSelector(yesButton)).click();

            driver.findElement(By.cssSelector(deleteButton2)).click();
            driver.findElement(By.cssSelector(yesButton)).click();
            driver.get("https://www.ozon.ru/cart");
        }

        try{
            assertThat(driver.findElement(By.cssSelector(".g3s:nth-child(4) > .tsCaptionBold")).getText(), is("0")); //выполняем проверку, на то, что в корзине нет товаров
        }catch (Exception e){
            e.printStackTrace();  //если селектор на странице не найдётся, выбрасываем исключение и идёи дальше
            System.out.println("selector not found");
        }finally {
            driver.quit();
        }
    }

//    @After
//    public void close(){
//        driver.quit();
//    }

}
