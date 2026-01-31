# 🚗 Araç Galeri Yönetim Sistemi (Backend API)

## 📖 Proje Hakkında

Bu proje, bir araç satış ekosistemini (Galericiler, Müşteriler, Araçlar ve Hesaplar) yönetmek için geliştirilmiş kapsamlı bir **RESTful API** sistemidir.

Proje, **T.C. Merkez Bankası (TCMB)** ile entegre çalışarak anlık döviz kurlarını çeker. Araç alım-satım işlemleri sırasında Dolar/TL dönüşümlerini gerçek zamanlı yaparak hesap bakiyelerini ve araç fiyatlarını dinamik olarak yönetir.

Veri güvenliği **Spring Security** ve **JWT** ile sağlanmış olup, tüm API uçları **Swagger UI** ile dokümante edilmiştir.

## ✨ Temel Özellikler

* **🔐 Güvenlik ve Kimlik Doğrulama:**
    * **JWT (JSON Web Token)** tabanlı Stateless (durumsuz) kimlik doğrulama.
    * `Register`, `Login` ve `Refresh Token` mekanizmaları.
    * Spring Security ile uçtan uca koruma.

* **💱 Dinamik Kur Dönüşümü (TCMB Entegrasyonu):**
    * Merkez Bankası API'si kullanılarak günlük USD kuru otomatik çekilir.
    * Satış işlemlerinde bakiye yetersizliği veya kur farkı hesaplamaları anlık kur üzerinden yapılır.

* **📄 Gelişmiş Listeleme (Pagination):**
    * Büyük veri setlerinin performanslı yönetimi için **Pageable** yapısı kullanıldı.
    * Listeleme servisleri sayfa numarası ve boyutuna göre veri döner.

* **⚡ Veri Tutarlılığı (Transaction):**
    * Araç satın alma (`buy-car`) işlemi atomik olarak (`@Transactional`) yönetilir.
    * Para transferi gerçekleşmeden araç durumu `SOLD` (Satıldı) olarak güncellenmez.

* **📝 API Dokümantasyonu (Swagger):**
    * Tüm Controller ve Model yapıları Swagger UI üzerinde görselleştirildi.

## 🛠️ Teknoloji Yığını

* **Dil:** Java 17
* **Framework:** Spring Boot 3.x
* **Veritabanı:** MSSQL
* **ORM:** Spring Data JPA, Hibernate
* **Güvenlik:** Spring Security, JWT
* **Dokümantasyon:** Springdoc OpenAPI (Swagger UI)
* **Araçlar:** Lombok, Maven, Postman

## 🔌 API Endpoint Listesi

Aşağıda projedeki ana servislerin listesi bulunmaktadır. Tüm endpointler `/rest/api/` öneki ile başlar.

### 🔐 Kimlik Doğrulama (Auth)
| Metot | URL | Açıklama |
| :--- | :--- | :--- |
| `POST` | `/register` | Yeni kullanıcı kaydı oluşturur |
| `POST` | `/authenticate` | Giriş yapar ve **Access Token** döner |
| `POST` | `/refreshToken` | Token süresi dolduğunda yeni token üretir |

### 💰 Satış İşlemleri (Transaction)
| Metot | URL | Açıklama |
| :--- | :--- | :--- |
| `POST` | `/sold-car/buy-car` | **(Ana İşlem)** Araç satın alımını gerçekleştirir, bakiyeyi düşer ve durumu günceller. |

### 🚗 Araç Yönetimi (Car)
| Metot | URL | Açıklama |
| :--- | :--- | :--- |
| `POST` | `/car/save` | Sisteme yeni araç ekler |
| `GET` | `/car/list` | Araçları sayfalı (Pageable) olarak listeler |
| `PUT` | `/car/update/{id}` | Araç bilgilerini günceller |
| `GET` | `/car/get/{id}` | ID'ye göre araç detayını getirir |

### 👤 Müşteri & Galerici (Customer & Gallerist)
| Metot | URL | Açıklama |
| :--- | :--- | :--- |
| `POST` | `/customer/save` | Yeni müşteri ekler |
| `POST` | `/gallerist/save` | Yeni galerici ekler |
| `GET` | `/customer/list` | Müşterileri listeler |
| `GET` | `/gallerist/list` | Galericileri listeler |

### 🏦 Hesap & Adres (Account & Address)
| Metot | URL | Açıklama |
| :--- | :--- | :--- |
| `POST` | `/account/save` | Kullanıcıya hesap/bakiye tanımlar |
| `POST` | `/address/save` | Kullanıcıya adres bilgisi ekler |

### 📈 Döviz Kurları
| Metot | URL | Açıklama |
| :--- | :--- | :--- |
| `GET` | `/currency-rates` | Belirtilen tarih aralığındaki döviz kurlarını getirir |

## 🚀 Kurulum

1.  **Projeyi Klonlayın**
    ```bash
    git clone [https://github.com/semihkurucay/springbootlearn-gallerist.git](https://github.com/semihkurucay/springbootlearn-gallerist.git)
    cd springbootlearn-gallerist
    ```

2.  **Veritabanı Ayarları**
    `application.properties` dosyasını düzenleyin:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/gallerist_db
    spring.datasource.username=root
    spring.datasource.password=sifreniz
    
    # JWT Secret Key
    jwt.secret=buraya_uzun_ve_guvenli_bir_key_yazin
    ```

3.  **Çalıştırın**
    ```bash
    mvn spring-boot:run
    ```

4.  **Swagger'a Erişin**
    `http://localhost:8080/swagger-ui/index.html`

---
