import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;

public class EC2Manager {
    final AmazonEC2 ec2 = AmazonEC2ClientBuilder.defaultClient();

    /**
     * EC2 인스턴스 리스트 조회
     */
    public void listInstances(){
        DescribeInstancesResult instances = ec2.describeInstances();
        for (Reservation reservation : instances.getReservations()) {
            for (Instance instance : reservation.getInstances()) {
                printInstanceInfo(instance);
            }
        }
    }

    /**
     * 인스턴스 정보 출력
     * @param instance
     */
    public void printInstanceInfo(Instance instance){
        String id = instance.getInstanceId();
        String type = instance.getInstanceType();
        String state = instance.getState().getName();
        System.out.println(
                "[ID]  " + id
                +"    [TYPE]  " + type
                +"    [STATE]  " + state
        );
    }

    /**
     * EC2 가용 지역 리스트 조회
     */
    public void listRegions(){
        DescribeRegionsResult regions = ec2.describeRegions();
        for (Region region : regions.getRegions()) {
            printRegionInfo(region);
        }
    }

    /**
     * 지역 정보 출력
     * @param region
     */
    public void printRegionInfo(Region region){
        String regionName = region.getRegionName();
        String endPoint = region.getEndpoint();
        System.out.println(
                "[REGION]  " + regionName
                +"    [ENDPOINT]  " + endPoint
        );
    }

    /**
     * EC2 가용 zone 리스트 조회
     */
    public void listZones(){
        DescribeAvailabilityZonesResult zones = ec2.describeAvailabilityZones();
        for (AvailabilityZone zone : zones.getAvailabilityZones()) {
            printZoneInfo(zone);
        }
    }

    /**
     * zone 정보 출력
     * @param zone
     */
    public void printZoneInfo(AvailabilityZone zone){
        String region = zone.getRegionName();
        String id = zone.getZoneId();
        String name = zone.getZoneName();
        System.out.println(
                "[ID]  " + id
                + "    [REGION]  " + region
                + "    [ZONE]  " + name
        );
    }
}
