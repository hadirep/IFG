package kafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer

class KafkaProducerHelper {

	static void sendMessage(String topic, String message) {
		Properties props = new Properties()
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName())
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName())

		KafkaProducer<String, String> producer = new KafkaProducer<>(props)
		ProducerRecord<String, String> record = new ProducerRecord<>(topic, message)
		producer.send(record)
		producer.close()
	}
}
