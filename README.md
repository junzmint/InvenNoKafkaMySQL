# Inventory Processing System
## Inventory Problems 
This is a Inventory Processing System based on LMAX disruptor. This is a version without Kafka for logging events and MySQL for database. This system is using a Single thread-based model and local memory but must handle a large volume of traffic. If the system can guarantee the eventual consistency of the inventory data of many data sources, it allows applying advanced techniques to handle the transaction. The approach here is to store data in local memory and use non-blocking techniques to maximize the throughput.
## Technology
- Java
- Guava
- LMAX Disruptor
- Gridgo
## Perfomance testing - Benchmark
