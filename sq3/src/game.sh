;;; Sierra Script 1.0 - (do not remove this comment)

(include system.sh) (include sci2.sh)

; howFast values
(enum
	slow
	medium
	fast
)

;Inventory items
(enum	
	iGlowingGem				;0
	iWire					;1
	iLadder					;2
	iReactor				;3
	iOratOnAStick			;4
	iThermoUnderwear		;5
	iChickenHat				;6
	iDecoderRing			;7
	iBuckazoids				;8
	iMetalPole				;9
	iThermalDetonator		;10
	iKeycard				;11
	iCoveralls				;12
	iVaporizer				;13
	iElmoPicture			;14
	iElmoPictureCopy		;15
	iInvisibilityBelt		;16
	iBagOfFastFood			;17
)

;Locales and Regions
(define DUNE 501)
(define PESTULON 503)
(define ORTEGA 600)
(define JUNKBAY 700)
(define TRAVEL 701)
(define SCUMSOFT 702)
(define GRABBER 703)

;Invisibility belt states
(enum
	beltTURNEDOFF
	beltTURNEDON
	beltDEPLETED
)

;Motivator states
(define motivatorONFLOOR 1)
(define motivatorGRABBED 3)
(define motivatorINSHIP 4)

;Ship locations
(enum
	shipJUNKBAY
	shipSPACE
	shipMONOLITH
	shipORTEGA_ORBIT
	shipPHLEEBHUT_ORBIT
	shipORTEGA_LAND
	shipPHLEEBHUT_LAND
	shipPESTULON_ORBIT
	shipPESTULON_LAND
)

;Ship shields
(enum
	shieldOFF
	shieldFRONT
	shieldBACK
)

;Terminator states
(enum 1
	terminatorMEET
	terminatorSEARCHING
	terminatorDEAD
)