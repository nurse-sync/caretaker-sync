{
  "clientEntity": {
    "userId": 1
  },
  "serviceProviderEntity": {
    "spId": 2
  },
  "addressEntity": {
    "addressId": 3
  },
  "startDate": "2024-09-25",
  "endDate": "2024-09-30",
  "memberEntity": {
    "memberId": 4
  },
  "messageToSp": "Please confirm your availability.",
  "statusEntity": {
    "statusId": 1
  }
}







{
	  "clientEntity": {
	    "userId": 1
	  },
	  "serviceProviderEntity": {
	    "spId": 2
	  },
	  "addressEntity": {
	    "addressId": 3
	  },
	  "startDate": "2024-09-25",
	  "endDate": "2024-09-30",
	  "memberEntity": {
	    "memberId": 4
	  },
	  "messageToSp": "Updated message to service provider.",
	  "messageFromSp": "Service provider message updated.",
	  "statusEntity": {
	    "statusId": 2
	  }
	}













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

	
	
	
	
	
	
	@Override
	public RequestPojo updateRequest(RequestPojo requestPojo) {
	    int requestId = requestPojo.getRequestId(); // Assuming RequestPojo has requestId
	    RequestEntity requestEntity = requestDao.findById(requestId)
	            .orElseThrow(() -> new ResourceNotFoundException("Request not found with ID: " + requestId));

	    // Copy properties from pojo to entity, excluding the ID
	    BeanUtils.copyProperties(requestPojo, requestEntity, "requestId");

	    // Save updated entity
	    RequestEntity updatedRequest = requestDao.save(requestEntity);
	    return convertEntityToPojo(updatedRequest);
	}

	
	
	
	
	@PutMapping
	public ResponseEntity<RequestPojo> updateRequest(@RequestBody RequestPojo requestPojo) {
	    RequestPojo updatedRequest = requestService.updateRequest(requestPojo);
	    return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
	}

	
	
	
	
	import java.util.List;
	import java.util.Optional;
	import java.util.stream.Collectors;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import org.springframework.beans.BeanUtils;

	@Service
	public class RequestServiceImpl implements RequestService {

	    @Autowired
	    private RequestDao requestDao;

	    @Autowired
	    private UserInfoDao userInfoDao;

	    @Autowired
	    private ServiceProviderDao serviceProviderDao;

	    @Autowired
	    private StatusDao statusDao;

	    @Autowired
	    private MemberDao memberDao;

	    // Convert RequestEntity to RequestPojo
	    private RequestPojo convertEntityToPojo(RequestEntity requestEntity) {
	        RequestPojo requestPojo = new RequestPojo();
	        BeanUtils.copyProperties(requestEntity, requestPojo);

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

	        if (requestEntity.getStatusEntity() != null) {
	            StatusPojo statusPojo = new StatusPojo();
	            BeanUtils.copyProperties(requestEntity.getStatusEntity(), statusPojo);
	            requestPojo.setStatusPojo(statusPojo);
	        }

	        if (requestEntity.getMemberEntity() != null) {
	            MemberPojo memberPojo = new MemberPojo();
	            BeanUtils.copyProperties(requestEntity.getMemberEntity(), memberPojo);
	            requestPojo.setMemberPojo(memberPojo);
	        }

	        return requestPojo;
	    }

	    // Convert RequestPojo to RequestEntity
	    private RequestEntity convertPojoToEntity(RequestPojo requestPojo) {
	        RequestEntity requestEntity = new RequestEntity();
	        BeanUtils.copyProperties(requestPojo, requestEntity);

	        if (requestPojo.getClientPojo() != null) {
	            UserInfoEntity clientEntity = new UserInfoEntity();
	            BeanUtils.copyProperties(requestPojo.getClientPojo(), clientEntity);
	            requestEntity.setClientEntity(clientEntity);
	        }

	        if (requestPojo.getServiceProviderPojo() != null) {
	            ServiceProviderEntity spEntity = new ServiceProviderEntity();
	            BeanUtils.copyProperties(requestPojo.getServiceProviderPojo(), spEntity);
	            requestEntity.setServiceProviderEntity(spEntity);
	        }

	        if (requestPojo.getStatusPojo() != null) {
	            StatusEntity statusEntity = new StatusEntity();
	            BeanUtils.copyProperties(requestPojo.getStatusPojo(), statusEntity);
	            requestEntity.setStatusEntity(statusEntity);
	        }

	        if (requestPojo.getMemberPojo() != null) {
	            MemberEntity memberEntity = new MemberEntity();
	            BeanUtils.copyProperties(requestPojo.getMemberPojo(), memberEntity);
	            requestEntity.setMemberEntity(memberEntity);
	        }

	        return requestEntity;
	    }

	    // Add new request
	    @Override
	    public RequestPojo addRequest(RequestPojo requestPojo) {
	        RequestEntity requestEntity = convertPojoToEntity(requestPojo);
	        RequestEntity savedRequest = requestDao.save(requestEntity);
	        return convertEntityToPojo(savedRequest);
	    }

	    // Fetch all requests
	    @Override
	    public List<RequestPojo> fetchAllRequests() {
	        List<RequestEntity> requestEntities = requestDao.findAll();
	        return requestEntities.stream().map(this::convertEntityToPojo).collect(Collectors.toList());
	    }

	    // Fetch a request by ID
	    @Override
	    public RequestPojo fetchRequestById(int requestId) {
	        Optional<RequestEntity> requestEntityOpt = requestDao.findById(requestId);
	        return requestEntityOpt.map(this::convertEntityToPojo).orElse(null); // No exception handling
	    }

	    // Update request by passing RequestPojo
	    @Override
	    public RequestPojo updateRequest(RequestPojo requestPojo) {
	        int requestId = requestPojo.getRequestId(); // Assuming RequestPojo has requestId
	        Optional<RequestEntity> requestEntityOpt = requestDao.findById(requestId);
	        if (requestEntityOpt.isPresent()) {
	            RequestEntity requestEntity = requestEntityOpt.get();
	            BeanUtils.copyProperties(requestPojo, requestEntity, "requestId");
	            RequestEntity updatedRequest = requestDao.save(requestEntity);
	            return convertEntityToPojo(updatedRequest);
	        }
	        return null; // No exception handling
	    }

	    // Get requests by client ID
	    @Override
	    public List<RequestPojo> getRequestsByClient(int clientId) {
	        UserInfoEntity clientEntity = userInfoDao.findById(clientId).orElse(null); // No exception handling
	        List<RequestEntity> requestEntities = requestDao.findByClientEntity(clientEntity);
	        return requestEntities.stream().map(this::convertEntityToPojo).collect(Collectors.toList());
	    }

	    // Get requests by service provider ID
	    @Override
	    public List<RequestPojo> getRequestsByServiceProvider(int spId) {
	        ServiceProviderEntity spEntity = serviceProviderDao.findById(spId).orElse(null); // No exception handling
	        List<RequestEntity> requestEntities = requestDao.findByServiceProviderEntity(spEntity);
	        return requestEntities.stream().map(this::convertEntityToPojo).collect(Collectors.toList());
	    }

	    // Get requests by client and status ID
	    @Override
	    public List<RequestPojo> getRequestsByClientAndStatus(int clientId, int statusId) {
	        UserInfoEntity clientEntity = userInfoDao.findById(clientId).orElse(null); // No exception handling
	        StatusEntity statusEntity = statusDao.findById(statusId).orElse(null); // No exception handling
	        List<RequestEntity> requestEntities = requestDao.findByClientEntityAndStatusEntity(clientEntity, statusEntity);
	        return requestEntities.stream().map(this::convertEntityToPojo).collect(Collectors.toList());
	    }

	    // Get requests by service provider and status ID
	    @Override
	    public List<RequestPojo> getRequestsByServiceProviderAndStatus(int spId, int statusId) {
	        ServiceProviderEntity spEntity = serviceProviderDao.findById(spId).orElse(null); // No exception handling
	        StatusEntity statusEntity = statusDao.findById(statusId).orElse(null); // No exception handling
	        List<RequestEntity> requestEntities = requestDao.findByServiceProviderEntityAndStatusEntity(spEntity, statusEntity);
	        return requestEntities.stream().map(this::convertEntityToPojo).collect(Collectors.toList());
	    }

	    // Update only the status of the request
	    @Override
	    public RequestPojo updateRequestStatus(int requestId, int statusId) {
	        Optional<RequestEntity> requestEntityOpt = requestDao.findById(requestId);
	        if (requestEntityOpt.isPresent()) {
	            RequestEntity requestEntity = requestEntityOpt.get();
	            StatusEntity statusEntity = statusDao.findById(statusId).orElse(null); // No exception handling
	            requestEntity.setStatusEntity(statusEntity);
	            RequestEntity updatedRequest = requestDao.save(requestEntity);
	            return convertEntityToPojo(updatedRequest);
	        }
	        return null; // No exception handling
	    }
	}

	
	
	
	
	
	
	
	
	package com.example.controller;

	import com.example.pojo.RequestPojo;
	import com.example.service.RequestService;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;

	import java.util.List;

	@RestController
	@RequestMapping("/api/requests")
	public class RequestController {

	    @Autowired
	    private RequestService requestService;

	    // Add a new request
	    @PostMapping
	    public ResponseEntity<RequestPojo> addRequest(@RequestBody RequestPojo requestPojo) {
	        RequestPojo savedRequest = requestService.addRequest(requestPojo);
	        return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
	    }

	    // Fetch all requests
	    @GetMapping
	    public ResponseEntity<List<RequestPojo>> fetchAllRequests() {
	        List<RequestPojo> requests = requestService.fetchAllRequests();
	        return new ResponseEntity<>(requests, HttpStatus.OK);
	    }

	    // Fetch request by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<RequestPojo> fetchRequestById(@PathVariable("id") int id) {
	        RequestPojo request = requestService.fetchRequestById(id);
	        return new ResponseEntity<>(request, HttpStatus.OK);
	    }

	    // Update request
	    @PutMapping("/{id}")
	    public ResponseEntity<RequestPojo> updateRequest(@PathVariable("id") int id, @RequestBody RequestPojo requestPojo) {
	        RequestPojo updatedRequest = requestService.updateRequest(id, requestPojo);
	        return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
	    }

	    // Get requests by client
	    @GetMapping("/client/{clientId}")
	    public ResponseEntity<List<RequestPojo>> getRequestsByClient(@PathVariable("clientId") int clientId) {
	        List<RequestPojo> requests = requestService.getRequestsByClient(clientId);
	        return new ResponseEntity<>(requests, HttpStatus.OK);
	    }

	    // Get requests by service provider
	    @GetMapping("/service-provider/{serviceProviderId}")
	    public ResponseEntity<List<RequestPojo>> getRequestsByServiceProvider(@PathVariable("serviceProviderId") int serviceProviderId) {
	        List<RequestPojo> requests = requestService.getRequestsByServiceProvider(serviceProviderId);
	        return new ResponseEntity<>(requests, HttpStatus.OK);
	    }

	    // Get requests by client and status
	    @GetMapping("/client/{clientId}/status/{statusId}")
	    public ResponseEntity<List<RequestPojo>> getRequestsByClientAndStatus(@PathVariable("clientId") int clientId,
	                                                                           @PathVariable("statusId") int statusId) {
	        List<RequestPojo> requests = requestService.getRequestsByClientAndStatus(clientId, statusId);
	        return new ResponseEntity<>(requests, HttpStatus.OK);
	    }

	    // Get requests by service provider and status
	    @GetMapping("/service-provider/{serviceProviderId}/status/{statusId}")
	    public ResponseEntity<List<RequestPojo>> getRequestsByServiceProviderAndStatus(@PathVariable("serviceProviderId") int serviceProviderId,
	                                                                                   @PathVariable("statusId") int statusId) {
	        List<RequestPojo> requests = requestService.getRequestsByServiceProviderAndStatus(serviceProviderId, statusId);
	        return new ResponseEntity<>(requests, HttpStatus.OK);
	    }

	    // Update request status
	    @PatchMapping("/{id}/status")
	    public ResponseEntity<RequestPojo> updateRequestStatus(@PathVariable("id") int id, @RequestParam("statusId") int statusId) {
	        RequestPojo updatedRequest = requestService.updateRequestStatus(id, statusId);
	        return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
	    }
	}

	
	
	
	
	