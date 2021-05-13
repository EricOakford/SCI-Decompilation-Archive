;;; Sierra Script 1.0 - (do not remove this comment)
(script# LBEGO)
(include game.sh) (include "19.shm")
(use Main)
(use StopWalk)
(use Window)
(use Ego)
(use Invent)


(class LBEgo of Ego
	(properties
		name {ego}
		wearingGown 0
	)
	
	(method (get what)
		(if (== what -1)
			(super get: &rest)
		else
			(super get: what &rest)
			(messager say: N_GET V_DO NULL 0 0 LBEGO)
		)
	)
	
	(method (put)
		(if
			(and
				(not (user canControl:))
				(== theCurIcon (theIconBar at: ICON_ITEM))
			)
			(= theCurIcon (theIconBar at: ICON_WALK))
			(theGame setCursor: waitCursor)
		)
		(super put: &rest)
	)
	
	(method (normalize theView)
		(= view (if argc theView else 800))
		(self
			setLoop: -1
			setCel: -1
			setPri: -1
			setMotion: 0
			setCycle: StopWalk -1
			setStep: 3 2
			z: 0
			illegalBits: cWHITE
			ignoreActors: 0
			setSpeed: global369
		)
	)
	
	(method (showInv &tmp saveWindow)
		(if (Inventory firstTrue: #ownedBy ego)
			(= saveWindow systemWindow)
			(= systemWindow SysWindow)
			(Inventory showSelf: ego)
			(if (not (theIconBar curInvIcon?))
				(theIconBar curIcon: (theIconBar at: ICON_WALK) disable: ICON_ITEM)
				(if (& ((theIconBar curIcon?) signal?) DISABLED)
					(theIconBar advanceCurIcon:)
				)
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
			)
			(= systemWindow saveWindow)
			(narrator showTitle: FALSE name: {Narrator})
		else
			(messager say: N_EGO NULL C_EMPTY 0 0 LBEGO)
		)
	)
)
