import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;

public class EC2Manager {
    final AmazonEC2 ec2 = AmazonEC2ClientBuilder.defaultClient();

    /**
     * EC2 인스턴스 리스트 조회
     */
    public void listInstances(){
        try {
            DescribeInstancesResult instances = ec2.describeInstances();
            for (Reservation reservation : instances.getReservations()) {
                for (Instance instance : reservation.getInstances()) {
                    printInstanceInfo(instance);
                }
            }
        } catch (Exception e){
            System.out.println("[Exception] " + e.toString());
        }
    }

    /**
     * 인스턴스 정보 출력
     * @param instance
     */
    public void printInstanceInfo(Instance instance){
        try {
            String id = instance.getInstanceId();
            String type = instance.getInstanceType();
            String state = instance.getState().getName();
            System.out.println(
                    "[ID]  " + id
                            +"    [TYPE]  " + type
                            +"    [STATE]  " + state
            );
        } catch (Exception e){
            System.out.println("[Exception] " + e.toString());
        }

    }

    /**
     * EC2 가용 지역 리스트 조회
     */
    public void listRegions(){
        try {
            DescribeRegionsResult regions = ec2.describeRegions();
            for (Region region : regions.getRegions()) {
                printRegionInfo(region);
            }
        } catch (Exception e){
            System.out.println("[Exception] " + e.toString());
        }

    }

    /**
     * 지역 정보 출력
     * @param region
     */
    public void printRegionInfo(Region region){
        try {
            String regionName = region.getRegionName();
            String endPoint = region.getEndpoint();
            System.out.println(
                    "[REGION]  " + regionName
                            +"    [ENDPOINT]  " + endPoint
            );
        } catch (Exception e){
            System.out.println("[Exception] " + e.toString());
        }

    }

    /**
     * EC2 가용 zone 리스트 조회
     */
    public void listZones(){
        try {
            DescribeAvailabilityZonesResult zones = ec2.describeAvailabilityZones();
            for (AvailabilityZone zone : zones.getAvailabilityZones()) {
                printZoneInfo(zone);
            }
        } catch (Exception e){
            System.out.println("[Exception] " + e.toString());
        }

    }

    /**
     * zone 정보 출력
     * @param zone
     */
    public void printZoneInfo(AvailabilityZone zone){
        try {
            String region = zone.getRegionName();
            String id = zone.getZoneId();
            String name = zone.getZoneName();
            System.out.println(
                    "[ID]  " + id
                            + "    [REGION]  " + region
                            + "    [ZONE]  " + name
            );
        } catch (Exception e){
            System.out.println("[Exception] " + e.toString());
        }

    }

    public void rebootInstance(String instanceID){
        try {
            System.out.printf("Rebooting ... %s\n", instanceID);
            RebootInstancesRequest request = new RebootInstancesRequest()
                    .withInstanceIds(instanceID);

            RebootInstancesResult response = ec2.rebootInstances(request);
            System.out.printf("Successfully rebooted instance %s", instanceID);
        } catch (Exception e){
            System.out.println("[Exception] " + e.toString());
        }

    }
}
