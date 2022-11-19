import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

public class InstanceManager {
    final AmazonEC2 ec2 = AmazonEC2ClientBuilder.defaultClient();

    public void listInstances(){
        DescribeInstancesRequest request = new DescribeInstancesRequest();
        DescribeInstancesResult response = ec2.describeInstances(request);
        for (Reservation reservation : response.getReservations()) {
            for (Instance instance : reservation.getInstances()) {
                printInstanceInfo(instance);
            }
        }
    }

    public void printInstanceInfo(Instance instance){
        String id = instance.getInstanceId();
        String type = instance.getInstanceType();
        String state = instance.getState().getName();
        System.out.println(
                "[ID]  " + id
                +" [TYPE]  " + type
                +" [STATE]  " + state

        );
    }
}
