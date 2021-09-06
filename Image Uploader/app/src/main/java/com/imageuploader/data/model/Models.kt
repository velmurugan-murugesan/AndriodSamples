package com.imageuploader.data.model

data class LoginRequest(val username: String, val password: String,val grant_type: String)

data class LoginResponse(val loggedUserID: String, val groupLeaderBadgeID: String, val loggedUserRole: String?, val loggedUserSectionID: String?,
                         val groupAssociationID: Int, val genericUserID: String?, val createTicketConfigFlag: Boolean, val createEIVINTicketConfigFlag: Boolean, val additionalRoles: List<String>)

data class LoginErrorResponse(val error: String, val error_description: String)

data class PartNumberResponse(val partNumber: String, var partName: String?, var kanbanNumber: String?, var supplierCode: String?, var expeditorID:String?,
                              var depoCode: String, val partSequencedFlag: Boolean, var location: String?)

data class SupplierNameResponse(var supplierName: String?)

data class TicketCountResponse(var content: Int)

data class ValidationModel(val partNumber: String, val supplierCode: String)

data class ValidationResponse(val partNumber: String,val expiredDate: String, val expiredFlag: Boolean)

data class Status(val status: String,val message: String)

data class PartInfo(val partName: String, val partNumber: String)