;;; Sierra Script 1.0 - (do not remove this comment)


(include system.sh) (include sci2.sh)
(include colors.sh)

; Views
(define vEgo			0)
(define vBigEgo			110)
(define vBEChagrin		111)
(define vBEDismay		112)
(define vBEDismembered	113)
(define vAuthors		118)
(define vEgoPause		119)
(define vTrapBed		191)
(define vEgoMowLawn		192)
(define vEgoMaze		193)
(define vEveHouseStuff	253)
(define vAirplanes		511)
(define vTitle			800)
(define vCredits		801)
(define vEve			802)
(define vDogExitCar		803)
(define vDogPiss		804)
(define vMountain		806)
(define vTitleGirl		809)
(define vTitleSparkle	815)
(define vThroneStuff	816)
(define vNonookee		818)
(define vFanGirl		819)
(define vGrapeGirl		820)
(define vHelicopter		821)

;Pictures
(define pCredits		91)

; Sounds
(define sScore		7)
(define sHelicopter	17)
(define sThemeSong	101)
(define sMeanwhile	102)
(define sDeath		103)
(define sNonookee	110)


;doorState
(enum
	doorClosed
	doorOpening
	doorOpen
	doorClosing
)


; Game phases
(enum 1
	rgCITY
	rgSHIP
	rgLIFEBOATS
	rgAIRPORT
	rgAIRPLANE
	rgENDGAME ;not used; just here to indicate the final region of Nontoonyt Island
)

; Endgame states
(enum 1
	endMEETKALALAU
	endMEETTRIBE
	endGOTOVOLCANO
)

; Inventory
(enum 1
	iDollarBill			;1
	iLotteryTicket		;2
	iCruiseTicket		;3
	iMillionDollarBill	;4
	iSwimsuit			;5
	iWadODough			;6
	iPassport			;7
	iGrotesqueGulp		;8
	iSunscreen			;9
	iOnklunk			;10
	iFruit				;11
	iSewingKit			;12
	iSpinachDip			;13
	iWig				;14
	iBikiniTop			;15
	iBikiniBottom		;16
	iKnife				;17
	iSoap				;18
	iMatches			;19
	iFlower				;20
	iHairRejuvenator	;21
	iSuitcase			;22
	iAirlineTicket		;23
	iParachute			;24
	iBobbyPin			;25
	iPamphlet			;26
	iAirsickBag			;27
	iStoutStick			;28
	iVine				;29
	iAshes				;30
	iSand				;31
)

;Sunscreen states
(enum 1
	sunscreenAPPLIED
	sunscreenAFTERSWIM
	sunscreenINLIFEBOAT
)

;Suitcase bomb states
(enum 1
	bombHOLDING
	bombEXPLODING
	bombAFTEREXPLOSION
)

;Ego states
(enum
	egoNORMAL				;0
	egoAUTO					;1
	egoDUMMY2				;2
	egoDOPPLEGANGER			;3
	egoBOARDSHIP			;4
	egoINRESORTMAZE			;5
	egoDUMMY6				;6
	egoSUITCASEBOMB			;7
	egoINTERMINAL			;8
	egoBOARDPLANE			;9
	egoLAUNCHPARACHUTE		;10
	egoMEETTRIBE			;11
	egoFALLING				;12
	egoHOLDINGBOTTLE		;13
	egoNOTDROPBOMB			;14
	egoNOWICK				;15
	egoKILLEDBYERUPTION		;16
	egoBLOWNUP				;17
	egoONTVSHOW				;18
	egoDIVING				;19
	egoSTUCKINTREE			;20
	egoATSEA				;21
	egoWONGAME				;22
	egoCAPTURED				;23
)

(define egoSTOPPED			1000)
(define egoDYING			1001)
(define egoDEAD				1002)
(define egoSLEEPING			1005)
(define egoSWIMMING			1006)
(define egoDROWNING			1008)
(define egoSITTING			1009)
(define egoATTABLE			1010)
(define egoCROUCHING		1012)
(define egoONVINE1			1013)
(define egoONVINE2			1014)
(define egoONVINE3			1015)
(define egoEATENBYPIRANHA	1016)

;Global stuff
(define LSL2			0)
(define	INVDESC			2)
(define	DOOR			3)
(define	BASS_SETTER		4)
(define	DEBUG			5)
(define	AIRPLANE_ACTOR	6)
(define	BARBER			7)
(define	HENCHWOMAN		8)
(define	BOSSKEY			9)
(define	COPYPROTECT		10)
(define TITLE			90)
(define SPEED			99)

;Actual rooms
(define rEveHouse		23)
(define rIntroEveHouse	91)
(define rIsland			92)
(define rNonookeeThrone	93)

;Regions
(define CITY		200)
(define SHIP		300)
(define RESORT		400)
(define BEACH		401)
(define AIRPORT		500)
(define AIRPLANE	600)
(define ISLAND		700)

;Required memory sizes in bytes
(define GaugeSize 2048)
(define InvSize 1024)