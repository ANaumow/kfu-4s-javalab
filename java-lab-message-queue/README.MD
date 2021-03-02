### Example of sdk usage

```
    JlmqConnector connector = Jlmq.connector()
            .usingStomp(false)
            .port("80")
            .connect();
    
    JlmqProducer producer = connector.producer()
            .toQueue("documents_for_generate")
            .create();
    
    JlmqConsumer consumer = connector.consumer()
            .subscribe("documents_for_generate")
            .onReceive(message -> {
                message.accepted();
                System.out.println("handling message: ");
                System.out.println(message.getBody());
                message.completed();
            })
            .create();
    
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
        producer.send(scanner.nextLine());
    }
```
