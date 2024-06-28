package BEANS;

import BEANS.Application.Validations;
import Foxconn.System.EJB.EJBaccessLocal;
import Foxconn.System.EJB.EJBbadgeLocal;
import Foxconn.System.EJB.EJBcredentialLocal;
import Foxconn.System.EJB.EJBemployeeLocal;
import Foxconn.System.EJB.EJBfacialLocal;
import Foxconn.System.EJB.EJBrfidLocal;
import Foxconn.System.EJB.EJBroleLocal;
import Foxconn.System.ENTITY.Access;
import Foxconn.System.ENTITY.Badge;
import Foxconn.System.ENTITY.Employee;
import Foxconn.System.ENTITY.Facial;
import Foxconn.System.ENTITY.PermissionAccess;
import Foxconn.System.ENTITY.Rfid;
import Foxconn.System.ENTITY.Role;
import java.io.Serializable;
import java.util.List;
import java.util.Base64;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.UnselectEvent;
import Foxconn.System.EJB.EJBpermissionAccessLocal;
import Foxconn.System.ENTITY.Credential;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Zacarias_Mercado
 */
@Named(value = "BEANmain")
@SessionScoped
public class BEANmain implements Serializable {

    private static final long serialVersionUID = 1L;

//-------------------------------------ENTERPRISE BEANS------------------------------------  
    @EJB
    private EJBrfidLocal ejbRfid;

    @EJB
    private EJBbadgeLocal ejbBadge;

    @EJB
    private EJBemployeeLocal ejbEmployee;

    @EJB
    private EJBaccessLocal ejbAccess;

    @EJB
    private EJBfacialLocal ejbFacial;

    @EJB
    private EJBroleLocal ejbRole;

    @EJB
    private EJBpermissionAccessLocal ejbPermissionAccess;

    @EJB
    private EJBcredentialLocal ejbCredential;

    public BEANmain() {
    }

    //-------------------------------------GENERAL VARIABLES------------------------------------
    private String tittle;
    private String imgTop;
    private String navegationBar;

    private boolean showAccess = false;
    private boolean showPermissionAccess;
    private boolean showRole;
    private boolean showBadge;
    private boolean showRFID;
    private boolean showFacial;
    private boolean showEmployee;

    private boolean showDeleteAccess;
    private boolean showDeletePermission;
    private boolean showDeleteRole;
    private boolean showDeleteBadge;
    private boolean showDeleteRFID;
    private boolean showDeleteFacial;
    private boolean showValidationFacial;
    private boolean showValidationBadge;
    private boolean showValidationRFID;

    private String selectedOption;

    private String[] accessArray;
    private List<Access> selectedAccess;

    private List<Employee> employeeListView;

    private String selectedBadge;
    private String selectedBadgeValidation;

    private String selectedRfid;

    private String selectedRole;

    private Employee selectedEmployeeTag;

    private UploadedFile profilePictureFile;
    private byte[] photoBytes;

    //-------------------------------------INSTANCES VARIABLES------------------------------------
    private Badge badge = new Badge();

    private Rfid rfid = new Rfid();

    private Employee employee = new Employee();

    private Access access = new Access();

    private Credential credential = new Credential();

    private Facial facial = new Facial();

    private Role role = new Role();

    private PermissionAccess permissionAccess = new PermissionAccess();

    private Messages msg = new Messages();

    private Select selectClass = new Select();

    private Validations validationClass = new Validations();

    //-------------------------------------LINKS------------------------------------
    public String tableViewIndex() {
        return "/Templates/TableView/TableViewIndex.xhtml";
    }

    public String formViewIndex() {
        return "/Templates/FormView/FormViewIndex.xhtml";
    }

    public String index() {
        return "/index.xhtml";
    }

    public String img() {
        return "/img";
    }

    public void onMenuSelect() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (selectedOption != null) {
            if (selectedOption.equals("logout")) {

                closeSession();
            } else {
                ConfigurableNavigationHandler navHandler = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
                navHandler.performNavigation(selectedOption);
            }
        }
    }

    public void closeSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.invalidate();

        try {
            System.out.println(externalContext.getRequestContextPath());
            externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String logout() {
        return "/login.xhtml";
    }

    //-------------------------------------- Prepare To Employee ------------------------------------
    //      Preparar un nuevo registro
    public void prepareNewEmployee() {

        employee = new Employee();
    }

    //      Preparar para editar registro
    public void prepareEditEmployee(Employee b) {

        employee = b;
    }

    //      Preparar para eliminar registro
    public void prepareDeleteEmployee(Employee b) {
        employee = b;
    }

//-------------------------------------- Prepare To ACCESS ------------------------------------
    //      Prepare a new register
    public void prepareNewAccess() {

        tittle = "Nuevo Acceso";
        imgTop = "img/acceso.png";

        showAccess = true;
        showDeleteAccess = false;
        showEmployee = false;
        access = new Access();
    }

    //      Prepare to edit register
    public void prepareEditAccess(Access a) {

        tittle = "Editar Acceso";
        imgTop = "img/acceso.png";
        showAccess = true;
        showDeleteAccess = false;
        showEmployee = false;
        access = a;
    }

    //      Prepare to delete register
    public void prepareDeleteAccess(Access a) {

        tittle = "Eliminar Acceso";
        imgTop = "img/acceso.png";
        showDeleteAccess = true;
        showEmployee = false;
        access = a;
    }

//-------------------------------------- Prepare To ROLE ------------------------------------
    //      Prepare a new register
    public void prepareNewRole() {
        tittle = "Nuevo Rol";
        imgTop = "img/role.png";

        showRole = true;
        showAccess = false;
        showDeleteRole = false;
        showEmployee = false;
        role = new Role();
    }

    //      Prepare to edit register
    public void prepareEditRole(Role r) {

        tittle = "Editar Rol";
        imgTop = "img/role.png";
        showRole = true;

        showDeleteRole = false;
        showEmployee = false;
        role = r;
    }

    //      Prepare to delete register
    public void prepareDeleteRole(Role r) {

        tittle = "Eliminar Rol";
        imgTop = "img/role.png";
        showDeleteRole = true;
        showRole = false;
        showEmployee = false;
        role = r;
    }

//-------------------------------------- Prepare To PERMISSION ACCESS ------------------------------------
    //      Prepare a new register
    public void prepareNewPermissionAccess() {

        showPermissionAccess = true;
        showAccess = false;
        showDeleteAccess = false;
        showEmployee = false;
        permissionAccess = new PermissionAccess();
    }

    //      Prepare to edit register
    public void prepareEditPermissionAccess(PermissionAccess p) {

        tittle = "Editar permiso de acceso";
        imgTop = "img/permisos.png";
        showPermissionAccess = true;
        showAccess = false;
        showEmployee = false;
        permissionAccess = p;
    }

    //      Prepare to delete register
    public void prepareDeletePermissionAccess(PermissionAccess p) {

        tittle = "Eliminar permiso de acceso";
        imgTop = "img/permisos.png";
        showDeletePermission = true;
        showAccess = false;
        showEmployee = false;
        permissionAccess = p;
    }

//-------------------------------------- Prepare To Validation------------------------------------
    //      Prepare to Validation Badge Button
    public void prepareValidationBadge() {
        tittle = "Validacion Gafete";
        imgTop = "img/gafete.png";
        showDeleteBadge = false;
        showAccess = false;
        showBadge = false;
        showValidationBadge = true;
        clearSelectedEmployeeTag();
    }

    //      Prepare to Validation RFID Button
    public void prepareValidationRfid() {
        tittle = "Validacion RFID";
        imgTop = "img/chip_rfid.png";
        showAccess = false;
        showDeleteRFID = false;
        showRFID = false;
        showValidationRFID = true;
    }

    //      Prepare to Validation Facial Button
    public void prepareValidationFacial() {
        tittle = "Validacion Facial";
        imgTop = "img/reconocimiento_facial.png";
        showDeleteFacial = false;
        showFacial = false;
        showAccess = false;
        showValidationFacial = true;
    }

    //--------------------------------------CRUD Employee------------------------------------
//      METHOD ADD EMPLOYEE
    public String addEmployee() {

        ejbEmployee.ADD(employee);  //Se agrega el empleado desde el BEAN
        employee = new Employee();

        System.out.println("Empleado ingresado correctamente:" + ejbEmployee.EmployeeList().get(ejbEmployee.EmployeeList().size() - 1));
        return index();
    }

    //      METHOD GET ALL EMPLOYEE'S TAGS    
    public List<String> tagList() {
        List<String> tagsList = new ArrayList<>();  //Create a new list to tags
        for (Employee emp : employeeListBEAN()) {   //Iterator to get all Tags of Employees & add it to tag list
            tagsList.add(emp.getTagID());   //Add each tag of employee list
        }
        return tagsList;    //Return a list with all tags
    }

    //      METHOD GET ALL EMPLOYEE'S NUMBER    
    public List<String> employeeNumberList() {
        List<String> enList = new ArrayList<>();  //Create a new list to employee number
        for (Employee en : employeeListBEAN()) {   //Iterator to get all Tags of Employees & add it to employee number list
            enList.add(en.getEmployeeNumber());   //Add each employee number of employee list
        }
        return enList;    //Return a list with all employee number
    }

    //      METHOD GET ALL EMPLOYEE'S PHONE NUMBER    
    public List<String> phoneNumberList() {
        List<String> phoneList = new ArrayList<>();  //Create a new list to phone number
        for (Employee en : employeeListBEAN()) {   //Iterator to get all Tags of Employees & add it to phone number list
            phoneList.add(en.getPhoneNumber());   //Add each phone number of employee list
        }
        return phoneList;    //Return a list with all phone number
    }

    //      METHOD GET ALL EMPLOYEE'S RFID    
    public List<String> rfidList() {
        List<String> rfidList = new ArrayList<>();  //Create a new list to rfid
        for (Employee en : employeeListBEAN()) {   //Iterator to get all Tags of Employees & add it to rfid list
            rfidList.add(en.getRfid());   //Add each rfid of employee list
        }
        return rfidList;    //Return a list with all rfid
    }

    //      METHOD GET ALL EMPLOYEE'S EMAIL    
    public List<String> emailList() {
        List<String> emList = new ArrayList<>();  //Create a new list to tags
        for (Employee en : employeeListBEAN()) {   //Iterator to get all Tags of Employees & add it to tag list
            emList.add(en.getEmail());   //Add each tag of employee list
        }
        return emList;    //Return a list with all tags
    }

    // METHOD TO GET CURRENT ROLE BY ID
    public Role findRoleById(String currentRole) {
        return ejbRole.searchByRole(currentRole);
    }

    public void clearSelectedEmployeeTag() {
        employee = null;
        selectedEmployeeTag = null;
        employee = new Employee();
        selectedEmployeeTag = new Employee();

        System.out.println("Seleccion: " + selectClass.getBadgeSelected());
        selectClass.setBadgeSelected(null);
        System.out.println("Nuevo valor " + selectClass.getBadgeSelected());
    }

    //  METHOD TO GET EMPLOYEE BY TAG ID
    public Employee findEmployeeByTagId(String currentTag) {
        System.out.println("Current Tag: " + currentTag);

        try {
            Employee emp = ejbEmployee.searchByTagId(currentTag);

            System.out.println("Employee validation result: " + emp);
            if (!emp.toString().equals("")) {
                showValidationBadge = true;
                selectedEmployeeTag = emp;
                System.out.println("Gafete aceptado en BEAN: " + selectedEmployeeTag.getName());
                return selectedEmployeeTag;
            } else {
                System.out.println("Empleado no encontrado");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar empleado: " + e.getMessage());
            selectedEmployeeTag = null;
            return null;
        }
    }

    // METHOD TO VALIDATE IF TAG EXISTS
    public boolean validateBadgeBean(String tag) {

        if (tag == null || tag.isEmpty() || tag.equals("")) {
            return false;
        } else {
            selectedEmployeeTag = findEmployeeByTagId(tag);
            return ejbEmployee.validateBadgeEJB(tag);
        }

    }

    //METHOD TO PERSISTENCE A EMPLOYEE INTO DATABASE
    public void createEmployee() throws IOException {
        // String pathFolder = "C:\\Users\\Zacarias_Mercado\\Documents\\NetBeansProjects\\Multifactor-Access\\Multifactor-Access-war\\web\\resources\\images"; //Folder to save the images to facial picture

        Role roleSelected = findRoleById(selectedRole); //Variable to get a current selected employee by id

        if (tagList().contains(employee.getTagID()) && (employee.getTagID() != null && !employee.getTagID().isEmpty())) { //Validation if the employee id is already created
            msg.tagIdExists(employee.getTagID());
            employee.setTagID(null);
            System.out.println(validationClass.isResultEmployeeForm());
            validationClass.setResultEmployeeForm(Boolean.FALSE);
            validationClass.setResultEmployeeForm(false);

        } else if (employeeNumberList().contains(employee.getEmployeeNumber()) && (employee.getEmployeeNumber() != null && !employee.getEmployeeNumber().isEmpty())) {
            msg.employeeNumberExists(employee.getEmployeeNumber());
            employee.setEmployeeNumber(null);
            validationClass.setResultEmployeeForm(false);

        } else if (emailList().contains(employee.getEmail()) && (employee.getEmail() != null && !employee.getEmail().isEmpty())) {
            msg.emailExists(employee.getEmail());
            employee.setEmail(null);
            validationClass.setResultEmployeeForm(false);

        } else if (phoneNumberList().contains(employee.getPhoneNumber()) && (employee.getPhoneNumber() != null && !employee.getPhoneNumber().isEmpty())) {
            msg.phoneNumberExists(employee.getPhoneNumber());
            employee.setPhoneNumber(null);
            validationClass.setResultEmployeeForm(false);

        } else if (rfidList().contains(employee.getRfid()) && (employee.getRfid() != null && !employee.getRfid().isEmpty())) {
            msg.rfidExists(employee.getRfid());
            employee.setRfid(null);
            validationClass.setResultEmployeeForm(false);
        } else {
            employee.setTbRoleId(roleSelected);
            employee.setBadgeList(null);
            employee.setFacialList(null);
            employee.setPermissionAssetList(null);
            employee.setRfidList(null);
            msg.acceptForm(employee.getName() + " " + employee.getFirstLastName() + " " + employee.getSecondLastName());
            validationClass.setResultEmployeeForm(true);

            System.out.println(employee.getEmployeeNumber());

            upload(employee.getEmployeeNumber());

            if (profilePictureFile != null) {
                employee.setPhoto(photoBytes);
                System.out.println("Tamanio de foto: " + photoBytes.length);
                FacesMessage message = new FacesMessage("Foto subida con exito");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

            credential.setTbEmployeeId(employee);
            credential.setPassword("a");

            ejbEmployee.ADD(employee);

            ejbCredential.ADD(credential);
            employee = new Employee();
            credential = new Credential();

        }

    }

    //   METHOD EDIT EMPLOYEE
    public String editEmployee() {
        ejbEmployee.EDIT(employee);
        employee = new Employee();
        return tableViewIndex();
    }

    //   METHOD DELETE EMPLOYEE   
    public String deleteEmployee() {
        ejbEmployee.REMOVE(employee);
        employee = new Employee();
        return tableViewIndex();
    }

    //--------------------------------------CRUD ACCESS------------------------------------
//      METHOD ADD ACCESS
    public String addAccess() {
        ejbAccess.ADD(access);
        access = new Access();
        return tableViewIndex();
    }

    //    METHOD EDIT ACCESS
    public String editAccess() {
        ejbAccess.EDIT(access);
        access = new Access();
        return tableViewIndex();
    }

    //    METHOD DELETE ACCESS  
    public String deleteAccess() {
        ejbAccess.REMOVE(access);
        access = new Access();
        return tableViewIndex();
    }

    //    METHOD LAST ROLE
    public Role lastRole() {
        return ejbRole.roleList().get(roleListBEAN().size() - 1);
    }

    public String addRoleAccess(List<Access> accessList, List<String> selectedAccesses) {
        Role rol = lastRole();

        List<Access> listAccessTemp = new ArrayList<>();
        int idTemp;
//        selectedAccesses.forEach((accessIterator) -> {
//            listAccessTemp.add(ejbAccess.accessGetByArea(accessIterator));
//        });

        for (String accessIterator : selectedAccesses) {
            listAccessTemp.add(ejbAccess.accessGetByArea(accessIterator));
        }

        try {
            if (rol != null && accessList != null) {
                for (Access selectedAcc : listAccessTemp) {
                    for (Access currentAccess : accessList) {
//                        System.out.println("Access selected: " + selectedAcc);
//                        System.out.println("Current access: " + currentAccess);
//                        System.out.println("Permission Access list size: " + permissionAccessList().size());

                        if (currentAccess.equals(selectedAcc)) {
                            idTemp = permissionAccessList().size() + 1;

                            PermissionAccess permissionAccessTemp = new PermissionAccess();

                            permissionAccessTemp.setTbRoleId(rol);
                            permissionAccessTemp.setTbAccessId(currentAccess);
                            ejbPermissionAccess.ADD(permissionAccessTemp);
                            break;  // Si se encuentra, no es necesario seguir buscando
                        }
                    }

                }

                return index();
            }
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                System.out.println("Violación de restricción: " + violation.getMessage());
                // Puedes hacer más con la información de la violación, como registrarla, mostrar mensajes al usuario, etc.
            }
            return index();
        }
        return "Error metodo agregar rol con acceso";

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.selectedAccess);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BEANmain other = (BEANmain) obj;
        if (!Objects.equals(this.access, other.access)) {
            return false;
        }
        return true;
    }

    public void addRole() {
        ejbRole.ADD(role);
        role = new Role();

        //showPermissionAccess = true;
    }

//    METHOD EDIT ROLE
    public String editRole() {
        ejbRole.EDIT(role);
        role = new Role();
        return tableViewIndex();
    }

    //    METHOD DELETE ROLE  
    public String deleteRole() {
        ejbRole.REMOVE(role);
        role = new Role();
        return tableViewIndex();
    }

    //--------------------------------------CRUD PERMISSION ACCESS------------------------------------
//      METHOD ADD PERMISSION ACCESS
    public String addPermissionAccess(Role r, Access acc) {
        ejbRole.ADD(role);
        role = new Role();

        ejbPermissionAccess.ADD(permissionAccess);
        permissionAccess = new PermissionAccess();
        return index();
    }

    //    METHOD EDIT PERMISSION
    public String editPermissionAccess() {
        ejbPermissionAccess.EDIT(permissionAccess);
        permissionAccess = new PermissionAccess();
        return tableViewIndex();
    }

    //    METHOD DELETE PERMISSION  
    public String deletePermissionAccess() {

        ejbPermissionAccess.REMOVE(permissionAccess);
        permissionAccess = new PermissionAccess();

        return tableViewIndex();
    }

//-------------------------------------- Show LIsts ------------------------------------
    //      METHOD GET ALL BADGE USERS
    public List<Employee> badgeListBEAN() {
        tittle = "Identificacion por Gafete";
        imgTop = "img/gafete.png";

        navegationBar = "Lista Gafete";

        showBadge = true;
        showRFID = false;
        showFacial = false;
        showAccess = false;
        showEmployee = false;

        showRole = false;
        showDeleteRole = false;

        showPermissionAccess = false;
        showDeletePermission = false;

        showDeleteBadge = false;
        showDeleteRFID = false;
        showDeleteFacial = false;

        showValidationFacial = false;
        showValidationRFID = false;
        showValidationBadge = false;

        return ejbEmployee.EmployeeList();
    }

//      METHOD GET ALL RFID  USERS 
    public List<Employee> rfidListBEAN() {
        tittle = "Identificacion por RFID";
        imgTop = "img/chip_rfid.png";

        navegationBar = "Lista RFID";

        showBadge = false;
        showRFID = true;
        showFacial = false;
        showAccess = false;
        showEmployee = false;

        showRole = false;
        showDeleteRole = false;

        showPermissionAccess = false;
        showDeletePermission = false;

        showDeleteBadge = false;
        showDeleteRFID = false;
        showDeleteFacial = false;

        showValidationFacial = false;
        showValidationRFID = false;
        showValidationBadge = false;
        return ejbEmployee.EmployeeList();
    }

//        ARRAY GET ALL ACCESS USER ARRAY TO SELECTMANYMENU
    public void accessArrayListBEAN() {

        accessArray = new String[accessListBEAN().size()];

        for (int i = 0; i < accessListBEAN().size(); i++) {
            accessArray[i] = String.valueOf(accessListBEAN().get(i).getArea());
        }
    }

    private List<Employee> listBadge;

    @PostConstruct
    public void init() {
        badgeListBEAN();
    }

    public Integer badgeID(int id) {

        return ejbBadge.searchForID(id);
    }

    public void accessOnItemUnselect(UnselectEvent event) {
        FacesMessage msgs = new FacesMessage();
        msgs.setSummary("Item unselected: " + event.getObject().toString());
        msgs.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext.getCurrentInstance().addMessage(null, msgs);
    }

    //METHOD TO UPLOAD A PROFILE PICTURE
    public void upload(String user) throws IOException {

        if (profilePictureFile != null) {
            try (InputStream in = profilePictureFile.getInputStream()) {
                photoBytes = toByteArray(in);
                System.out.println("Photo bytes length: " + photoBytes.length);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //METHOD TO CONVERT PROFILE PICTURE IMAGE TO BYTES INTO ARRAY
    private byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = input.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    // METHOD CONVERT BYTES TO BASE64
    public String getBase64Image(byte[] imageBytes) {
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    // Método para obtener la imagen en Base64 de un empleado
    public String getEmployeeImage(Employee empleado) {
        if (empleado != null && empleado.getPhoto() != null) {
            return "data:image/png;base64," + getBase64Image(empleado.getPhoto());
        }
        return null;
    }

//        LIST GET ALL ACCESS USER
    public List<Access> accessListBEAN() {

        if (showEmployee == false && showBadge == false && showRFID == false && showRole == false) {

            tittle = "Accesos";
            imgTop = "img/acceso.png";

            navegationBar = "Lista Accesos";

            showAccess = true;
            showBadge = false;
            showRFID = false;
            showFacial = false;
            showEmployee = false;

            showRole = false;
            showDeleteRole = false;

            showPermissionAccess = false;
            showDeletePermission = false;

            showDeleteBadge = false;
            showDeleteRFID = false;
            showDeleteFacial = false;

            showValidationFacial = false;
            showValidationRFID = false;
            showValidationBadge = false;

            return ejbAccess.accessList();
        }
        return ejbAccess.accessList();
    }

//      METHOD GET ALL ROLE USERS
    public List<Role> roleListBEAN() {

        if (showEmployee == false && showBadge == false && showRFID == false) {

            tittle = "Roles";
            imgTop = "img/role.png";

            navegationBar = "Lista Roles";

            showRole = true;
            showAccess = false;
            showBadge = false;
            showRFID = false;
            showFacial = false;
            showEmployee = false;

            showPermissionAccess = false;
            showDeletePermission = false;
            showDeleteRole = false;

            showDeleteBadge = false;
            showDeleteRFID = false;
            showDeleteFacial = false;

            showValidationFacial = false;
            showValidationRFID = false;
            showValidationBadge = false;

            return ejbRole.roleList();
        }
        return ejbRole.roleList();
    }

    public void createPermission(Role r, PermissionAccess p) {

        r.getPermissionAccessList().add(p);

    }

    public List<PermissionAccess> permissionAccessList() {

        return ejbPermissionAccess.permissionAccessList();
    }

    public String permissionAccessList(PermissionAccess p) {

        int id = p.getId();

        PermissionAccess permissionFound = (PermissionAccess) ejbPermissionAccess.searchByID(id);

        return null;
    }

//      METHOD GET ALL EMPLOYEE USERS
    public List<Employee> employeeListBEAN() {
        //tittle = "Empleado";
        //imgTop = "img/reconocimiento_facial.png";

        //navegationBar = "Lista Facial";
        //showAccess = false;
        //showBadge = false;
        //showRFID = false;
        showBadge = false;
        showRFID = false;
        showFacial = false;
        showAccess = false;
        showEmployee = true;

        showRole = false;
        showDeleteRole = false;

        showPermissionAccess = false;
        showDeletePermission = false;

        showDeleteBadge = false;
        showDeleteRFID = false;
        showDeleteFacial = false;

        showValidationFacial = false;
        showValidationRFID = false;
        showValidationBadge = false;
        return ejbEmployee.EmployeeList();
    }

//      METHOD GET ALL FACIAL USERS     
    public List<Employee> facialListBEAN() {
        tittle = "Reconocimiento Facial";
        imgTop = "img/reconocimiento_facial.png";

        navegationBar = "Lista Facial";

        showBadge = false;
        showRFID = false;
        showFacial = true;
        showAccess = false;

        showRole = false;
        showDeleteRole = false;

        showEmployee = false;

        showPermissionAccess = false;
        showDeletePermission = false;

        showDeleteBadge = false;
        showDeleteRFID = false;
        showDeleteFacial = false;

        showValidationFacial = false;
        showValidationRFID = false;
        showValidationBadge = false;

        return ejbEmployee.EmployeeList();
    }

//--------------------------------------AJAX COMPONENTS-------------------------    
    //REGEX EXPRESSION TO VALIDATION
    //------------------------------------ DATA VALIDATION ------------------------------------
    public boolean viewValidation(String t) {

        switch (t) {

//-----------------------BADGE--------------------------------------------------
//            VIEW BADGE
            case "Identificacion por Gafete":

                imgTop = "img/gafete.png";
                navegationBar = "Lista Gafete";

                showBadge = true;
                showRFID = false;
                showFacial = false;
                showAccess = false;
                showEmployee = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showBadge;

//                NEW BADGE
            case "Nuevo Gafete":

                showBadge = true;
                showFacial = false;
                showRFID = false;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                imgTop = "img/gafete.png";

                return showBadge;

//                EDIT BADGE
            case "Editar Gafete":
                imgTop = "img/gafete.png";

                showBadge = true;
                showRFID = false;
                showFacial = false;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showBadge;

//                DELETE BADGE
            case "Eliminar Gafete":
                imgTop = "img/gafete.png";

                showBadge = false;
                showRFID = false;
                showFacial = false;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = true;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showDeleteBadge;

//                VALIDATION BADGE
            case "Validacion Gafete":
                imgTop = "img/gafete.png";

                showBadge = false;
                showRFID = false;
                showFacial = false;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = true;

                return showValidationBadge;

//-----------------------END BADGE----------------------------------------------
//-----------------------RFID---------------------------------------------------              
//                VIEW RFID
            case "Identificacion por RFID":

                imgTop = "img/chip_rfid.png";
                navegationBar = "Lista RFID";

                showBadge = false;
                showRFID = true;
                showFacial = false;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showRFID;

//                NEW RFID
            case "Nuevo RFID":

                showBadge = false;
                showRFID = true;
                showFacial = false;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                imgTop = "img/chip_rfid.png";

                return showRFID;

//                EDIT RFID
            case "Editar RFID":
                imgTop = "img/chip_rfid.png";

                showBadge = false;
                showFacial = false;
                showRFID = true;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showRFID;

//                DELETE RFID
            case "Eliminar RFID":
                imgTop = "img/chip_rfid.png";

                showBadge = false;
                showRFID = false;
                showFacial = false;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = true;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showDeleteRFID;

//-----------------------FACIAL---------------------------------------------------               
//                VIEW FACIAL
            case "Reconocimiento Facial":

                imgTop = "img/reconocimiento_facial.png";
                navegationBar = "Lista Facial";

                showBadge = false;
                showRFID = false;
                showFacial = true;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showFacial;

//                NEW FACIAL
            case "Nuevo Facial":

                showBadge = false;
                showRFID = false;
                showFacial = true;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                imgTop = "img/reconocimiento_facial.png";

                return showFacial;

//                EDIT FACIAL 
            case "Editar Facial":
                imgTop = "img/reconocimiento_facial.png";

                showBadge = false;
                showRFID = false;
                showFacial = true;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showFacial;

//                DELETE FACIAL
            case "Eliminar Facial":
                imgTop = "img/reconocimiento_facial.png";

                showBadge = false;
                showRFID = false;
                showFacial = false;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = true;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showDeleteFacial;

//-----------------------END FACIAL---------------------------------------------------                               
//-----------------------ACCESS--------------------------------------------------                               
//                NEW ACCESS
            case "Nuevo Acceso":

                showBadge = false;
                showFacial = false;
                showRFID = false;
                showAccess = true;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                imgTop = "img/acceso.png";

                return showAccess;

//                EDIT ACCESS
            case "Editar Acceso":
                imgTop = "img/acceso.png";

                showBadge = false;
                showFacial = false;
                showRFID = false;
                showAccess = true;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showAccess;

//                DELETE ACCESS
            case "Eliminar Acceso":
                imgTop = "img/acceso.png";

                showBadge = false;
                showFacial = false;
                showRFID = false;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = true;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showDeleteAccess;

//----------------------END ACCESS----------------------------------------------                               
//-----------------------PERMISSION ACCESS--------------------------------------                               
//                NEW PERMISSION ACCESS
            case "Nuevo permiso de acceso":

                showBadge = false;
                showFacial = false;
                showRFID = false;
                showAccess = false;

                showPermissionAccess = true;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                imgTop = "img/permisos.png";

                return showPermissionAccess;

//                EDIT PERMISSION ACCESS
            case "Editar permiso de acceso":
                imgTop = "img/permisos.png";

                showBadge = false;
                showFacial = false;
                showRFID = false;
                showAccess = false;

                showPermissionAccess = true;
                showDeletePermission = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showPermissionAccess;

//                DELETE PERMISSION ACCESS
            case "Eliminar permiso de acceso":
                imgTop = "img/permisos.png";

                showBadge = false;
                showFacial = false;
                showRFID = false;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = true;

                showRole = false;
                showDeleteRole = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = true;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showDeletePermission;

//-----------------------END PERMISSION ACCESS----------------------------------
//-----------------------ROLE---------------------------------------------------    
//                NEW ROLE 
            case "Nuevo Rol":

                showBadge = false;
                showFacial = false;
                showRFID = false;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showRole = true;
                showDeleteRole = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                imgTop = "img/role.png";

                return showRole;

//                EDIT ROLE
            case "Editar Rol":
                imgTop = "img/role.png";

                showBadge = false;
                showFacial = false;
                showRFID = false;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showRole = true;
                showDeleteRole = false;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showRole;

//                DELETE ROLE
            case "Eliminar Rol":
                imgTop = "img/role.png";

                showBadge = false;
                showFacial = false;
                showRFID = false;
                showAccess = false;

                showPermissionAccess = false;
                showDeletePermission = false;

                showRole = false;
                showDeleteRole = true;

                showDeleteBadge = false;
                showDeleteRFID = false;
                showDeleteFacial = false;
                showDeleteAccess = false;

                showValidationFacial = false;
                showValidationRFID = false;
                showValidationBadge = false;

                return showDeleteRole;

//-----------------------END ROLE-----------------------------------------------     
//----------------------- VALIDATIONS ------------------------------------------                               
            case "Validacion de empleado":
                imgTop = "img/gafete.png";

                return true;

//----------------------- END VALIDATIONS --------------------------------------                               
            default:
                return false;
        }

    }

    public boolean validationTagBadge(String tagId) {
        return ejbBadge.validateBadgeEJB(tagId);
    }

    //------------------------GETTERS & SETTERS------------------------------------
    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getImgTop() {
        return imgTop;
    }

    public void setImgTop(String imgTop) {
        this.imgTop = imgTop;
    }

    public String getNavegationBar() {
        return navegationBar;
    }

    public void setNavegationBar(String navegationBar) {
        this.navegationBar = navegationBar;
    }

    public Rfid getRfid() {
        return rfid;
    }

    public void setRfid(Rfid rfid) {
        this.rfid = rfid;
    }

    public boolean isShowAccess() {
        return showAccess;
    }

    public void setShowAccess(boolean showAccess) {
        this.showAccess = showAccess;
    }

    public boolean isShowBadge() {
        return showBadge;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setShowBadge(boolean showBadge) {
        this.showBadge = showBadge;
    }

    public boolean isShowRFID() {
        return showRFID;
    }

    public void setShowRFID(boolean showRFID) {
        this.showRFID = showRFID;
    }

    public EJBrfidLocal getEjbRfid() {
        return ejbRfid;
    }

    public void setEjbRfid(EJBrfidLocal ejbRfid) {
        this.ejbRfid = ejbRfid;
    }

    public EJBbadgeLocal getEjbBadge() {
        return ejbBadge;
    }

    public void setEjbBadge(EJBbadgeLocal ejbBadge) {
        this.ejbBadge = ejbBadge;
    }

    public EJBemployeeLocal getEjbEmployee() {
        return ejbEmployee;
    }

    public void setEjbEmployee(EJBemployeeLocal ejbEmployee) {
        this.ejbEmployee = ejbEmployee;
    }

    public boolean isShowDeleteAccess() {
        return showDeleteAccess;
    }

    public void setShowDeleteAccess(boolean showDeleteAccess) {
        this.showDeleteAccess = showDeleteAccess;
    }

    public boolean isShowDeleteBadge() {
        return showDeleteBadge;
    }

    public void setShowDeleteBadge(boolean showDeleteBadge) {
        this.showDeleteBadge = showDeleteBadge;
    }

    public boolean isShowDeleteRFID() {
        return showDeleteRFID;
    }

    public void setShowDeleteRFID(boolean showDeleteRFID) {
        this.showDeleteRFID = showDeleteRFID;
    }

    public EJBaccessLocal geteJBaccess() {
        return ejbAccess;
    }

    public void seteJBaccess(EJBaccessLocal eJBaccess) {
        this.ejbAccess = eJBaccess;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public EJBaccessLocal getEjbAccess() {
        return ejbAccess;
    }

    public void setEjbAccess(EJBaccessLocal ejbAccess) {
        this.ejbAccess = ejbAccess;
    }

    public boolean isShowFacial() {
        return showFacial;
    }

    public void setShowFacial(boolean showFacial) {
        this.showFacial = showFacial;
    }

    public boolean isShowDeleteFacial() {
        return showDeleteFacial;
    }

    public void setShowDeleteFacial(boolean showDeleteFacial) {
        this.showDeleteFacial = showDeleteFacial;
    }

    public EJBfacialLocal getEjbFacial() {
        return ejbFacial;
    }

    public void setEjbFacial(EJBfacialLocal ejbFacial) {
        this.ejbFacial = ejbFacial;
    }

    public Facial getFacial() {
        return facial;
    }

    public void setFacial(Facial facial) {
        this.facial = facial;
    }

    public boolean isShowValidationFacial() {
        return showValidationFacial;
    }

    public void setShowValidationFacial(boolean showValidationFacial) {
        this.showValidationFacial = showValidationFacial;
    }

    public boolean isShowValidationBadge() {
        return showValidationBadge;
    }

    public void setShowValidationBadge(boolean showValidationBadge) {
        this.showValidationBadge = showValidationBadge;
    }

    public boolean isShowValidationRFID() {
        return showValidationRFID;
    }

    public void setShowValidationRFID(boolean showValidationRFID) {
        this.showValidationRFID = showValidationRFID;
    }

    public EJBpermissionAccessLocal geteJBpermission() {
        return ejbPermissionAccess;
    }

    public void seteJBpermission(EJBpermissionAccessLocal ejbPermission) {
        this.ejbPermissionAccess = ejbPermission;
    }

    public EJBpermissionAccessLocal getEjbPermission() {
        return ejbPermissionAccess;
    }

    public void setEjbPermissionAccess(EJBpermissionAccessLocal ejbPermission) {
        this.ejbPermissionAccess = ejbPermission;
    }

    public boolean isShowPermissionAccess() {
        return showPermissionAccess;
    }

    public void setShowPermissionAccess(boolean showPermissionAccess) {
        this.showPermissionAccess = showPermissionAccess;
    }

    public boolean isShowDeletePermission() {
        return showDeletePermission;
    }

    public void setShowDeletePermission(boolean showDeletePermission) {
        this.showDeletePermission = showDeletePermission;
    }

    public PermissionAccess getPermission() {
        return permissionAccess;
    }

    public void setPermission(PermissionAccess permission) {
        this.permissionAccess = permission;
    }

    public boolean isShowEmployee() {
        return showEmployee;
    }

    public void setShowEmployee(boolean showEmployee) {
        this.showEmployee = showEmployee;
    }

    public String[] getAccessArray() {
        return accessArray;
    }

    public void setAccessArray(String[] accessArray) {
        this.accessArray = accessArray;
    }

    public String getSelectedBadgeValidation() {
        return selectedBadgeValidation;
    }

    public void setSelectedBadgeValidation(String selectedBadgeValidation) {
        this.selectedBadgeValidation = selectedBadgeValidation;
    }

    public EJBroleLocal getEjbRole() {
        return ejbRole;
    }

    public void setEjbRole(EJBroleLocal ejbRole) {
        this.ejbRole = ejbRole;
    }

    public boolean isShowRole() {
        return showRole;
    }

    public void setShowRole(boolean showRole) {
        this.showRole = showRole;
    }

    public List<Access> getSelectedAccess() {
        return selectedAccess;
    }

    public void setSelectedAccess(List<Access> selectedAccess) {
        this.selectedAccess = selectedAccess;
    }

    public boolean isShowDeleteRole() {
        return showDeleteRole;
    }

    public void setShowDeleteRole(boolean showDeleteRole) {
        this.showDeleteRole = showDeleteRole;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UploadedFile getProfilePictureFile() {
        return profilePictureFile;
    }

    public void setProfilePictureFile(UploadedFile profilePictureFile) {
        this.profilePictureFile = profilePictureFile;
    }

    public PermissionAccess getPermissionAccess() {
        return permissionAccess;
    }

    public void setPermissionAccess(PermissionAccess permissionAccess) {
        this.permissionAccess = permissionAccess;
    }

    public String getSelectedRfid() {
        return selectedRfid;
    }

    public void setSelectedRfid(String selectedRfid) {
        this.selectedRfid = selectedRfid;
    }

    public EJBpermissionAccessLocal getEjbPermissionAccess() {
        return ejbPermissionAccess;
    }

    public List<Employee> getEmployeeListView() {
        return employeeListView;
    }

    public void setEmployeeListView(List<Employee> employeeListView) {
        this.employeeListView = employeeListView;
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }

    public Employee getSelectedEmployeeTag() {
        return selectedEmployeeTag;
    }

    public void setSelectedEmployeeTag(Employee selectedEmployeeTag) {
        this.selectedEmployeeTag = selectedEmployeeTag;
    }

    public List<Employee> getListBadge() {
        return listBadge;
    }

    public void setListBadge(List<Employee> listBadge) {
        this.listBadge = listBadge;
    }

    public Messages getMsg() {
        return msg;
    }

    public void setMsg(Messages msg) {
        this.msg = msg;
    }

    public String getSelectedBadge() {
        return selectedBadge;
    }

    public void setSelectedBadge(String selectedBadge) {
        this.selectedBadge = selectedBadge;
    }

    public Select getSelectClass() {
        return selectClass;
    }

    public void setSelectClass(Select selectClass) {
        this.selectClass = selectClass;
    }

    public Validations getValidationsClass() {
        return validationClass;
    }

    public void setValidationsClass(Validations validationsClass) {
        this.validationClass = validationsClass;
    }

    public EJBcredentialLocal getEjbCredential() {
        return ejbCredential;
    }

    public void setEjbCredential(EJBcredentialLocal ejbCredential) {
        this.ejbCredential = ejbCredential;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public Validations getValidationClass() {
        return validationClass;
    }

    public void setValidationClass(Validations validationClass) {
        this.validationClass = validationClass;
    }

    public byte[] getPhotoBytes() {
        return photoBytes;
    }

    public void setPhotoBytes(byte[] photoBytes) {
        this.photoBytes = photoBytes;
    }

}
