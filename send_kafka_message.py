from kafka import KafkaProducer
import json

producer = KafkaProducer(
    bootstrap_servers='localhost:9092',
    value_serializer=lambda v: json.dumps(v).encode('utf-8')
)

message = {"id": 1, "title": "Test Product", "price": 100}
producer.send('test-topic', value=message)
producer.flush()

print("Sent message to test-topic:", message)
print("Pesan berhasil di kirim ke Kafka")