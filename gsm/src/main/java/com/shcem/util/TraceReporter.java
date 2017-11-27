package com.shcem.util;

import com.shcem.common.PropertiesLoader;
import com.shcem.common.PropertyUtil;
import com.shcem.constants.SystemDefine;
import org.apache.kafka.clients.producer.ProducerConfig;
import zipkin.Span;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.kafka08.KafkaSender;

public class TraceReporter {

	private AsyncReporter<Span> report = null;
	private KafkaSender sender = null;

	public TraceReporter() {

	}

	public AsyncReporter<Span> getReport(String mode) {
		if (report == null) {
			PropertiesLoader propertiesLoader=PropertyUtil.create(SystemDefine.KAFKA_ZIPKIN_PROPERTY);

			String ziplinTopic = propertiesLoader.getProperty("topic_name");
			String bootStrapServers = propertiesLoader.getProperty(mode + "_" + ProducerConfig.BOOTSTRAP_SERVERS_CONFIG);

			sender = KafkaSender.builder().topic(ziplinTopic).bootstrapServers(bootStrapServers).build();
			report = AsyncReporter.builder(sender).build();
		}
		return report;
	}
}
