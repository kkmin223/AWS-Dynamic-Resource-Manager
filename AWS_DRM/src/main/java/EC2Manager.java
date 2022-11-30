import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;

public class EC2Manager {
    final AmazonEC2 ec2;

    public EC2Manager(){
        ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
        try {
            credentialsProvider.getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (~/.aws/credentials), and is in valid format.",
                    e);
        }
        ec2 = AmazonEC2ClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion("ap-northeast-2")
                .build();

    }

    /**
     * EC2 인스턴스 리스트 조회
     */
    public void listInstances(){
        try {
            DescribeInstancesRequest request = new DescribeInstancesRequest();
            while(true){
                DescribeInstancesResult response = ec2.describeInstances(request);

                for (Reservation reservation : response.getReservations()) {
                    for (Instance instance : reservation.getInstances()) {
                        printInstanceInfo(instance);
                    }
                }
                request.setNextToken(response.getNextToken());
                if(response.getNextToken() == null){
                    return;
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
            String imageId = instance.getImageId();
            String type = instance.getInstanceType();
            String state = instance.getState().getName();
            String monitorState = instance.getMonitoring().getState();

            System.out.printf("[id] %s, [AMI] %s, [type] %s, [state] %10s, [monitoring state] %s\n",id, imageId, type, state, monitorState);
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
            System.out.printf("[region] %15s, [endpoint] %s\n", regionName, endPoint);
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
            System.out.printf("[id] %s, [region] %15s, [zone] %15s\n", id, region, name);
        } catch (Exception e){
            System.out.println("[Exception] " + e.toString());
        }

    }

    /**
     * 인스턴스 재시작*
     * @param instanceID
     */
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

    public void stopInstance(String instanceID){
        try{
            System.out.printf("Stop Instance ... %s\n", instanceID);
            StopInstancesRequest request = new StopInstancesRequest()
                    .withInstanceIds(instanceID);
            StopInstancesResult response = ec2.stopInstances(request);
            System.out.printf("Successfully stop instance %s", instanceID);
        } catch (Exception e){
            System.out.println("[Exception] " + e.toString());
        }
    }

    public void startInstance(String instanceID){
        try{
            System.out.printf("Start Instance ... %s\n", instanceID);
            StartInstancesRequest request = new StartInstancesRequest()
                    .withInstanceIds(instanceID);
            StartInstancesResult response = ec2.startInstances(request);
            System.out.printf("Successfully start instance %s", instanceID);
        } catch (Exception e){
            System.out.println("[Exception] " + e.toString());
        }
    }

    public void listImages(){
        try {
            DescribeImagesRequest request = new DescribeImagesRequest();
            ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
            request.getFilters().add(new Filter().withName("name").withValues("aws-condor-slave"));
            request.setRequestCredentialsProvider(credentialsProvider);

            DescribeImagesResult images = ec2.describeImages(request);

            for (Image image : images.getImages()) {
                printImage(image);
            }
        } catch (Exception e){
            System.out.println("[Exception] " + e.toString());
        }
    }

    public void createInstance(String ami_id) {
        final AmazonEC2 ec2 = AmazonEC2ClientBuilder.defaultClient();

        RunInstancesRequest run_request = new RunInstancesRequest()
                .withImageId(ami_id)
                .withInstanceType(InstanceType.T2Micro)
                .withMaxCount(1)
                .withMinCount(1);

        RunInstancesResult run_response = ec2.runInstances(run_request);

        String reservation_id = run_response.getReservation().getInstances().get(0).getInstanceId();

        System.out.printf(
                "Successfully started EC2 instance %s based on AMI %s",
                reservation_id, ami_id);

    }

    public void printImage(Image image) {
        String imageId = image.getImageId();
        String name = image.getName();
        String owner = image.getOwnerId();
        System.out.printf("[ImageID] %s [Name] %s [Owner] %s\n", imageId, name, owner);
    }
}
