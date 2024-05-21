package ex03;

public class TransactionsLinkedList implements TransactionsList {
    private int size = 0;
    transactionNode head = null; 
    transactionNode tail = null;

    class transactionNode {
        private Transaction transaction;
        private transactionNode next;
        // private Node previous;

        public transactionNode(){};        
        public transactionNode(Transaction transaction, transactionNode next) {
            this.transaction = transaction;
            this.next = next;
        }
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (head == null) {
            transactionNode node = new transactionNode(transaction, null);
            head = node;
            tail = node;
        } else {
            transactionNode node = new transactionNode(transaction, head);
            // head.previous = node;
            head = node;
        }
        size++;
    }

    @Override
    public Transaction removeTransaction(String transactionId) {
        if (this.head == null) {
            return null;
        }
        transactionNode nodeTmp = new transactionNode();
        nodeTmp.next = head;
        transactionNode currentNode = nodeTmp;
        Transaction forReturn = new Transaction();
        while (currentNode.next != null) {
            if (currentNode.next.transaction.getId() == transactionId) {
                forReturn = currentNode.next.transaction;
                currentNode.next = currentNode.next.next;
                size--;                
            } else {
                currentNode = currentNode.next;
            }
        }
        head = nodeTmp.next;
        if(forReturn == null) {
            throw new TransactionNotFoundException("Transaction not found!");
        }
        return forReturn;
    }
    

    @Override
    public Transaction[] toArray() {
        Transaction[] transformedToArray = new Transaction[size];
        transactionNode nodeTmp = new transactionNode();
        nodeTmp.next = head;
        transactionNode currentNode = nodeTmp;

        for(int i = 0; i < size; ++i) {
            transformedToArray[i] = currentNode.next.transaction;
            currentNode = currentNode.next;
        }
        return transformedToArray;
    }


    public void print() {
        transactionNode nodePtr = head;
        while(nodePtr != null) {           
            nodePtr.transaction.print();
            nodePtr = nodePtr.next;
        }        
    }
}
