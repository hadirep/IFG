# IFG : Katalon + Kafka + REST API Test Project

## Teknologi yang Digunakan
- [Katalon Studio](https://www.katalon.com/)
- Apache Kafka dan Zookeeper (via Docker)  
- [Python](https://www.python.org/downloads/) (untuk mengirim pesan ke Kafka)  
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
- Untuk menghentikan service, gunakan:
```bash
docker-compose down
```
### 3. **Mengirim Pesan ke Kafka (Producer)**
- Digunakan script Python bernama `send_kafka_message.py`
- Script ini akan mengirimkan pesan JSON ke Kafka topic bernama `dummyjson-topic`
- Jalankan script menggunakan:
```bash
python send_kafka_message.py
```
### 4. **Jalankan Test Case di Katalon Studio**
#### A. Kafka_Consumer_Test
- Membaca pesan dari Kafka topic
- Verifikasi bahwa pesan yang diterima mengandung data tertentu (misalnya: `"Test Product"`)

#### B. Consumer_Producer_Test
- Melakukan pengujian REST API ke endpoint public dummyjson
- Mencakup:
  - GET semua produk dan produk berdasarkan ID
  - POST produk baru (simulasi sebagai producer)
  - PUT untuk update produk

## Struktur Folder Penting
- `Test Cases/Kafka/Kafka_Consumer_Test.tc`  
- `Test Cases/REST API/Consumer_Producer_Test.tc`  
- `Object Repository/PUBLIC API/...`
- `Drivers/kafka-clients-4.0.0.jar`
- `Include/scripts/groovy/kafka/KafkaConsumerHelper.groovy`  
- `send_kafka_message.py`  
- `docker-compose.yml`

## Dependensi Kafka untuk Katalon
- Pastikan kamu menambahkan file kafka-clients-<versi>.jar ke Drivers/ di Katalon. Jika belum ada, bisa diunduh dari [Maven Central Repository - kafka-clients](https://central.sonatype.com/artifact/org.apache.kafka/kafka-clients/versions)

## Hasil yang di harapkan
- Data berhasil dikirim dan dibaca melalui REST API
- Pesan Kafka berhasil diterima oleh Katalon consumer dan tervalidasi dengan benar
