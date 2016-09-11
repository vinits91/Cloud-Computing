package com.remote.sqs;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfilesConfigFile;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;


//responsible to start the workers threads
public class Workers {
	static AmazonSQS sqs;
	static String queueUrl;
	static String outputQueueUrl;
	static AmazonDynamoDBClient dynamoDB;
	static DescribeTableRequest describeTableRequest;

	public static void main(String[] args) {
		int threadsPerWorker = Integer.parseInt(args[4]);
		AWSCredentials credentials = null;
		try {
			ProfilesConfigFile credentialsConfig = new ProfilesConfigFile("credentials");
			credentials = new ProfileCredentialsProvider(credentialsConfig,"default").getCredentials();
		} catch (Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles file. " + e);
		}
		sqs = new AmazonSQSClient(credentials);
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		sqs.setRegion(usWest2);
		dynamoDB = new AmazonDynamoDBClient(credentials);
		dynamoDB.setRegion(usWest2);
		String tName = "sqs";
		boolean flag = false;

		//getting the existing Queue and DynamoDB
		while (queueUrl == null || outputQueueUrl == null || describeTableRequest == null) {
			try {
				describeTableRequest = new DescribeTableRequest().withTableName(tName);
				// TableDescription tableDescription =
				// dynamoDB.describeTable(describeTableRequest).getTable();
				// System.out.println("Table Description: " + tableDescription);
				queueUrl = sqs.getQueueUrl(args[2]).getQueueUrl();
				outputQueueUrl = sqs.getQueueUrl("outputremoteSQS").getQueueUrl();

			} catch (Exception exception) {
				if (!flag) {
					System.out.println("Waiting for SQS and Table to get active......");
					flag = true;
				}
				// System.out.println("Waiting for SQS and Table to get
				// active......");
			}
		}
		System.out.println("its Now Available");
		List<Thread> workerThreadList = new ArrayList<Thread>(threadsPerWorker);
		for (int i = 0; i < threadsPerWorker; i++) {
			workerThreadList.add(new Thread(new WorkerThread(sqs, queueUrl, outputQueueUrl, dynamoDB)));
			workerThreadList.get(i).start();
		}

	}

}
