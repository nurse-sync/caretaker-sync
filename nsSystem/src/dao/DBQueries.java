package dao;

public class DBQueries {

    // ==> ns_user_info table
    public static final String CREATE_USER = "INSERT INTO ns_user_info (full_name, email, phone_number) VALUES (?, ?, ?)";
    public static final String FETCH_USER_BY_ID = "SELECT * FROM ns_user_info WHERE user_id = ?";
    public static final String FETCH_ALL_USERS = "SELECT * FROM ns_user_info";
    public static final String UPDATE_USER = "UPDATE ns_user_info SET full_name = ?, email = ?, phone_number = ? WHERE user_id = ?";
    public static final String DELETE_USER = "DELETE FROM ns_user_info WHERE user_id = ?";

    
    
    // ==> ns_login table
    public static final String CREATE_LOGIN = "INSERT INTO ns_login (user_id, user_name, password) VALUES (?, ?, ?)";
    public static final String FETCH_BY_USER_ID = "SELECT * FROM ns_login WHERE user_id = ?";
    public static final String UPDATE_LOGIN = "UPDATE ns_login SET user_name = ?, password = ? WHERE user_id = ?";
    public static final String DELETE_LOGIN = "DELETE FROM ns_login WHERE user_id = ?";
    public static final String GET_USER_BY_USERNAME = "SELECT user_id, user_name, password FROM ns_user_info WHERE user_name = ?";

    // check if user exists with the provided username and password 
    public static final String VERIFY_USER = "SELECT COUNT(*) FROM ns_login WHERE user_name = ? AND password = ?";
    
    
    // ==> ns_user_login_role table
    public static final String ASSIGN_ROLE_TO_USER = "INSERT INTO ns_user_login_role (user_id, role_id) VALUES (?, ?)";
    public static final String FETCH_ROLES_BY_USER_ID = "SELECT role_name FROM ns_role r INNER JOIN ns_user_login_role ur ON r.role_id = ur.role_id WHERE ur.user_id = ?";
    public static final String REMOVE_ROLE_FROM_USER = "DELETE FROM ns_user_login_role WHERE user_id = ? AND role_id = ?";

    // ==> ns_address table
	public static final String ADD_ADDRESS = "INSERT INTO ns_address (address_flat_number, address_house_number, address_street_name, address_locality, address_district, address_pincode, address_country) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String FETCH_ADDRESS_BY_ID = "SELECT * FROM ns_address WHERE address_id = ?";
    public static final String FETCH_ALL_ADDRESSES = "SELECT * FROM ns_address";
    public static final String UPDATE_ADDRESS = "UPDATE ns_address SET address_flat_number = ?, address_house_number = ?, address_street_name = ?, address_locality = ?, address_district = ?, address_pincode = ?, address_country = ? WHERE address_id = ?";
    public static final String DELETE_ADDRESS = "DELETE FROM ns_address WHERE address_id = ?";

    // ==> ns_member_data table
    public static final String ADD_MEMBER = "INSERT INTO ns_member_data (full_name, age, gender, identification_url, admin_id, status_id) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String FETCH_MEMBER_BY_ID = "SELECT * FROM ns_member_data WHERE member_id = ?";
    public static final String FETCH_ALL_MEMBERS = "SELECT * FROM ns_member_data";
    public static final String UPDATE_MEMBER = "UPDATE ns_member_data SET full_name = ?, age = ?, gender = ?, identification_url = ?, admin_id = ?, status_id = ? WHERE member_id = ?";
    public static final String DELETE_MEMBER = "DELETE FROM ns_member_data WHERE member_id = ?";

    public static final String UPDATE_MEMBER_STATUS = "UPDATE ns_member_data SET status_id = ? WHERE member_id = ?";

    // ==> ns_nurse_license table
    public static final String ADD_NURSE_LICENSE = "INSERT INTO ns_nurse_license (sp_id, nurse_license_url) VALUES (?, ?)";
    public static final String FETCH_NURSE_LICENSE_BY_ID = "SELECT * FROM ns_nurse_license WHERE nurse_license_id = ?";
    public static final String FETCH_ALL_NURSE_LICENSES = "SELECT * FROM ns_nurse_license";
    public static final String UPDATE_NURSE_LICENSE = "UPDATE ns_nurse_license SET sp_id = ?, nurse_license_url = ? WHERE nurse_license_id = ?";
    public static final String DELETE_NURSE_LICENSE = "DELETE FROM ns_nurse_license WHERE nurse_license_id = ?";

    // ==> ns_qualification table
    public static final String ADD_QUALIFICATION = "INSERT INTO ns_qualification (sp_id, experience_in_years, role_name) VALUES (?, ?, ?)";
    public static final String FETCH_QUALIFICATION_BY_ID = "SELECT * FROM ns_qualification WHERE sp_qualification_id = ?";
    public static final String FETCH_ALL_QUALIFICATIONS = "SELECT * FROM ns_qualification";
    public static final String UPDATE_QUALIFICATION = "UPDATE ns_qualification SET sp_id = ?, experience_in_years = ?, role_name = ? WHERE sp_qualification_id = ?";
    public static final String DELETE_QUALIFICATION = "DELETE FROM ns_qualification WHERE sp_qualification_id = ?";

    // ==> ns_service_provider table
    public static final String CREATE_SERVICE_PROVIDER = "INSERT INTO ns_service_provider (sp_id, gender, category, weekly_salary, available_from, available_to, address_id, qualification_id, live_in, admin_id, status_id, id_proof_url, nurse_license_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String FETCH_SERVICE_PROVIDER_BY_ID = "SELECT * FROM ns_service_provider WHERE sp_id = ?";
    public static final String FETCH_ALL_SERVICE_PROVIDERS = "SELECT * FROM ns_service_provider";
    public static final String UPDATE_SERVICE_PROVIDER = "UPDATE ns_service_provider SET gender = ?, category = ?, weekly_salary = ?, available_from = ?, available_to = ?, address_id = ?, qualification_id = ?, live_in = ?, admin_id = ?, status_id = ?, id_proof_url = ?, nurse_license_id = ? WHERE sp_id = ?";
    public static final String DELETE_SERVICE_PROVIDER = "DELETE FROM ns_service_provider WHERE sp_id = ?";
    public static final String FETCH_SERVICE_PROVIDERS_BY_CATEGORY = "SELECT * FROM ns_service_provider WHERE category = ?";
    public static final String FETCH_SERVICE_PROVIDERS_BY_STATUS = "SELECT * FROM ns_service_provider WHERE status_id = ?";

    public static final String UPDATE_SERVICE_PROVIDER_STATUS = "UPDATE ns_service_provider SET status_id = ? WHERE sp_id = ?";

    // ==> ns_request table
    public static final String CREATE_REQUEST = "INSERT INTO ns_request (client_id, sp_id, address_id, start_date, end_date, member_id, message_to_sp, message_from_sp, status_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String FETCH_REQUEST_BY_ID = "SELECT * FROM ns_request WHERE request_id = ?";
    public static final String FETCH_ALL_REQUESTS = "SELECT * FROM ns_request";
    public static final String UPDATE_REQUEST = "UPDATE ns_request SET client_id = ?, sp_id = ?, address_id = ?, start_date = ?, end_date = ?, member_id = ?, message_to_sp = ?, message_from_sp = ?, status_id = ? WHERE request_id = ?";
    public static final String DELETE_REQUEST = "DELETE FROM ns_request WHERE request_id = ?";
    public static final String FETCH_REQUESTS_BY_CLIENT_ID = "SELECT * FROM ns_request WHERE client_id = ?";
    public static final String FETCH_REQUESTS_BY_SERVICE_PROVIDER_ID = "SELECT * FROM ns_request WHERE sp_id = ?";
    public static final String FETCH_REQUESTS_BY_STATUS = "SELECT * FROM ns_request WHERE status_id = ?";

    public static final String UPDATE_REQUEST_STATUS = "UPDATE ns_request SET status_id = ? WHERE request_id = ?";
    public static final String FETCH_REQUESTS_BY_CLIENT_AND_STATUS = "SELECT * FROM ns_request WHERE client_id = ? AND status_id = ?";
    public static final String FETCH_REQUESTS_BY_SP_AND_STATUS = "SELECT * FROM ns_request WHERE sp_id = ? AND status_id = ?";

}

