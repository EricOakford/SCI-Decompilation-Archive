;;; Sierra Script 1.0 - (do not remove this comment)
(script# FPEGO) ;19
(include game.sh) (include "19.shm")
(use Main)
(use StopWalk)
(use Window)
(use Ego)
(use Invent)


(class FPEgo of Ego
	(properties
		name "ego"
		noun N_EGO
		modNum FPEGO
		sightAngle 180
		state startUpdOn
		view 800
		loop 0
		cel 0
		signal ignrHrz
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_BEER
				(messager say: noun theVerb NULL NULL NULL FPEGO)
				(ego put: iBeerBottle)
				(ego get: -1 iEmptyBottle)
				(Bset fDrankBeer)
			)
			(V_CUP_FULL
				(messager say: noun theVerb NULL NULL NULL FPEGO)
				(ego put: iFullCup)
				(ego get: -1 iEmptyCup)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (get what theNoun)
		(if (== what -1)
			(super get: theNoun &rest)
		else
			(super get: what &rest)
			(messager say: (theNoun noun?) V_DO NULL NULL NULL curRoomNum)
		)
	)
	
	(method (put)
		(super put: &rest)
		(if
			(and
				(not (user canControl:))
				(== oldCurIcon (theIconBar at: ICON_USEIT))
			)
			(= oldCurIcon (theIconBar at: ICON_WALK))
			(theGame setCursor: waitCursor)
		else
			(theIconBar curIcon: (theIconBar at: ICON_WALK))
			(theGame setCursor: 0)
		)
	)
	
	(method (normalize theView)
		(= view (if argc theView else 800))
		(self
			setLoop: -1
			setCel: -1
			setPri: -1
			setMotion: 0
			setCycle: StopWalk -1
			setStep: 6 4
			z: 0
			illegalBits: cWHITE
			ignoreActors: 0
		)
	)
	
	(method (showInv &tmp oldWindow)
		(if (Inventory firstTrue: #ownedBy ego)
			(= oldWindow systemWindow)
			(= systemWindow SysWindow)
			(Inventory showSelf: ego)
			(if (not (theIconBar curInvIcon?))
				(theIconBar curIcon: (theIconBar at: ICON_WALK) disable: ICON_USEIT)
				(if (& ((theIconBar curIcon?) signal?) DISABLED)
					(theIconBar advanceCurIcon:)
				)
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
			)
			(= systemWindow oldWindow)
			(narrator showTitle: FALSE name: {Narrator})
		else
			(messager say: N_EGO NULL C_EMPTY NULL NULL FPEGO)
		)
	)
)
