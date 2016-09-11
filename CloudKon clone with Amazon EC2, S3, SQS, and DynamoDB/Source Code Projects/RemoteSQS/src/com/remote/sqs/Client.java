package com.remote.sqs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfilesConfigFile;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.util.Tables;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;

public class Client {
	static BufferedReader bufferedReader;
	static AmazonSQS sqs;
	static String queueUrl;
	static String outputQueueUrl;
	static List<Message> message;
	static long stop, start = -1, totalTimeDifference;
	static double diff;
	static AmazonDynamoDBClient dynamoDB;
	static CreateTableRequest createTableRequest;
	static DescribeTableRequest describeTableRequest;

	//client that creates a SQS and DynamoDB, and reads data fromworkload file and put that into SQS
	public static void main(String[] args) {

		AWSCredentials credentials = null;
		try {
			ProfilesConfigFile credentialsConfig = new ProfilesConfigFile("credentials");//credentials file required to access AWS
			credentials = new ProfileCredentialsProvider(credentialsConfig, "default").getCredentials();
		} catch (Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles file. " + e);
		}
		// SQS initialization
		sqs = new AmazonSQSClient(credentials);
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		sqs.setRegion(usWest2);
		
		//DynamoDB initialization
		dynamoDB = new AmazonDynamoDBClient(credentials);
		dynamoDB.setRegion(usWest2);
		String tName = "sqs";
		//checking table is active or not, if not creates the table with id column
		if (Tables.doesTableExist(dynamoDB, tName)) {
			System.out.println("Table " + tName + " is already ACTIVE");
		} else {
			createTableRequest = new CreateTableRequest().withTableName(tName)
					.withKeySchema(new KeySchemaElement().withAttributeName("mid").withKeyType(KeyType.HASH))
					.withAttributeDefinitions(
							new AttributeDefinition().withAttributeName("mid").withAttributeType(ScalarAttributeType.S))
					.withProvisionedThroughput(
							new ProvisionedThroughput().withReadCapacityUnits(3000L).withWriteCapacityUnits(3000L));
			TableDescription createdTableDescription = dynamoDB.createTable(createTableRequest).getTableDescription();
		}
		try {
			Tables.awaitTableToBecomeActive(dynamoDB, tName);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//creating the queue for input tasks and reading responses from workers
		CreateQueueRequest createQueueRequest = new CreateQueueRequest(args[2]);
		queueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();
		CreateQueueRequest outputQueueRequest = new CreateQueueRequest("outputremoteSQS");
		outputQueueUrl = sqs.createQueue(outputQueueRequest).getQueueUrl();

		String workload_file = args[4];
		try {
			bufferedReader = new BufferedReader(new FileReader(new File(workload_file)));
			String task;
			long index = 1;
			List<SendMessageBatchRequestEntry> batchRequestEntries = new ArrayList<SendMessageBatchRequestEntry>();
			SendMessageBatchRequestEntry batchRequestEntry;
			int count = 0;
			while ((task = bufferedReader.readLine()) != null) {//reads workload file and put the tasks in SQS
				index++;
				enqueue(task);
			}

			
			//Reads the responses from workers
			ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(outputQueueUrl);
			// System.out.println(index);
			long outputIndex = 1;
			while (true) {
				receiveMessageRequest.setVisibilityTimeout(43200);
				receiveMessageRequest.withMaxNumberOfMessages(1);
				try {
					synchronized (sqs) {
						message = sqs.receiveMessage(receiveMessageRequest).getMessages();
					}
					if (message.size() > 0) {
						String result = message.get(0).getBody();
						outputIndex++;
						// sqs.deleteMessage(outputQueueUrl,
						// message.get(0).getReceiptHandle());
					} else {
						receiveMessageRequest.setWaitTimeSeconds(1);
					}
				} catch (QueueDoesNotExistException exception) {
					System.out.println("queue doesn't exist");
					System.exit(0);
				}
				// System.out.println("-------------" + outputIndex);
				if (outputIndex == index) {
					stop = System.nanoTime();
					totalTimeDifference = (stop - start);
					System.out.println("----Time in nanoseconds----" + totalTimeDifference);
					System.out.println("Deleting the test queue.\n");
					sqs.deleteQueue(new DeleteQueueRequest(queueUrl));
					sqs.deleteQueue(new DeleteQueueRequest(outputQueueUrl));
					System.exit(0);
				}
			}

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	
	//putting task on quque
	public static void enqueue(String msg) {
		if (start == -1) {
			start = System.nanoTime();
		}

		sqs.sendMessage(queueUrl, msg);

	}
}
