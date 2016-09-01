package br.ufpe.cin.ines.ress

class User {

	transient springSecurityService

	String username
	String password
	Address address
	String name
	String email
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
		username blank: false, nullable: false, unique: true
		password blank: false
		address blank: false, unique: true
		name blank: false
		email blank: false, unique: true
	}

	static mapping = {
		password column: '`password`'
		cache true
		table 'users'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
