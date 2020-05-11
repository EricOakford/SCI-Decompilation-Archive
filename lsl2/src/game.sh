;;; Sierra Script 1.0 - (do not remove this comment)


(include system.sh) (include sci2.sh)

; Views
(define vBEChagrin 111)
(define vBEDismay 112)
(define vAuthors 118)
(define vEgoPause 119)


; Game phases
(enum
	phaseTIMEOVER
	phaseCITY
	phaseSHIP
	phaseLIFEBOATS
	phaseAIRPORT
	phaseAIRPLANE
	phaseENDGAME ;not used; just here to indicate the final region of Nontoonyt Island
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
	egoNormal				;0
	egoAuto					;1
	egoDummy2				;2
	egoDoppleganger			;3
	egoBoardedShip			;4
	egoInJungleMaze			;5
	egoDummy6				;6
	egoSuitcaseBombed		;7
	egoInTerminal			;8
	egoBoardedPlane			;9
	egoLaunchedParachute	;10
	egoMeetingTribe			;11
	egoFalling				;12
	egoHoldingBottle		;13
	egoNoDropBomb			;14
	egoNoWick				;15
	egoKilledByEruption		;16
	egoBlownUp				;17
	egoOnTVShow				;18
	egoDiving				;19
	egoStuckInTree			;20
	egoAtSea				;21
	egoWonGame				;22
	egoCaptured				;23
)
(define egoStopped			1000)
(define egoDead				1001)
(define egoDeathMessage		1002)
(define egoSleeping			1005)
(define egoSwimming			1006)
(define egoDrowning			1008)
(define egoSitting			1009)
(define egoAtTable			1010)
(define egoCrouching		1012)
(define egoOnVine1			1013)
(define egoOnVine2			1014)
(define egoOnVine3			1015)
(define egoEatenByPiranha	1016)


;Regions
(define DEBUG		5)
(define BARBER		7)
(define HENCHWOMAN	8)
(define CITY		200)
(define SHIP		300)
(define RESORT		400)
(define BEACH		401)
(define AIRPORT		500)
(define AIRPLANE	600)
(define ISLAND		700)