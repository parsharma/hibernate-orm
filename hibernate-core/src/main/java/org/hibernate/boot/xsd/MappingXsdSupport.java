/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.boot.xsd;

import org.hibernate.Internal;

/**
 * Support for XSD handling related to Hibernate's `hbm.xml` and
 * JPA's `orm.xml`.
 *
 * @author Steve Ebersole
 */
@SuppressWarnings("unused")
@Internal
public class MappingXsdSupport {

	/**
	 * Singleton access
	 */
	public static final MappingXsdSupport INSTANCE = new MappingXsdSupport();

	public static final XsdDescriptor jpa10 = LocalXsdResolver.buildXsdDescriptor(
			"org/hibernate/jpa/orm_1_0.xsd",
			"1.0",
			"http://java.sun.com/xml/ns/persistence/orm"
	);

	public static final XsdDescriptor jpa20 = LocalXsdResolver.buildXsdDescriptor(
			"org/hibernate/jpa/orm_2_0.xsd",
			"2.0",
			"http://java.sun.com/xml/ns/persistence/orm"
	);

	public static final XsdDescriptor jpa21 = LocalXsdResolver.buildXsdDescriptor(
			"org/hibernate/jpa/orm_2_1.xsd",
			"2.1",
			"http://xmlns.jcp.org/xml/ns/persistence/orm"
	);

	public static final XsdDescriptor jpa22 = LocalXsdResolver.buildXsdDescriptor(
			"org/hibernate/jpa/orm_2_2.xsd",
			"2.2",
			"http://xmlns.jcp.org/xml/ns/persistence/orm"
	);

	public static final XsdDescriptor jpa30 = LocalXsdResolver.buildXsdDescriptor(
			"org/hibernate/jpa/orm_3_0.xsd",
			"3.0",
			"https://jakarta.ee/xml/ns/persistence/orm"
	);

	public static final XsdDescriptor jpa31 = LocalXsdResolver.buildXsdDescriptor(
			"org/hibernate/jpa/orm_3_1.xsd",
			"3.1",
			"https://jakarta.ee/xml/ns/persistence/orm"
	);

	public static final XsdDescriptor hbmXml = LocalXsdResolver.buildXsdDescriptor(
			"org/hibernate/xsd/mapping/legacy-mapping-4.0.xsd",
			"4.0",
			"http://www.hibernate.org/xsd/orm/hbm"
	);

	public static final XsdDescriptor hibernateMappingXml = LocalXsdResolver.buildXsdDescriptor(
			"org/hibernate/hibernate-mapping-4.0.xsd",
			"4.0",
			"http://www.hibernate.org/xsd/hibernate-mapping"
	);

	private MappingXsdSupport() {
		//Do not construct new instances
	}

	public static XsdDescriptor latestJpaDescriptor() {
		return jpa22;
	}

	public static boolean shouldBeMappedToLatestJpaDescriptor(String uri) {
		// JPA 1.0 and 2.0 share the same namespace URI
		return jpa10.getNamespaceUri().equals( uri );
	}

	public static boolean isValidJpaVersion(String version) {
		switch ( version ) {
			case "1.0":
			case "2.0":
			case "2.1":
			case "2.2":
			case "3.0":
			case "3.1":
				return true;
			default:
				return false;
		}
	}

	public XsdDescriptor jpaXsd(String version) {
		switch ( version ) {
			case "1.0": {
				return jpa10;
			}
			case "2.0": {
				return jpa20;
			}
			case "2.1": {
				return jpa21;
			}
			case "2.2": {
				return jpa22;
			}
			case "3.0:": {
				return jpa30;
			}
			case "3.1:": {
				return jpa31;
			}
			default: {
				throw new IllegalArgumentException( "Unrecognized JPA orm.xml XSD version : `" + version + "`" );
			}
		}
	}

	public XsdDescriptor hbmXsd() {
		return hbmXml;
	}

}
