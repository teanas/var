/**
 * Class for generating commands for benchmarking with YCSB
 * @author Anastasiia Sivirina
 *
 */
public class Ben {

	public static void main(String[] args) {
		
		String ip = "3.89.231.29"; //ip of the instance with databases
		String rec = "50000";  
		String op ="4000000";  //10000000 rmit 4000000 duet
		String thr = "5";  //14 rmit 5 duet
		
		//Depending on which approach is wanted, the undesired one should be commented out
		//rmit(ip, rec, op, thr);
		duet(ip, rec, op, thr);
	}
	
	/**
	 * Generates commands for loading and running workloads in a random order. Note, that port 27017 is for version 4.2.7 and 28018 for version 4.0.18
	 * @param ip
	 * @param rec
	 * @param op
	 * @param thr
	 */
	public static void rmit(String ip, String rec, String op, String thr) {
	
			for(int i=0; i<5; i++) {
				
				System.out.println("./bin/ycsb load mongodb-async -s -P workloads/workloada -s -p recordcount=" + rec +" -p operationcount=" + op + " -p threadcount=" + thr + " -p mongodb.url=mongodb://" + ip +":27017/ycsb?w=0 > outputLoad"+ip.length()+".txt\n");			
				System.out.println("./bin/ycsb load mongodb-async -s -P workloads/workloada -s -p recordcount=" + rec +" -p operationcount=" + op + " -p threadcount=" + thr + " -p mongodb.url=mongodb://" + ip +":28018/ycsb?w=0 > outputLoad"+ip.length()+".txt\n");

				double x = Math.random();
				if (x > 0.5) {
					System.out.println("./bin/ycsb run mongodb-async -s -P workloads/workloada -s -p recordcount=" + rec + " -p operationcount=" + op + " -p threadcount=" + thr + " -p mongodb.url=mongodb://" + ip +":28018/ycsb?w=0 > outputRun0"+i+"a.txt\n");
					System.out.println("./bin/ycsb run mongodb-async -s -P workloads/workloada -s -p recordcount=" + rec + " -p operationcount=" + op + " -p threadcount=" + thr + " -p mongodb.url=mongodb://" + ip +":27017/ycsb?w=0 > outputRun2"+i+"a.txt\n");
				
				} else {
					System.out.println("./bin/ycsb run mongodb-async -s -P workloads/workloada -s -p recordcount=" + rec + " -p operationcount=" + op + " -p threadcount=" + thr + " -p mongodb.url=mongodb://" + ip +":27017/ycsb?w=0 > outputRun2"+i+"a.txt\n");
					System.out.println("./bin/ycsb run mongodb-async -s -P workloads/workloada -s -p recordcount=" + rec + " -p operationcount=" + op + " -p threadcount=" + thr + " -p mongodb.url=mongodb://" + ip +":28018/ycsb?w=0 > outputRun0"+i+"a.txt\n");
				
				}
				}
		
	}
	
	/**
	 * Generates commands for loading and running workloads. Note, that port 8000 is for 4.2.7 and 9000 for 4.0.18. Note, that both parts has to be started simultaniously in the console.
	 * @param ip
	 * @param rec
	 * @param op
	 * @param thr
	 */
	public static void duet(String ip, String rec, String op, String thr) {
		
		for(int i=0; i<5; i++) {
			System.out.println("./bin/ycsb load mongodb-async -s -P workloads/workloada -s -p recordcount=" + rec +" -p operationcount=" + op + " -p threadcount=" + thr + " -p mongodb.url=mongodb://" + ip +":9000/ycsb?w=0 > outputLoad40.txt\n");
			System.out.println("./bin/ycsb run mongodb-async -s -P workloads/workloada -s -p recordcount=" + rec + " -p operationcount=" + op + " -p threadcount=" + thr + " -p mongodb.url=mongodb://" + ip +":9000/ycsb?w=0 > outputRun40"+i+"a.txt\n");
		}
	
		for(int i=0; i<5; i++) {
			System.out.println("./bin/ycsb load mongodb-async -s -P workloads/workloada -s -p recordcount=" + rec +" -p operationcount=" + op + " -p threadcount=" + thr + " -p mongodb.url=mongodb://" + ip +":8000/ycsb?w=0 > outputLoad42.txt\n");
			System.out.println("./bin/ycsb run mongodb-async -s -P workloads/workloada -s -p recordcount=" + rec + " -p operationcount=" + op + " -p threadcount=" + thr + " -p mongodb.url=mongodb://" + ip +":8000/ycsb?w=0 > outputRun42"+i+"a.txt\n");
		}
		
	}

}
