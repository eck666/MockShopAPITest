# ECommerceMockServer

ECommerceMockServer, bir e-ticaret API'sini simüle etmek için kullanılan bir MockServer uygulamasıdır. Bu proje, ürün listeleme, ürün detayı, sepete ekleme ve sipariş oluşturma gibi temel e-ticaret işlemlerini simüle eder.

## Kurulum ve Kullanım

1. Projeyi klonlayın:
    ```bash
    git clone https://github.com/yourusername/ECommerceMockServer.git
    ```

2. Proje dizinine gidin:
    ```bash
    cd ECommerceMockServer
    ```

3. Maven bağımlılıklarını indirin:
    ```bash
    mvn clean install
    ```

4. MockServer'ı başlatın:
    ```bash
    mvn exec:java -Dexec.mainClass="com.ecommerce.mockserver.MockServerStarter"
    ```

MockServer, `http://localhost:1080` adresinde çalışacaktır. API endpoint'lerine istek yaparak yapılandırma dosyasındaki yanıtları alabilirsiniz.

## Yapılandırma

API endpoint'lerini ve yanıtları yapılandırmak için `mockserver-configuration.json` dosyasını düzenleyin.
