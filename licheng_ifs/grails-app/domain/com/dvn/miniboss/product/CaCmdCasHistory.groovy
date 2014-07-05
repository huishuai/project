package com.dvn.miniboss.product

class CaCmdCasHistory {
	String id
	String caid
	String resendcounter
	String customid
	String groupid
	String teamno
	String serviceid
	String cmdtype
	String stbnum
	String iccnum
	String prgid
	String prgname
	String prgprovider
	String controlid
	String productbegindate
	String productenddate
	String operationCode
	String operatorname
	String errorflag
	String error
	String cmddate
	String areaid
	String netid
	String issent
	String isspecial
	Date recorddate
	Date senddate
	String emmtype
	String stbareacode
	String mailcontent


	static constraints = {
		id(size: 0..20, nullable: false)
		caid(size: 0..10, nullable: true)
		resendcounter(size: 0..2, nullable: true)
		customid(size: 0..20, nullable: true)
		groupid(size: 0..20, nullable: true)
		teamno(size: 0..20, nullable: true)
		serviceid(size: 0..20, nullable: true)
		cmdtype(size: 0..50, nullable: true)
		stbnum(size: 0..30, nullable: true)
		iccnum(size: 0..30, nullable: true)
		prgid(size: 0..500, nullable: true)
		prgname(size: 0..2000, nullable: true)
		prgprovider(size: 0..3, nullable: true)
		controlid(size: 0..500, nullable: true)
		productbegindate(size: 0..30, nullable: true)
		productenddate(size: 0..30, nullable: true)
		operationCode(size: 0..20, nullable: true)
		operatorname(size: 0..20, nullable: true)
		errorflag(size: 0..20, nullable: true)
		error(size: 0..200, nullable: true)
		cmddate(size: 0..30, nullable: true)
		areaid(size: 0..20, nullable: true)
		netid(size: 0..20, nullable: true)
		issent(size: 0..5, nullable: true)
		isspecial(size: 0..5, nullable: true)
		recorddate(nullable: true)
		senddate(nullable: true)
		emmtype(size: 0..20, nullable: true)
		stbareacode(size: 0..30, nullable: true)
		mailcontent(size: 0..2000, nullable: true)
	}

	static mapping = {
		table "ca_cmd_cas_history"
		version false
		id generator: 'assigned', column: 'TRANSNUM'
	}
}
