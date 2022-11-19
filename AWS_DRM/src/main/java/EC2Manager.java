import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;

public class EC2Manager {
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

    public void listRegions(){
        DescribeRegionsResult regionsResult = ec2.describeRegions();
        for (Region region : regionsResult.getRegions()) {
            printRegionInfo(region);
        }
    }

    public void printRegionInfo(Region region){
        String regionName = region.getRegionName();
        String endPoint = region.getEndpoint();
        System.out.println(
                "[REGION]   " + regionName
                +"  [ENDPOINT]   " + endPoint
        );
    }
}
