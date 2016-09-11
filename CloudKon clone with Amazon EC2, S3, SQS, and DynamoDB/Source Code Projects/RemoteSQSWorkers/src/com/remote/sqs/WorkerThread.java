package com.remote.sqs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

//worker threads
public class WorkerThread implements Runnable {
	AmazonSQS sqs;
	static String queueUrl;
	List<Message> message;
	static String outputQueueUrl;
	AmazonDynamoDBClient dynamoDB;
	int msgCount;
	static int count=1;

	WorkerThread(AmazonSQS sqs, String queueUrl, String outputQueueUrl, AmazonDynamoDBClient dynamoDB) {
		this.sqs = sqs;
		WorkerThread.queueUrl = queueUrl;
		WorkerThread.outputQueueUrl = outputQueueUrl;
		this.dynamoDB = dynamoDB;
	}

	@Override
	public void run() {
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
		while (true) {
			receiveMessageRequest.setVisibilityTimeout(43200);
			receiveMessageRequest.withMaxNumberOfMessages(1);
			try {
				synchronized (sqs) {//reads the message from quque
					message = sqs.receiveMessage(receiveMessageRequest).getMessages();
				}

				if (message.size() > 0) {
					//check if message id is already there in DynamoDB 
					String[] strArray = message.get(0).getBody().split(" ");
						HashMap<String, Condition> scanFilter = new HashMap<String, Condition>();
						Condition condition = new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
								.withAttributeValueList(new AttributeValue().withS(message.get(0).getMessageId()));
						scanFilter.put("mid", condition);
						ScanRequest scanRequest = new ScanRequest("sqs").withScanFilter(scanFilter);
						ScanResult scanResult = dynamoDB.scan(scanRequest);
						msgCount = scanResult.getCount();
				
					// System.out.println(strArray[1]);
					if (msgCount == 0) {
						//System.out.println(count);
						//count++;
						//System.out.println("-----------------------------------------------");
						//if message is not there in DynamoDb then add it to the DynamoDB
						Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
						item.put("mid", new AttributeValue(message.get(0).getMessageId()));
						PutItemRequest putItemRequest = new PutItemRequest("sqs", item);
						PutItemResult putItemResult = dynamoDB.putItem(putItemRequest);
					
						try {
							//Executing the task
							Thread.sleep(Integer.parseInt(strArray[1]));
							//System.out.println(strArray[1]);
							sqs.sendMessage(new SendMessageRequest(outputQueueUrl, "0"));
							//sqs.deleteMessage(queueUrl, message.get(0).getReceiptHandle());
							// System.out.println(message.get(0).getBody());
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					receiveMessageRequest.setWaitTimeSeconds(1);
				}
			} catch (QueueDoesNotExistException exception) {
				System.out.println("queue doesn't exist");
				System.exit(0);
			}
		}

	}

}
