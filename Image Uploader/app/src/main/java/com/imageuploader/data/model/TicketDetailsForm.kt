package com.imageuploader.data.model

class TicketDetailsForm {

    var content: TicketDetails? = null
}


class TicketDetails{



    var errorMessages: List<String>? = null
    var isErrorFlag: Boolean = false


    var tagNumber: String? = null
    var reportingAssociate: String? = null
    var profileURL: String? = null
    var defectTypeID: String? = null
    var defectCauseID: String? = null
    var defectType: String? = null
    var defectCause: String? = null
    var defectSectionID: String? = null

    var defectDesc: String? = null
    var partNumber: String? = null
    var partName: String? = null
    var dateCreated: String? = null
    var dateResolved: String? = null
    var reportingBadgeNumber: String? = null
    var resolvedBy: String? = null
    var currentSectionID: Long? = null
    var currentSectionDesc: String? = null
    var basicCostCode: String? = null
    var rejectCode: String? = null
    var quantity: Long? = null
    var defectOtherReason: String? = null
    var groupLeaderReviewID: String? = null
    var groupLeaderReviewName: String? = null
    var costCodeMap: Map<String, String>? = null
    var chargeCodeMap: Map<Int, String>? = null
    var chargeCode: Int = 0
    var chargeDesc: String? = null

    private val newPartNumber: String? = null
    private val newPartName: String? = null
    private val misLabel: Boolean = false


    var inOutMap: Map<String, String>? = null

    var adjRsnMap: Map<String, String>? = null

    var failRsnMap: Map<String, String>? = null

    var custSplrCdMap: Map<String, String>? = null

    var custSplrCdList: List<String>? = null

    var respCostCodeMap: Map<String, String>? = null

    var respCostCode: String? = null

    var endItemExpRange: String? = null

    var adjRejectReason: String? = null

    var adjRejectedBy: String? = null

    var adjRejectedOn: String? = null

    var sapPartPrice: String? = null

    var sapDocNo: String? = null

    var eiExpRangeMap: Map<String, String>? = null

    var inOut: String? = null

    var isChangedSplrCd: Boolean = false

    var splrCdChgdRsn: String? = null

    var doNo: String? = null

    var lineCodeOptions: Map<String, String>? = null
    //@NotEmpty(message = "validate.notempty.sequenceNumber", groups = {UpdateTicket.class,SaveSupplierResp.class})
    var sequenceNumber: String? = null
    var isPartSequencedFlag: Boolean = false
    var supplierCode: String? = null
    var supplierName: String? = null
    var expeditorID: String? = null
    var depoCode: String? = null
    var reasonForCancel: String? = null
    var currentGroupID: String? = null
    var currentGroupDesc: String? = null
    var sectionWithDefectDetModels: List<SectionWithDefectDetModel>? = null

    var sectionID: Long? = null
    var groupLeader: String? = null
    var ticketTransferCostCode: String? = null
    var groupLeaderID: String? = null
    var groupLeaderName: String? = null
    var groupID: String? = null
    /*
    private List<SectionModel> sectionModels;
*/
    /*public List<SectionModel> getSectionModels() {
        return sectionModels;
    }
    public void setSectionModels(List<SectionModel> sectionModels) {
        this.sectionModels = sectionModels;
    }*/
    var reasonForChange: String? = null
    var rejectReason: String? = null
    var isRejectIndFlag: Boolean = false
    /**
     * @return the transferIndFlag
     */
    /**
     * @param transferIndFlag the transferIndFlag to set
     */
    var isTransferIndFlag: Boolean = false
    var assignedFromGroupLeaderID: String? = null
    var assignedFromSectionID: String? = null
    var assignedFromSectionDesc: String? = null
    var assignedFromGroupLeaderName: String? = null
    var assignedFromGroupID: String? = null
    var assignedFromGroupDesc: String? = null
    var location: String? = null
    var isAssignToSQAFlag: Boolean = false

    var sqaAssociateNumber: String? = null
    var sqaAssocicateName: String? = null
    var scrapQuantity: Long? = null
    var useQuantity: Long? = null
    var returnToSupplierQuantity: Long? = null
    var reworkQuantity: Long? = null
    var totalQuantity: Long? = null
    //@NotEmpty(message = "validate.notempty.returnAuthorizationName", groups = {SaveSupplierResp.class})
    //@Pattern(message = "validate.pattern.returnAuthorizationName", regexp = "[a-zA-Z ]*", groups = {SaveSupplierResp.class})
    var returnAuthorizationName: String? = null
    var isActiveInvestigation: Boolean = false
    var rmaNumber: String? = null
    var isModifiedQuantityFlag: Boolean = false
    var isModifiedSequenceNumberFlag: Boolean = false
    var printerName: String? = null
    var noOfprintCopies: Int = 0
    var edoDate: String? = null
    var edoExpeditorID: String? = null
    var ticketStatus: String? = null
    var lineCode: String? = null
    var isTransToSQAFlag: Boolean = false
    var kanbanNumber: String? = null
    var isDeadPartFlag: Boolean = false
    var groupDesc: String? = null
    /*public List<ScrapImageModel> getScrapImageModels() {
        return scrapImageModels;
    }*/
    /* public void setScrapImageModels(List<ScrapImageModel> scrapImageModels) {
        this.scrapImageModels = scrapImageModels;
    }*/
    var isInventoryFlag: Boolean = false
    var isOldInventoryFlag: Boolean = false
    var isSAPPart: Boolean = false
    var isIssueEDO: Boolean = false
    /*
    private List<ScrapImageModel> scrapImageModels;
*/
    var isPrintEnableFlag: Boolean = false
    var problemDescription: String? = null
    var groupIdAndDescMap: Map<String, Any>? = null
    var isGroupIdAndDescMapFlag: Boolean = false

    var endItem: String? = null
    var vin: String? = null

    var isAdjTktFlag: Boolean = false

    var adjmtRsnCd: String? = null
    var failRsnCd: String? = null
    var adjmtInOut: String? = null
    var adjmtPlusMinus: String? = null


}

class SectionWithDefectDetModel {

    var sectionID: Int? = 0
    var sectionDescription: String? = null
    var defectCauses: List<DefectCauses>? = null


}

class DefectCauses {

    var rowID: Int = 0
    var editID: Int = 0
    var defectCauseID: Int = 0
    var defectCauseDesc: String? = null
    var oldDefectCauseDesc: String? = null
    var activeFlag: String? = null
    var causeActiveFlag:  Boolean = false
    var sectionID: Int = 0
    var sectionDesc: String? = null
    var newRecord: Boolean = false
    var defectTypes: List<DefectTypes>? = null



}

class DefectTypes {

    var rowID: Int = 0
    var editID: Int = 0
    var defectTypeID: Int = 0
    var defectTypeDesc: String? = null
    var oldDefectTypeDesc: String? = null
    var activeFlag: String? = null
    var typeActiveFlag: Boolean = false
    var defectCauseID: Int = 0
    var defectCauseDesc:String? = null
    var sectionDesc: String? = null
    var newRecord: Boolean = false


}
