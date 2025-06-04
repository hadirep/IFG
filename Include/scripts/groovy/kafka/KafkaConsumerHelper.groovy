package kafka

import org.apache.kafka.clients.consumer.*
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*

class KafkaConsumerHelper {
	static List<String> consumeMessages(String topicName) {
		Properties props = new Properties()
		props.put("bootstrap.servers", "localhost:9092")
		props.put("group.id", "katalon-consumer-group")
		props.put("key.deserializer", StringDeserializer.class.getName())
		props.put("value.deserializer", StringDeserializer.class.getName())
		props.put("auto.offset.reset", "earliest")

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)
		consumer.subscribe(Collections.singletonList(topicName))

		ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10))
		List<String> messages = []
		for (ConsumerRecord<String, String> record : records) {
			println("Kafka: " + record.value())
			messages.add(record.value())
		}

		consumer.close()
		return messages
	}
}
