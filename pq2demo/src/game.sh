;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)

;Locales
(define GUN			150)
(define FIELDKIT	153)
(define JET			154)
(define COVE		155)
(define OFFICE		156)
(define DEBUG		801)

;Regions
(define SEWER 205)

;Inventory items
(enum
	iHandGun			;0
	iAmmoClips			;1
	iKeyRing			;2
	iUnmarkedCarKeys	;3
	iMoneyClip			;4
	iThankYouLetter		;5
	iDeathThreat		;6
	iWallet				;7
	iHandcuffs			;8
	iWireClippers		;9
	iFieldKit			;10
	iFlower				;11
	iNewMugShot			;12
	iHitList			;13
	iKnife				;14
	iEarProtectors		;15
	iPlaneTicket		;16
	iPlasterCast		;17
	iLostBadge			;18
	iThumbprint			;19
	iBullets			;20
	iEmptyHolster		;21
	iFingerprint		;22
	iOldMugShot			;23
	iEnvelopeCorner		;24
	iEnvelope			;25
	iJailClothes		;26
	iMoteKey			;27
	iVialOfBlood		;28
	iLipstick			;29
	iWalkieTalkie		;30
	iJailerRevolver		;31
	iGasMask			;32
	iBombInstructions	;33
	iCarRegistration	;34
	iColbyCard			;35
	iMarieDoorNote		;36
	iLPDBusinessCard	;37
)

;Game phases
(define phaseMALL 3)
(define phaseCOVE 5)

;Cars
(define carWORK 13)
(define carPERSONAL 33)

;Gunfire states
(enum 1
	gunPERMITTED		;1	;deadly force may be needed
	gunUSELESS			;2	;just wastes ammo and prints a messsage
	gunPROHIBITED		;3	;institutionalized, ending the game
)

;Date aftermath states
(enum
	dateNOCALL
	dateNODATE
	dateKISSED
	dateNOKISS
	dateNOEAT
	dateWALKEDOUT
)

;Event flags
(enum 1
	CAN_GET_WARRANT				;1
	CAN_CALL_FOR_BACKUP 		;2
	GOT_SCUBA_TANK				;3
	GOT_WET_SUIT				;4
	GOT_SWIM_FINS				;5
	GOT_WEIGHT_BELT				;6
	GOT_BUOYANCY_VEST			;7
	GOT_SCUBA_MASK				;8
	CORONER_HAS_WOODY			;9
	GELEPSI_BOOKING_EVIDENCE	;10
	FIELD_KIT_BIN_OPEN			;11
	fEgoDeskUnlocked			;12
	GOT_MOTEL_WARRANT			;13
	SWAT_AT_MOTEL				;14
	FLAG_15						;15
	FLAG_16						;16
)
(define MARIE_KIDNAPPING_REPORTED 27)
(define FLAG_31 31)
(define VISITED_HOMICIDE_OFFICE 32)
(define FOUND_DIVING_CERTIFICATE 33)
(define TRIED_TO_GET_TICKET_TO_HOUSTON 34)
(define SHOP_WOMAN_TALKING_TO_HAINES 35)
(define HOUSTON_FLIGHT_AUTHORIZED 36)
(define STEELTON_FLIGHT_AUTHORIZED 37)
(define KEITH_FOLLOWING_SONNY 40)
(define SMUDGED_GLOVEBOX_PRINTS 41)
(define FLAG_45 45)
(define GOT_EMPTY_HOLSTER 50)
(define ASKED_SHOP_WOMAN_ABOUT_CAR 51)
(define CALLED_CORONER_AT_COTTON_COVE 54)
(enum 56
	LEARNED_ABOUT_LLOYD_ADDICTION	;56
	STEVE_TELLS_ABOUT_LLOYD			;57
	POINTS_FINDDIVINGCERTIFICATE	;58
	POINTS_OPENLOCKER				;59
	POINTS_GETGUN					;60
	POINTS_GETHANDCUFFS				;61
	POINTS_GETAMMOCLIPS				;62
	FLAG_63							;63
	POINTS_READSUBPOENA				;64
	POINTS_READEVIDENCEREPORT	 	;65
	POINTS_GETEARPROTECTORS			;66
	POINTS_ALIGNGUNSIGHTS			;67
	POINTS_GETMOREAMMO				;68
)
(define SEARCHED_COTTON_COVE_GROUND 71)
(define TOOK_PICTURE_COTTON_COVE 72)
(define READ_JAIL_CLOTHES_TAG 73)
(define GOT_CORRECT_SCUBA_TANK 74)
(define READ_JAILER_VIN 77)
(define LOOKED_AT_PASSENGER_LIST 82)
(define READ_CAR_RENTALS 84)
(define POINTS_LLOYDINREHAB 85)
(define LEARNED_BAINS_STAYED_AT_MOTEL 88)
(define POINTS_CHECKMARIEHANDWRITING 90)
(define COLBY_PHONE_TAP 94)
(define WARNED_COLBY_ABOUT_BAINS 95)
(define POINTS_FINDMANHOLE 100)
(define POINTS_OPENMANHOLE 101)
(define POINTS_GETUNMARKEDCARKEY 104)
(define POINTS_GETWALLET 105)
(define POINTS_GETTHANKYOULETTER 106)
(define FOUND_JAILER_HAND 108)
(define FOUND_JAILER_BODY 109)
(define RADIO_IN_COVE_SHOOTING 111)
(define MALL_FIRST_ARRIVED 112)
(define HAVE_MOTEL_BLOOD 113)
(define STOLEN_CAR_TOWED 118)
(define FOUND_WOMAN_STOLEN_CAR 119)
(enum 122
	FOUND_MARIE							;122
	POINTS_READBULLETINBOARD			;123
	POINTS_LEARNCAPTAINPASSWORDS		;124
	POINTS_SOUND						;125
	TOLD_KEITH_ABOUT_JAILER_REVOLVER	;126
	BOOKED_JAILER_REVOLVER				;127
	BOOKED_FINGERPRINT					;128
	BOOKED_THUMBPRINT					;129
	BOOKED_KNIFE						;130
	FLAG_131							;131
	BOOKED_PLASTER_CAST					;132
	BOOKED_BULLETS						;133
	BOOKED_JAIL_CLOTHES					;134
	BOOKED_HIT_LIST						;135
	BOOKED_COLBY_CARD					;136
	BOOKED_ENVELOPE						;137
	BOOKED_ENVELOPE_CORNER				;138
	BOOKED_LOST_BADGE					;139
	FLAG_140							;140 ;is tested and cleared when entering Burt's Park, but is never set
	UNUSED_141							;141
	KEITH_WAITING_AT_JAIL				;142
	HAVE_TRUNK_BLOOD					;143
	HAVE_COTTON_COVE_BLOOD				;144
	BOOKED_EMPTY_HOLSTER				;145
	BOOKED_COTTON_COVE_BLOOD			;146
	BOOKED_TRUNK_BLOOD					;147
	BOOKED_MOTEL_BLOOD					;148
	FOUND_WOODY_IN_TRUNK				;149
	MARSHALL_LEFT_AIRPORT_BATHROOM		;150
	MARSHALL_AT_AIRPORT_SINK			;151
	VISITED_MALL_CRIME_SCENE			;152
)
(define GOT_TICKET_TO_STEELTON 155)
(define TELL_KEITH_ABOUT_MALL_SCENE 156)
(define POINTS_SAVEMARIE		160)
(define POINTS_MARIECALMED		161)
(define FASTENED_SEATBELT 163)
(define DROVE_TO_MOTEL 165)
(define TRIED_TO_GO_TO_HOUSTON 166)
(define GAME_RESTARTING 167)