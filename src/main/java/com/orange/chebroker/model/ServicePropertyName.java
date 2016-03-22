package com.orange.chebroker.model;

/**
 * service properties names (which is also the suffix of the system environment variables)
 * NOTE: order of the list is important, so use Enum.values() instead of an EnumSet
 */
public enum ServicePropertyName {
	METADATA_DISPLAYNAME,
	METADATA_IMAGEURL,
	METADATA_SUPPORTURL,
	METADATA_DOCUMENTATIONURL,
	METADATA_PROVIDERDISPLAYNAME,
	METADATA_LONGDESCRIPTION, 
	NAME,
	DESCRIPTION,
	BINDEABLE,
	TAGS
}
