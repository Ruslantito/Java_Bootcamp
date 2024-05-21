package ex04;

public class TransactionsLinkedList implements TransactionsList {
    public int size = 0;
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
            throw new IllegalTransactionException("Transaction not found!");
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

    public Transaction[] getTransByUserId(int userId) {
        Transaction[] transactions = new Transaction[size];
        transactionNode nodeTmp = new transactionNode();
        nodeTmp.next = head;
        transactionNode currentNode = nodeTmp;
        int newSize = 0;
        for(int i = 0; i < size; ++i) {
            if(currentNode.next.transaction.getSender().getId() == userId) {
                transactions[newSize] = currentNode.next.transaction;
                ++newSize;
            }
            currentNode = currentNode.next;
        }
        return transactions;
    }

    public Transaction removeTransactionById(int userId, String transactionId) {
        if (this.head == null) {
            return null;
        }
        transactionNode nodeTmp = new transactionNode();
        nodeTmp.next = head;
        transactionNode currentNode = nodeTmp;
        Transaction res = new Transaction();
        while (currentNode.next != null) {
            if (currentNode.next.transaction.getId() == transactionId) {
                if (currentNode.next.transaction.getSender().getId() == userId) {
                    res = currentNode.next.transaction;
                    currentNode.next = currentNode.next.next;
                    size--;
                    return res;
                } else {
                    currentNode = currentNode.next;
                }
            } else {
                currentNode = currentNode.next;
            }
        }
        return null;
    }

    public Transaction[] checkTransferValidity() {
        Transaction[] transformedToArray = new Transaction[size];
        transactionNode nodeTmp = new transactionNode();
        nodeTmp.next = head;
        transactionNode currentNode = nodeTmp;
        
        for(int i = 0, k = 0; i < size; ++i) {
            boolean inList = false;
            transactionNode nodeTmp2 = new transactionNode();
            nodeTmp2.next = head;
            transactionNode currentNode2 = nodeTmp2;
            for(int j = 0; j < size; ++j) {

                if(i != j && currentNode.next.transaction.getId() == currentNode2.next.transaction.getId()) {
                    inList = true;
                }
                currentNode2 = currentNode2.next;
            }
            if(!inList) {
                transformedToArray[k] = currentNode.next.transaction;
                ++k;
            }            
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
