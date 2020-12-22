## KJanitor

This is an application to perform janitorial operations on a Kafka cluster.

### Operations supported

* Creation of topics
* Listing of topics
* Deletion of topics

#### Creation of topics

It takes a list of topics and the corresponding creation parameters (partitions, retention, cleanup policy) and creates the topics that are not present in the cluster.

Creation parameters can be specified as a default, which will then apply to all topics that do not specify their own.

In case of a topic that is already present in the cluster, no action will be performed other than printing the current configuration of the topic.

#### Listing of topics

It will connect to the cluster and get a list of all the topics present in the cluster.

#### Deletion of topics

In the simplest mode, it will take a list of topics to be deleted and delete them from the cluster.

There is a spring cleaning mode, which means getting a list of the topics and their current size. It will delete all topics that have 0 messages, unless they are whitelisted.
