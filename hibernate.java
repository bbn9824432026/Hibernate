// HibernateConfiguration
hbm2ddl.auto .
create:create schema , previous data will be deleted
update : update schema with given values
validate : validate schema. it make no changes
create-drop : schema will be schema with delete old data , and drop if sessionfactory is Closed

AUTO:use default strategy , create hibernate_sequence table
IDENTITY: use database generated primary key
SEQUENCE: use database 
TABLE:use database table to track primary key

//EntityClass

@Entity
@Table(name="employee_table")
@DynamicUpdate 
// only fire modified changes in update query

@Id
@Column(name="employee_id",length=100,nullable=false,unique=true)
@GeneratedValue(strategy=GenerationType.AUTO , generator="reference")
@SequenceGenerator(name="reference" , initialValue=1 ,allocationSize=1 ,sequenceName="tableName")
@TableGenerator(name="reference" , initialValue=1 , allocationSize=1 , table="tableName")


@Embedded
private Address address; // getters setters,  it is called valueType object

@Embedded
@AttributeOverrides(value={
	@AttributeOverride(column= @Column(same as it is) , name="propertt of address class"),
	//
})
private Address homeAddress;
@Embeddeable
class Address{} // properties getters setters


@ElementCollection(fetch=fechType.EAGER)
@JoinTable(name="tableName", joinColumns=@JoinColumn(name="name for foreignkey"))
@GenericGenerator(name="reference" , strategy="sequence")// low high
@CollectionId(columns= { @Column(name="primaryKey")} , generator="reference" ,type=@Type(type="int"))
private Collection<Address> addresses = new HashSet<>();
// Address class is value type and it is annotataed with @Embeddebla



@OneToOne(cascade=CascadeType.All)
@JoinColumn(name="foreign key name")// primary key of address table as foreign key in Employee
private Address address;
// default behaviour is eager loading

@OneToOne(mappedBy="address")
private Employee employee;



@OneToMany(cascade=CascadeType.All)
@JoinTable(name="table Name" , joinColumns=@JoinColumn(name="pkofEmplyeeAsForeignKey"),inversjoinColumn=@JoinColumn(name="pkOFAddressAsForeignKey"))
private List<Address> addresses = new ArrayList<Address>();
//sessionFactory

*************************************************************************************************
//DAO

session.beginTransaction();

session.save(Entity e);  int 
//This method is used to save an entity/object into database and return generated primarykey.
//It will throw an exception if an entity already exists in the database.
session.persist(Entity e);
//This method is used to save an entity/object into database and return a void.
//It will throw an exception if an entity already exists in the database.
session.get(Entity.class , int id) : Entity 

session.saveOrUpdate(Entity e);
//If primary key is not available in entity object it will save otherwise update it

session.delete(Entity e);
session.getTransaction().commit();


*******************************************************************************

#dirtycheckingmechanism

if you make any changes in persisted object within transaction boundry even if you didnt
update that object hibernate will automatically persist that updated object into database





