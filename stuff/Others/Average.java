public class Average {
     public static void main(String args[]) {
            int sum = 0;
            for(String argument: args) {
                   sum + = argument;
            }
            System.out.println("The Average is : ", sum/args.length);
      }

}
