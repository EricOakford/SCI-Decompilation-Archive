;;; Sierra Script 1.0 - (do not remove this comment)
;**************************************************************
;***
;***	GAME.SH--
;***
;**************************************************************

(include system.sh) (include sci2.sh) ;system and kernel functions

; Regions
(define rgTahiti		300)
(define rgWater			301)
(define rgWashington	302)
(define rgCaves			304)
(define rgScuba			305)
(define rgDebug			307)
(define rgOcean			308)
(define rgTunisia		310)
(define rgSubmarine		314)

; howFast values
(enum
	slow
	medium
	fast
)

;Game phases
(enum
	phaseBEGIN
	phaseTAHITI
	phaseSUB
	phaseTUNISIA
)

;Earring states
(enum
	earringINITIAL
	earringOPEN
	earringSEARCHED
)

;EO: This game does inventory items a little weirdly.
; Rather than having all of the inventory items in one script,
; it loads them by region!

; Main Inventory items (those that are present in more than one region)
(enum
	iEnvelope	;0
	iMicrofilm	;1
	iIDCard		;2
)

; Tahiti inventory
(enum 3
	iBlackBook	;3
	iChange		;4
	iTahitiKey	;5
	iEarring	;6
)

; Tunisia inventory
(enum 3
	iFish				;3
	iCapsule			;4
	iMap				;5
	iTunisiaKey			;6
	iDuctTape			;7
	iTranquilizerGun	;8
	iSugarCanister		;9
	iFlourCanister		;10
	iCoffeeCanister		;11
	iBusinessCard		;12
	iFoodPlatter		;13
	iNote				;14
)

; Submarine inventory
(enum 3
	iExplosive			;3
	iRumBottle			;4
	iSubKey				;5
	iDiver				;6
	iFlare				;7
	iDevice				;8
	iCotterPin			;9
	iWasher				;10
	iNut				;11
	iMetalCylinder		;12
	iVernierCaliper		;13
	iCodeBook			;14
	iHammer				;15
	iOpenEndWrench		;16
	iShearedCylinder	;17
)

;Tahiti flags
(define fWearingShirt	$4000)