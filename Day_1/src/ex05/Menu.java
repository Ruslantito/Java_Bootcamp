package ex05;

import java.util.Scanner;

public class Menu {
    private TransactionsService transactionsService = new TransactionsService();
        
    public void process(String[] args) {
        boolean modeDev = checkModeDev(args);
        if(!modeDev && args.length > 0) {
            notCorrectInfos();
            System.exit(-1);
        }
        drawMenu(modeDev);
        Scanner in = new Scanner(System.in);
        int parameter = getInteget(in)[1]; 
        while(parameter != 7) {            
            menuProcess(parameter, in, modeDev);
            System.out.println("---------------------------------------------------------");
            drawMenu(modeDev);
            parameter = getInteget(in)[1]; 
        }
        in.close();
    }
    
    private void drawMenu(boolean modeDev) {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if(modeDev) {
            System.out.println("5. DEV - remove a transfer by ID");
            System.out.println("6. DEV - check transfer validity");
        }
        System.out.println("7. Finish execution");
    }

    private boolean checkModeDev(String[] args) {
        boolean modeDev = false;
        if(args.length == 1) {
            String[] param1 = new String[3];
            param1 = args[0].split("=");
            if(param1.length == 2 && param1[0].equals("--profile") && param1[1].equals("dev")) {
                modeDev = true;
            }
        }
        return modeDev;
    }

    private void menuProcess(int parameter, Scanner in, boolean modeDev) {
        switch (parameter) {
            case 1:
                menuProccessAddUser(in);
                break;
            case 2:
                menuProccessUserBalance(in);
                break;
            case 3:
                menuProccessPerformTransfer(in);                
                break;
            case 4:
                menuProccessTransactionsByUser(in);
                break;
            case 5:
                menuProccessRemovingTransactionsById(in, modeDev);
                break;
            case 6:
                menuProccessTransferValidity(modeDev);
                break;
            default:
                notCorrectInfos();
            break;
        }
    }

    private void menuProccessAddUser(Scanner in) {
        System.out.println("Enter a user name and a balance");
        String[] userDataArr = getNewData(in);
        if(userDataArr.length == 2 ) {
            int[] convertedData = stringToInt(userDataArr[1]);
            if(convertedData[0] == 1) {
                transactionsService.addUser(userDataArr[0], convertedData[1]);
            }
        } else {
            notCorrectInfos();
        }
    }

    private void menuProccessUserBalance(Scanner in) {
        System.out.println("Enter a user ID");
        String[] userIdArr = getNewData(in);
        if(userIdArr.length == 1) {
            int[] convertedData = stringToInt(userIdArr[0]);
            if(convertedData[0] == 1) {
                try {
                    User user = transactionsService.getUserById(convertedData[1]);
                    transactionsService.printUserBalance(user);
                } catch (Exception e) {
                    notCorrectInfos();
                }
            }
        } else {
            notCorrectInfos();
        }
    }

    private void menuProccessPerformTransfer(Scanner in) {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        String[] transferDataArr = getNewData(in);
        if(transferDataArr.length == 3) {
            int[] senderId = stringToInt(transferDataArr[0]);
            int[] recipientId = stringToInt(transferDataArr[1]);
            int[] transferAmount = stringToInt(transferDataArr[2]);
            if(senderId[0] == 1 && recipientId[0] == 1 && transferAmount[0] == 1) {
                if (senderId[1] == recipientId[1]) {
                    System.out.println("Sender ID and recipient ID can't be same, please enter correct informations!");   
                } else {
                    boolean complete = transactionsService.addTransaction(senderId[1], recipientId[1], transferAmount[1]);
                    if(complete) {
                        System.out.println("The transfer is completed");
                    } else {
                        notCorrectInfos();
                    }
                }
            } else {
                notCorrectInfos();
            }
        } else {
            notCorrectInfos();
        }
    }

    private void menuProccessTransactionsByUser(Scanner in) {
        System.out.println("Enter a user ID");
        String[] transactionsDataArr = getNewData(in);
        if(transactionsDataArr.length == 1) {
            int[] convertedData = stringToInt(transactionsDataArr[0]);
            if(convertedData[0] == 1) {
                Transaction[] transactionsByUser = transactionsService.getTransactionsByUserId(convertedData[1]);
                if(transactionsByUser.length == 0) {
                    System.out.println("No transactions found for this user(id = " + transactionsDataArr[0] + ")!");
                } else {
                    transactionsService.printTransactions(transactionsByUser);
                }
            }
        }        
    }

    private void menuProccessRemovingTransactionsById(Scanner in, boolean modeDev) {
        if(modeDev) {
            System.out.println("Enter a user ID and a transfer ID");
            String[] removeDataArr = getNewData(in);
            if(removeDataArr.length == 2) {
                int[] convertedData = stringToInt(removeDataArr[0]);
                if(convertedData[0] == 1) {
                    Transaction transactionDel = transactionsService.removingTransactionsById(convertedData[1], removeDataArr[1]);
                    if(transactionDel != null) {
                        transactionsService.printDeletedTransaction(transactionDel);
                    } else {
                        System.out.print("No transactions found for this user(id = " + convertedData[1] + ")");
                        System.out.println(" and transfer ID(id = "  + removeDataArr[1] + ")!");
                    }
                }
            } else {
                notCorrectInfos();
            }
        } else {
            notCorrectInfos();
        }
    }

    private void menuProccessTransferValidity(boolean modeDev) {
        if(modeDev) {
            System.out.println("Check results:");
            Transaction[] transferValidity = transactionsService.transferValidity();
            if(transferValidity.length == 0) {
                System.out.println("No transactions found!");
            } else {
                transactionsService.printTransactionsValidity(transferValidity);
            }
        } else {
            notCorrectInfos();
        }
    }
    
    private int[] getInteget(Scanner in) {
        int dataArr[] = {0, -1};
        String[] getInteger = getNewData(in);
        if(getInteger.length == 1) {
            try {
                dataArr = stringToInt(getInteger[0]);
            } catch (Exception e) {
                in.nextLine();
            }
        }
        return dataArr;
    }

    private String[] getNewData(Scanner in) {
        String data = new String();
        String[] dataArr = new String[5];
        data = in.nextLine();
        dataArr = data.split(" ");
        return dataArr;
    }

    private int[] stringToInt(String str) {
        int dataArr[] = {0, -1};
        try {
            dataArr[1] = Integer.valueOf(str);
            dataArr[0] = 1;
        } catch (Exception e) {
            notCorrectInfos();
        }
        return dataArr;
    }

    private void notCorrectInfos() {
        System.out.println("Please enter correct information!");
    }
}
