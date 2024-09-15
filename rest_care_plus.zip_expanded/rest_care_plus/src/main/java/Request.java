
public class Request {
	import java.util.List;

	public interface RequestService {

	    List<RequestPojo> getRequestsByClientId(int clientId);

	    List<RequestPojo> getRequestsByServiceProviderId(int serviceProviderId);

	    List<RequestPojo> getRequestsByClientIdAndStatus(int clientId, int statusId);

	    List<RequestPojo> getRequestsByServiceProviderIdAndStatus(int serviceProviderId, int statusId);

	    RequestPojo getRequestById(int requestId);

	    RequestPojo addRequest(RequestPojo requestPojo);

	    RequestPojo updateRequest(RequestPojo requestPojo);

	    void updateRequestStatus(int requestId, int statusId);
	}

	
	
	
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import org.springframework.beans.BeanUtils;

	import java.util.List;
	import java.util.stream.Collectors;

	@Service
	public class RequestServiceImpl implements RequestService {

	    @Autowired
	    private RequestDao requestDao;

	    @Autowired
	    private UserInfoDao userInfoDao;

	    @Autowired
	    private StatusDao statusDao;

	    @Autowired
	    private ServiceProviderDao serviceProviderDao;

	    @Override
	    public RequestPojo addRequest(RequestPojo requestPojo) {
	        RequestEntity requestEntity = new RequestEntity();
	        BeanUtils.copyProperties(requestPojo, requestEntity);
	        RequestEntity savedRequest = requestDao.save(requestEntity);
	        return convertEntityToPojo(savedRequest);
	    }

	    @Override
	    public List<RequestPojo> fetchAllRequests() {
	        List<RequestEntity> requestEntities = requestDao.findAll();
	        return requestEntities.stream().map(this::convertEntityToPojo).collect(Collectors.toList());
	    }

	    @Override
	    public RequestPojo fetchRequestById(int requestId) {
	        RequestEntity requestEntity = requestDao.findById(requestId)
	                .orElseThrow(() -> new ResourceNotFoundException("Request not found with ID: " + requestId));
	        return convertEntityToPojo(requestEntity);
	    }

	    @Override
	    public RequestPojo updateRequest(int requestId, RequestPojo requestPojo) {
	        RequestEntity requestEntity = requestDao.findById(requestId)
	                .orElseThrow(() -> new ResourceNotFoundException("Request not found with ID: " + requestId));
	        BeanUtils.copyProperties(requestPojo, requestEntity, "requestId");
	        RequestEntity updatedRequest = requestDao.save(requestEntity);
	        return convertEntityToPojo(updatedRequest);
	    }

	    @Override
	    public List<RequestPojo> getRequestsByClient(int clientId) {
	        UserInfoEntity clientEntity = userInfoDao.findById(clientId)
	                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + clientId));
	        List<RequestEntity> requestEntities = requestDao.findByClientEntity(clientEntity);
	        return requestEntities.stream().map(this::convertEntityToPojo).collect(Collectors.toList());
	    }

	    @Override
	    public List<RequestPojo> getRequestsByServiceProvider(int spId) {
	        ServiceProviderEntity serviceProviderEntity = serviceProviderDao.findById(spId)
	                .orElseThrow(() -> new ResourceNotFoundException("Service provider not found with ID: " + spId));
	        List<RequestEntity> requestEntities = requestDao.findByServiceProviderEntity(serviceProviderEntity);
	        return requestEntities.stream().map(this::convertEntityToPojo).collect(Collectors.toList());
	    }

	    @Override
	    public List<RequestPojo> getRequestsByClientAndStatus(int clientId, int statusId) {
	        UserInfoEntity clientEntity = userInfoDao.findById(clientId)
	                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + clientId));
	        StatusEntity statusEntity = statusDao.findById(statusId)
	                .orElseThrow(() -> new ResourceNotFoundException("Status not found with ID: " + statusId));
	        List<RequestEntity> requestEntities = requestDao.findByClientEntityAndStatusEntity(clientEntity, statusEntity);
	        return requestEntities.stream().map(this::convertEntityToPojo).collect(Collectors.toList());
	    }

	    @Override
	    public List<RequestPojo> getRequestsByServiceProviderAndStatus(int spId, int statusId) {
	        ServiceProviderEntity serviceProviderEntity = serviceProviderDao.findById(spId)
	                .orElseThrow(() -> new ResourceNotFoundException("Service provider not found with ID: " + spId));
	        StatusEntity statusEntity = statusDao.findById(statusId)
	                .orElseThrow(() -> new ResourceNotFoundException("Status not found with ID: " + statusId));
	        List<RequestEntity> requestEntities = requestDao.findByServiceProviderEntityAndStatusEntity(serviceProviderEntity, statusEntity);
	        return requestEntities.stream().map(this::convertEntityToPojo).collect(Collectors.toList());
	    }

	    @Override
	    public void updateRequestStatus(int requestId, int statusId) {
	        RequestEntity requestEntity = requestDao.findById(requestId)
	                .orElseThrow(() -> new ResourceNotFoundException("Request not found with ID: " + requestId));
	        StatusEntity statusEntity = statusDao.findById(statusId)
	                .orElseThrow(() -> new ResourceNotFoundException("Status not found with ID: " + statusId));
	        requestEntity.setStatusEntity(statusEntity);
	        requestDao.save(requestEntity);
	    }

	    // Helper method to convert entity to pojo
	    private RequestPojo convertEntityToPojo(RequestEntity requestEntity) {
	        RequestPojo requestPojo = new RequestPojo();
	        BeanUtils.copyProperties(requestEntity, requestPojo);

	        // Copy nested objects as well
	        if (requestEntity.getClientEntity() != null) {
	            UserInfoPojo clientPojo = new UserInfoPojo();
	            BeanUtils.copyProperties(requestEntity.getClientEntity(), clientPojo);
	            requestPojo.setClientPojo(clientPojo);
	        }

	        if (requestEntity.getServiceProviderEntity() != null) {
	            ServiceProviderPojo spPojo = new ServiceProviderPojo();
	            BeanUtils.copyProperties(requestEntity.getServiceProviderEntity(), spPojo);
	            requestPojo.setServiceProviderPojo(spPojo);
	        }

	        if (requestEntity.getAddressEntity() != null) {
	            AddressPojo addressPojo = new AddressPojo();
	            BeanUtils.copyProperties(requestEntity.getAddressEntity(), addressPojo);
	            requestPojo.setAddressPojo(addressPojo);
	        }

	        if (requestEntity.getMemberEntity() != null) {
	            MemberPojo memberPojo = new MemberPojo();
	            BeanUtils.copyProperties(requestEntity.getMemberEntity(), memberPojo);
	            requestPojo.setMemberPojo(memberPojo);
	        }

	        if (requestEntity.getStatusEntity() != null) {
	            StatusPojo statusPojo = new StatusPojo();
	            BeanUtils.copyProperties(requestEntity.getStatusEntity(), statusPojo);
	            requestPojo.setStatusPojo(statusPojo);
	        }

	        return requestPojo;
	    }
	}

	
	
	
	
	@RestController
	@RequestMapping("/members")
	public class MemberController {

	    @Autowired
	    private MemberService memberService;

	    @GetMapping("/{id}")
	    public ResponseEntity<MemberPojo> getMember(@PathVariable int id) {
	        MemberPojo member = memberService.fetchAMember(id);
	        return new ResponseEntity<>(member, HttpStatus.OK);
	    }

	    @PostMapping("/")
	    public ResponseEntity<MemberPojo> addMember(@RequestBody MemberPojo newMember) {
	        MemberPojo member = memberService.addMember(newMember);
	        return new ResponseEntity<>(member, HttpStatus.CREATED);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<MemberPojo> updateMember(@PathVariable int id, @RequestBody MemberPojo updateMember) {
	        updateMember.setMemberId(id);
	        MemberPojo member = memberService.updateMember(updateMember);
	        return new ResponseEntity<>(member, HttpStatus.OK);
	    }

	    @GetMapping("/byClient/{clientId}")
	    public ResponseEntity<List<MemberPojo>> getMembersByClientId(@PathVariable int clientId) {
	        List<MemberPojo> members = memberService.findByAddedByClientId(clientId);
	        return new ResponseEntity<>(members, HttpStatus.OK);
	    }

	    // Add more endpoints for adminId and statusId if required.
	}

	
	
	
	
	
	@ControllerAdvice
	public class GlobalExceptionHandler {

	    @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleGlobalException(Exception ex) {
	        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	
	
	
	
	
	
	
	
	
	
	