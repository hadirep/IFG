# IFG : Katalon + Kafka + REST API Test Project

## Teknologi yang Digunakan
- [Katalon Studio v9.5.0](https://www.katalon.com/)
- [Docker](https://www.docker.com/products/docker-desktop/)
- Apache Kafka dan Zookeeper (via Docker)  
- Public REST API: [dummyjson.com](https://dummyjson.com/)

## Cara Menjalankan
### 1. **Clone repository ini**
```bash
git clone https://github.com/hadirep/ifg.git
```
### 2. **Jalankan Kafka via Docker**
- Pastikan Docker telah terinstal di sistem
- Buat file `docker-compose.yml` (lihat isi file pada repo ini)
- Jalankan Kafka dan Zookeeper dengan perintah:
```bash
docker-compose up -d
```
- Cek Apakah Kafka berjalan:
```bash
docker ps
```
- Untuk menghentikan service, gunakan:
```bash
docker-compose down
```
### 3. **Jalankan Test Case di Katalon Studio**
#### A. REST_API-Producer
- Mengirim data ke API dan ke Kafka
- Mencakup:
  - POST (https://dummyjson.com/products/add) produk baru (simulasi sebagai producer)
  - PUT (https://dummyjson.com/products/1) untuk update produk
#### B. REST_API-Consumer
- Menerima pesan dari REST API Public dari [dummyJson](https://dummyjson.com/)
- Mencakup:
  - GET All Product (https://dummyjson.com/products)
  - GET Data By ID (https://dummyjson.com/products/1)
#### C. Kafka-Consumer
- Membaca pesan dari Kafka topic
- Verifikasi bahwa pesan yang diterima mengandung data tertentu (misalnya: `"Product Hadi"`)
  
### 4. **Pastikan kafka topic menerima pesan dari Katalon**
- Masuk ke Container Kafka
```bash
winpty docker exec -it kafka bash
```
- Melihat Topik yang telah di buat dari Katalon (hadi-topic)
```bash
kafka-topics --describe --topic hadi-topic --bootstrap-server localhost:9092
```
- Membaca Pesan hadi-topic (Consumer)
```bash
kafka-console-consumer --topic hadi-topic --from-beginning --bootstrap-server localhost:9092
```
- Keluar dari Pesan hadi-topic
```bash
CTRL+C
```
- Keluar dari Container
```bash
exit
```

## Struktur Folder Penting
- `Test Cases/Kafka/Kafka-Consumer.tc`  
- `Test Cases/REST API/REST_API-Producer.tc`
- `Test Cases/REST API/REST_API-Consumer.tc`
- `Object Repository/PUBLIC API/...`
- `Drivers/kafka-clients-4.0.0.jar`
- `Include/scripts/groovy/kafka/KafkaConsumerHelper.groovy`
- `Include/scripts/groovy/kafka/KafkaProducerHelper.groovy`  
- `docker-compose.yml`

## Dependensi Kafka untuk Katalon
- Pastikan kamu menambahkan file kafka-clients-<versi>.jar ke Drivers/ di Katalon. Jika belum ada, bisa diunduh dari [Maven Central Repository - kafka-clients](https://central.sonatype.com/artifact/org.apache.kafka/kafka-clients/versions)

## Hasil yang di harapkan
- Data berhasil dikirim dan dibaca melalui REST API
- Pesan Kafka berhasil diterima oleh Katalon consumer dan tervalidasi dengan benar

## Author
- Nama: Hadi Rahmah Esa Putra
- Email: Hadiesarahma@gmail.com
